package com.master.demo.service.impl;

import com.master.demo.Entities.Objeto;
import com.master.demo.Entities.Partida;
import com.master.demo.Repositories.PartidaRepository;
import com.master.demo.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaServiceImpl implements PartidaService {

    private PartidaRepository partidaRepository;

    @Autowired
    public PartidaServiceImpl(final PartidaRepository partidaRepository){
        this.partidaRepository = partidaRepository;
    }

    @Override
    public void insertarPartida(Integer idPartida, String gastos, String informacion) {
        Partida partida = new Partida(idPartida, gastos, informacion);
        this.partidaRepository.save(partida);
    }
}
