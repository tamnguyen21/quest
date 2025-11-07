package fr.formation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOHello;
import fr.formation.model.Hello;

@RestController
public class HelloRestController {
    @Autowired
    private IDAOHello dao;

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/api/hello/all")
    public List<Hello> findAll() {
        List<Hello> v = this.dao.findAll();
        return v;
    }
}
