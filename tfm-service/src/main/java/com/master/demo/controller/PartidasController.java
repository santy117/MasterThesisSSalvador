package com.master.demo.controller;


import com.example.api.PartidasApi;
import com.example.models.PartidaDTO;
import com.example.models.PartidaResponseDTO;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PartidasController implements PartidasApi {



    @Autowired
    private PartidaService partidaService;


    //TODO: implementar metodo
    @Override
    public ResponseEntity<PartidaResponseDTO> getPartidaById(Integer partidaId) {
        return null;
    }

    //TODO: implementar metodo
    @Override
    public ResponseEntity<List<PartidaResponseDTO>> getPartidasByVersion(Integer versionId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> insertPartida(PartidaDTO body) {
        this.partidaService.insertarPartida(body.getIdVersion(),body.getGastos(), body.getInformacion());
        return null;
    }
}
