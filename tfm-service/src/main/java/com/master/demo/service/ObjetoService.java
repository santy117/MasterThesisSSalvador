package com.master.demo.service;

import com.example.models.ObjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ObjetoService {
    List<ObjectDTO> getAllObjects();

    void createObject(String nombre);
}
