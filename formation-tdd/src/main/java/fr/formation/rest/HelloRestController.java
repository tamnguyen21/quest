package fr.formation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOHello;

@RestController
public class HelloRestController {
    @Autowired
    private IDAOHello dao;

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/api/hello/all")
    public String findAll() {
        this.dao.findAll();
        return "";
    }
}
