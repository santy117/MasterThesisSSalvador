package com.master.demo.controller;


import com.example.api.UsuarioApi;
import com.example.models.PartidaResponseDTO;
import com.example.models.RegistroPeticionesResponseDTO;
import com.master.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuariosController implements UsuarioApi {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<RegistroPeticionesResponseDTO>> getRegistroPeticiones(String usuario) {
        List<RegistroPeticionesResponseDTO> registroPeticionesResponse = this.usuarioService.getRegistroPeticiones(usuario);
        return new ResponseEntity<>(registroPeticionesResponse, HttpStatus.OK);
    }
}
