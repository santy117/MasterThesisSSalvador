package com.master.demo.controller;

import com.master.demo.Entities.HelloWorld;
import com.master.demo.Repositories.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController{
    @Autowired
    private HelloWorldRepository helloWorldRepository;

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

}
