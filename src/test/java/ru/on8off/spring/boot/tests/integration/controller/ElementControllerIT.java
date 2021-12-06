package ru.on8off.spring.boot.tests.integration.controller;

import ru.on8off.spring.boot.tests.controller.dto.ElementDto;
import ru.on8off.spring.boot.tests.fixture.ElementFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.on8off.spring.boot.tests.repository.ElementRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ElementControllerIT {
    @LocalServerPort
    private int port;
    private String host = "http://localhost:";
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private ElementRepository elementRepository;

    @Test
    void get() {
        var elementSource = ElementFixture.generateElement();
        var element = testRestTemplate.postForObject(host + port +"/", elementSource, ElementDto.class);
        Assertions.assertNotNull(element.getId());
        Assertions.assertEquals(elementSource.getName(), element.getName());

        var list = testRestTemplate.getForObject(host + port +"/", ElementDto[].class);
        Assertions.assertEquals(1, list.length);

        var elementSingle = testRestTemplate.getForObject(host + port +"/{id}", ElementDto.class, element.getId());
        Assertions.assertEquals(element.getId(), elementSingle.getId());
        Assertions.assertEquals(element.getName(), elementSingle.getName());

        testRestTemplate.delete(host + port +"/{id}", element.getId());
        list = testRestTemplate.getForObject(host + port +"/", ElementDto[].class);
        Assertions.assertEquals(0, list.length);
    }

}