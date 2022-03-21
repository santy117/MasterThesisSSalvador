package com.master.demo.controller;


import com.example.api.PartidasApi;
import com.example.models.ObjectResponseDTO;
import com.example.models.PartidaDTO;
import com.example.models.PartidaResponseDTO;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PartidasController implements PartidasApi {



    @Autowired
    private PartidaService partidaService;


    @Override
    public ResponseEntity<PartidaResponseDTO> getPartidaById(Integer partidaId) {
        PartidaResponseDTO partidaResponse = this.partidaService.getPartidaByIdPartida(partidaId);
        return new ResponseEntity<>(partidaResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PartidaResponseDTO>> getPartidasByVersion(Integer versionId) {
        List<PartidaResponseDTO> partidaResponse = this.partidaService.getPartidasByIdVersion(versionId);
        return new ResponseEntity<>(partidaResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> insertPartida(PartidaDTO body) {
        this.partidaService.insertarPartida(body.getIdVersion(),body.getGastos(), body.getInformacion());
        return null;
    }
}
