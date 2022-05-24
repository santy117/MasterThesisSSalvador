package com.master.demo.service;

import com.example.models.RegistroPeticionesResponseDTO;

import java.util.List;

public interface CacheService {
    void setUsuarioCache(String usuario, String versiones);

    String getVersionesCacheadas(String usuario);
}
