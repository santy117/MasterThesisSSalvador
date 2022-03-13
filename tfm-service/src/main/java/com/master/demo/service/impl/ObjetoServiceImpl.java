package com.master.demo.service.impl;

import com.example.models.ObjectDTO;
import com.master.demo.Entities.Objeto;
import com.master.demo.Repositories.HelloWorldRepository;
import com.master.demo.Repositories.ObjetoRepository;
import com.master.demo.service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjetoServiceImpl implements ObjetoService {

    private HelloWorldRepository helloWorldRepository;
    private ObjetoRepository objetoRepository;

    @Autowired
    public ObjetoServiceImpl(final HelloWorldRepository helloWorldRepository, final ObjetoRepository objetoRepository){
        this.helloWorldRepository = helloWorldRepository;
        this.objetoRepository = objetoRepository;
    }

    @Override
    public List<ObjectDTO> getAllObjects() {
        List<ObjectDTO> objetosResponse = new ArrayList<ObjectDTO>();
        List<Objeto> objetos = this.objetoRepository.findAll();
        objetos.forEach(objeto -> {
            ObjectDTO itemResponse = new ObjectDTO();
            itemResponse.setIdObjeto(objeto.getIdObjeto());
            itemResponse.setIdVersion(objeto.getIdVersion());
            itemResponse.setNombre(objeto.getNombre());
            objetosResponse.add(itemResponse);
        });
        return objetosResponse;
    }

    @Override
    public void createObject(Integer idObjeto, Integer idVersion, String nombre) {
        Objeto objeto = new Objeto(idObjeto, idVersion, nombre);
        this.objetoRepository.save(objeto);
    }
}
