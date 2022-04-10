package com.master.demo.service;

import com.example.models.RegistroPeticionesResponseDTO;

import java.util.List;

public interface UsuarioService {
    List<RegistroPeticionesResponseDTO> getRegistroPeticiones(String usuario);
}
