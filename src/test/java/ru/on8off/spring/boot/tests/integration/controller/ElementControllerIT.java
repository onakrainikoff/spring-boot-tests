package ru.on8off.spring.boot.tests.integration.controller;

import ru.on8off.spring.boot.tests.controller.dto.ElementDto;
import ru.on8off.spring.boot.tests.fixture.ProductFixture;
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
    private TestRestTemplate restTemplate;
    @Autowired
    private ElementRepository elementRepository;

    @Test
    void get() {
        var elementSource = ProductFixture.generateProduct();

        elementSource = ElementDto.toDto(elementRepository.save(ElementDto.fromDto(elementSource)));
        var productResult = restTemplate.getForObject(host + port +"/{id}", ElementDto.class, elementSource.getId());
        Assertions.assertEquals(elementSource.getId(), productResult.getId());
        Assertions.assertEquals(elementSource.getName(), productResult.getName());
    }

}