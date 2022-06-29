package com.master.demo.service;

import com.example.models.PartidaResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PartidaService {
    void insertarPartida( Integer idVersion, String gastos, String informacion);

    PartidaResponseDTO getPartidaByIdPartida(Integer partidaId, String user);

    List<PartidaResponseDTO> getPartidasByIdVersion(Integer versionId, String user);

    void importarPartidas(MultipartFile file);

    void importarPartidasSync(MultipartFile file);

    void registroPeticiones(String tipoRegistro, Integer idVersion, String user);

    void cachePartidasByIdVersionAsync(Integer versionId, String user);
}
