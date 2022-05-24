package com.master.demo.controller;


import com.example.api.AuthenticationApi;
import com.example.models.AuthDTO;
import com.master.demo.service.CacheService;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthenticationController implements AuthenticationApi {

    @Autowired
    CacheService cacheService;

    @Autowired
    PartidaService partidaService;

    @Override
    public ResponseEntity<Void> authentication(AuthDTO body) {
        String versiones = this.cacheService.getVersionesCacheadas(body.getUsuario());
        String[] versionesArray = versiones.split(" ");
        for(String version : versionesArray){
            System.out.println("Cargando en cache las partidas de la version: "+ version + " para el usuario "+ body.getUsuario());
            this.partidaService.getPartidasByIdVersion(Integer.parseInt(version), body.getUsuario());
        }

        return null;
    }
}
