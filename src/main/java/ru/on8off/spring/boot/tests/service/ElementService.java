package ru.on8off.spring.boot.tests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.on8off.spring.boot.tests.controller.dto.ElementDto;
import ru.on8off.spring.boot.tests.repository.ElementRepository;
import ru.on8off.spring.boot.tests.repository.entity.Element;

import java.util.List;

@Service
public class ElementService {
    @Value("${external.url}")
    private String externalUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ElementRepository elementRepository;

    @Transactional(readOnly = true)
    public List<Element> getAll(){
        return elementRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Element get(Integer id){
        return elementRepository.getById(id);
    }

    @Transactional
    public Element save(Element element){
        return elementRepository.save(element);
    }

    @Transactional
    public void delete(Integer id){
        elementRepository.deleteById(id);
    }

    public ElementDto getExternalElement(Integer id){
        return restTemplate.getForObject(externalUrl + "/" + id, ElementDto.class);
    }
}
