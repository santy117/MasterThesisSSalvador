package com.master.demo.controller;


import com.example.api.ObjetosApi;
import com.example.models.ObjectDTO;
import com.example.models.ObjectResponseDTO;
import com.example.models.VersionDTO;
import com.master.demo.service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ObjetosController implements ObjetosApi {

    @Autowired
    private ObjetoService objetoService;


    @Override
    public ResponseEntity<Void> createObject(ObjectDTO body) {
        this.objetoService.createObject(body.getNombre());
        return null;
    }

    @Override
    public ResponseEntity<Void> createObjectVersion(VersionDTO body) {
        System.out.println(body.getObjectId());
        if(body.getObjectId()!=null){
            this.objetoService.createVersion(body.getObjectId());
        }
        return null;
    }

    @Override
    public ResponseEntity<List<ObjectResponseDTO>> getAllObjects() {
        List<ObjectResponseDTO> objectResponse = this.objetoService.getAllObjects();
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
        //return ResponseEntity.ok().body(this.objetoService.getAllObjects());
    }

    @Override
    public ResponseEntity<ObjectResponseDTO> getObjectById(Integer objectId) {
        ObjectResponseDTO objectResponse = this.objetoService.getObjectById(objectId);
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ObjectResponseDTO> getObjectByVersionId(Integer versionId) {
        ObjectResponseDTO objectResponse = this.objetoService.getObjectByIdVersion(versionId);
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
    }

}
