package com.master.demo.controller;


import com.example.api.PartidasApi;
import com.example.models.PartidaDTO;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PartidasController implements PartidasApi {



    @Autowired
    private PartidaService partidaService;


    @Override
    public ResponseEntity<Void> insertPartida(PartidaDTO body) {
        this.partidaService.insertarPartida(body.getIdVersion(),body.getGastos(), body.getInformacion());
        return null;
    }
}
