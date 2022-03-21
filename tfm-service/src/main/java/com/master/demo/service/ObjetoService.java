package com.master.demo.service;

import com.example.models.ObjectDTO;
import com.example.models.ObjectResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ObjetoService {
    List<ObjectResponseDTO> getAllObjects();

    void createObject(String nombre);

    ObjectResponseDTO getObjectById(Integer objectId);

    ObjectResponseDTO getObjectByIdVersion(Integer versionId);
}
