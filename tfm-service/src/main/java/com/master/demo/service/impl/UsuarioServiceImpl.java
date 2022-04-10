package com.master.demo.service.impl;


import com.example.models.PartidaResponseDTO;
import com.example.models.RegistroPeticionesResponseDTO;
import com.master.demo.Entities.Partida;
import com.master.demo.Entities.RegistroPeticion;
import com.master.demo.Repositories.RegistroPeticionRepository;
import com.master.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private RegistroPeticionRepository registroPeticionRepository;

    @Autowired
    public UsuarioServiceImpl(final RegistroPeticionRepository registroPeticionRepository){
        this.registroPeticionRepository = registroPeticionRepository;
    }

    @Override
    public List<RegistroPeticionesResponseDTO> getRegistroPeticiones(String usuario) {
        List<RegistroPeticionesResponseDTO> registroPeticionesResponseDTO = new ArrayList<>();
        List<RegistroPeticion> registroPeticiones =  this.registroPeticionRepository.findRegistroByUsuario(usuario);
        registroPeticiones.forEach(registro -> {
            RegistroPeticionesResponseDTO registroTemp = new RegistroPeticionesResponseDTO();
            registroTemp.setIdPeticion(registro.getIdPeticion());
            registroTemp.setTipoPeticion(registro.getTipoPeticion());
            registroTemp.setIdObjeto(registro.getObjeto().getIdObjeto());
            registroTemp.setIdVersion(registro.getVersion().getIdVersion());
            registroPeticionesResponseDTO.add(registroTemp);
        });
        return registroPeticionesResponseDTO;
    }
}
