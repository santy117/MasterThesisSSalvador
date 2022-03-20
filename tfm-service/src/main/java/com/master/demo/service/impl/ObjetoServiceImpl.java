package com.master.demo.service.impl;

import com.example.models.ObjectDTO;
import com.master.demo.Entities.Objeto;
import com.master.demo.Entities.Version;
import com.master.demo.Repositories.HelloWorldRepository;
import com.master.demo.Repositories.ObjetoRepository;
import com.master.demo.Repositories.VersionRepository;
import com.master.demo.service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObjetoServiceImpl implements ObjetoService {

    private ObjetoRepository objetoRepository;
    private VersionRepository versionRepository;

    @Autowired
    public ObjetoServiceImpl(final ObjetoRepository objetoRepository, final VersionRepository versionRepository){
        this.objetoRepository = objetoRepository;
        this.versionRepository = versionRepository;
    }

    @Override
    public List<ObjectDTO> getAllObjects() {
        List<ObjectDTO> objetosResponse = new ArrayList<ObjectDTO>();
       /* List<Objeto> objetos = this.objetoRepository.findAll();
        objetos.forEach(objeto -> {
            ObjectDTO itemResponse = new ObjectDTO();
            itemResponse.setIdObjeto(objeto.getIdObjeto());
            itemResponse.setIdVersion(objeto.getIdVersion().getIdVersion());
            itemResponse.setNombre(objeto.getNombre());
            objetosResponse.add(itemResponse);
        });*/
        return objetosResponse;
    }

    @Override
    @Transactional
    public void createObject(String nombre) {
        Objeto objeto = new Objeto();
        objeto =  this.objetoRepository.save(objeto);
        objeto.setNombre(nombre);
        Version version = new Version();
        version.setIdObjeto(objeto.getIdObjeto());
        this.versionRepository.save(version);
        objeto.setIdVersion(version);
        this.objetoRepository.save(objeto);
    }
}
