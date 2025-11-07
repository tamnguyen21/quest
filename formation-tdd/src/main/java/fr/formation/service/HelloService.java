package fr.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOHello;
import fr.formation.model.Hello;

@Service
public class HelloService {
    @Autowired
    private IDAOHello dao;

    public void create(String message) {
        Hello hello = new Hello();

        hello.setMessage(message);

        this.dao.save(hello);
    }
}
