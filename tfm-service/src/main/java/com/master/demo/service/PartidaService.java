package com.master.demo.service;

import com.example.models.PartidaResponseDTO;

import java.util.List;

public interface PartidaService {
    void insertarPartida( Integer idVersion, String gastos, String informacion);

    PartidaResponseDTO getPartidaByIdPartida(Integer partidaId);

    List<PartidaResponseDTO> getPartidasByIdVersion(Integer versionId);
}
