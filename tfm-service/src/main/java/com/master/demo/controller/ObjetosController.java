package com.master.demo.controller;


import com.example.api.ObjetosApi;
import com.example.models.ObjectDTO;
import com.master.demo.Entities.HelloWorld;
import com.master.demo.Repositories.HelloWorldRepository;
import com.master.demo.service.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ObjetosController implements ObjetosApi {
    @Autowired
    private HelloWorldRepository helloWorldRepository;

    @Autowired
    private ObjetoService objetoService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        HelloWorld n = new HelloWorld();
        n.setNombre(name);
        this.helloWorldRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<HelloWorld> getAllUsers() {
        // This returns a JSON or XML with the users
        return this.helloWorldRepository.findAll();
    }


    @Override
    public ResponseEntity<Void> createObject(ObjectDTO body) {
        this.objetoService.createObject(body.getNombre());
        return null;
    }

    @Override
    public ResponseEntity<List<ObjectDTO>> getAllObjects() {
        List<ObjectDTO> objectResponse = this.objetoService.getAllObjects();
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
        //return ResponseEntity.ok().body(this.objetoService.getAllObjects());
    }

}
