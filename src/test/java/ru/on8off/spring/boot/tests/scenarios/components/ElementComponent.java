package ru.on8off.spring.boot.tests.scenarios.components;

import ru.on8off.spring.boot.tests.controller.dto.ElementDto;
import ru.on8off.spring.boot.tests.fixture.ElementFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.on8off.spring.boot.tests.repository.ElementRepository;

@Component
public class ElementComponent {
    @Autowired
    private ElementRepository elementRepository;

    public void createElements(){
            elementRepository.save(ElementDto.fromDto(ElementFixture.generateElement()));
            elementRepository.save(ElementDto.fromDto(ElementFixture.generateElement()));
    }
}
