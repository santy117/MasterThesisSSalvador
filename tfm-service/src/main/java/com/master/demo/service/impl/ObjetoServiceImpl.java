package com.master.demo.service.impl;

import com.example.models.ObjectResponseDTO;
import com.master.demo.Entities.Objeto;
import com.master.demo.Entities.Version;
import com.master.demo.Repositories.ObjetoRepository;
import com.master.demo.Repositories.VersionRepository;
import com.master.demo.service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<ObjectResponseDTO> getAllObjects() {
        List<ObjectResponseDTO> objetosResponse = new ArrayList<>();
        List<Objeto> objetos = this.objetoRepository.findAll();
        objetos.forEach(objeto -> {
            ObjectResponseDTO itemResponse = new ObjectResponseDTO();
            itemResponse.setIdObjeto(objeto.getIdObjeto());
            itemResponse.setIdVersion(objeto.getIdVersion().getIdVersion());
            itemResponse.setNombre(objeto.getNombre());
            objetosResponse.add(itemResponse);
        });
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
        return;
    }

    @Override
    public ObjectResponseDTO getObjectById(Integer objectId) {
        Optional<Objeto> objeto = this.objetoRepository.findById(objectId);
        ObjectResponseDTO objectResponseDTO = new ObjectResponseDTO();
        objectResponseDTO.setIdObjeto(objeto.get().getIdObjeto());
        objectResponseDTO.setIdVersion(objeto.get().getIdVersion().getIdVersion());
        objectResponseDTO.setNombre(objeto.get().getNombre());
        return objectResponseDTO;
    }

    @Override
    public ObjectResponseDTO getObjectByIdVersion(Integer versionId) {
        Objeto objeto = this.objetoRepository.findByIdVersion(versionId);
        ObjectResponseDTO objectResponseDTO = new ObjectResponseDTO();
        objectResponseDTO.setIdObjeto(objeto.getIdObjeto());
        objectResponseDTO.setIdVersion(objeto.getIdVersion().getIdVersion());
        objectResponseDTO.setNombre(objeto.getNombre());
        return objectResponseDTO;
    }

    @Override
    @Transactional
    public void createVersion(Integer objectId) {
        Version version = new Version();
        version.setIdObjeto(objectId);
        version = this.versionRepository.save(version);
        this.objetoRepository.updateVersion(version.getIdObjeto(), version.getIdVersion());
        return;

    }
}
