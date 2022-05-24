package com.master.demo.service.impl;


import com.example.models.RegistroPeticionesResponseDTO;
import com.master.demo.Entities.RegistroPeticion;
import com.master.demo.Entities.UsuariosCache;
import com.master.demo.Repositories.RegistroPeticionRepository;
import com.master.demo.Repositories.UsuariosCacheRepository;
import com.master.demo.service.CacheService;
import com.master.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CacheServiceImpl implements CacheService {

    private UsuariosCacheRepository usuariosCacheRepository;

    @Autowired
    public CacheServiceImpl(final UsuariosCacheRepository usuariosCacheRepository){
        this.usuariosCacheRepository = usuariosCacheRepository;
    }


    @Override
    @Transactional
    public void setUsuarioCache(String usuario, String versiones) {
        UsuariosCache usuariosCache = new UsuariosCache();
        usuariosCache.setUsuario(usuario);
        usuariosCache.setVersionesCacheadas(versiones);

        Optional<String> versionesRep = this.usuariosCacheRepository.findVersionesByUsuario(usuario);
        if(versionesRep.isPresent()){
            this.usuariosCacheRepository.updateVersiones(usuariosCache.getUsuario(), usuariosCache.getVersionesCacheadas());
        }else{
            this.usuariosCacheRepository.save(usuariosCache);
        }
    }

    @Override
    public String getVersionesCacheadas(String usuario) {
        Optional<String> versionesRep = this.usuariosCacheRepository.findVersionesByUsuario(usuario);
        if(versionesRep.isPresent()) {
            return this.usuariosCacheRepository.findVersionesByUsuario(usuario).get();
        }else{
            return "";
        }

    }
}
