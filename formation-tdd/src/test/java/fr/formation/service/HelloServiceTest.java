package fr.formation.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.formation.dao.IDAOHello;
import fr.formation.model.Hello;

@ExtendWith(MockitoExtension.class)
public class HelloServiceTest {
    @Mock
    private IDAOHello dao;

    @InjectMocks
    private HelloService service;

    @Test
    void shouldCreateHelloWithMessage() {
        // given
        String message = "quelque chose";
        ArgumentCaptor<Hello> helloCaptor = ArgumentCaptor.captor();

        // when
        this.service.create(message);

        // then
        // Mockito.verify(this.dao).save(Mockito.any());
        // .capture permet de récupérer la valeur qui est envoyée dans le .save
        Mockito.verify(this.dao).save(helloCaptor.capture());

        // .getValue() permet de récupérer la valeur qui a été capturée
        Hello hello = helloCaptor.getValue();

        Assertions.assertEquals(message, hello.getMessage());
    }
}
