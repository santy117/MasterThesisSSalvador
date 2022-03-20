package com.master.demo.service.impl;

import com.master.demo.Entities.Objeto;
import com.master.demo.Entities.Partida;
import com.master.demo.Entities.Version;
import com.master.demo.Repositories.PartidaRepository;
import com.master.demo.Repositories.VersionRepository;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PartidaServiceImpl implements PartidaService {

    private PartidaRepository partidaRepository;
    private VersionRepository versionRepository;

    @Autowired
    public PartidaServiceImpl(final PartidaRepository partidaRepository, final VersionRepository versionRepository){
        this.partidaRepository = partidaRepository;
        this.versionRepository = versionRepository;
    }

    @Override
    @Transactional
    public void insertarPartida(Integer idVersion, String gastos, String informacion) {
        Partida partida = new Partida();
        Optional<Version> version = this.versionRepository.findById(idVersion);
        partida.setVersion(version.get());
        partida.setGastos(gastos);
        partida.setInformacion(informacion);
        this.partidaRepository.save(partida);
    }
}
