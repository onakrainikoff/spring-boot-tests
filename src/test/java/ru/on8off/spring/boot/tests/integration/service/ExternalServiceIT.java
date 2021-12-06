package ru.on8off.spring.boot.tests.integration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;
import ru.on8off.spring.boot.tests.controller.dto.ElementDto;
import ru.on8off.spring.boot.tests.repository.ElementRepository;
import ru.on8off.spring.boot.tests.repository.entity.Element;
import ru.on8off.spring.boot.tests.service.ElementService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest
public class ExternalServiceIT {
    @Autowired
    private ElementService elementService;
    @MockBean
    private ElementRepository mockElementRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${external.url}")
    private String externalUrl;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetExternalElement() throws URISyntaxException, JsonProcessingException {
        mockServer.expect(ExpectedCount.once(), MockRestRequestMatchers.requestTo(new URI(externalUrl + "/999")))
                  .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                  .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                  .body(mapper.writeValueAsString(new ElementDto(999, "External"))));
        var element = elementService.getExternalElement(999);
        Assertions.assertNotNull(element);
        Assertions.assertEquals(999, element.getId());
        Assertions.assertEquals("External", element.getName());
    }

    @Test
    public void testGetAll(){
        Mockito.when(mockElementRepository.findAll()).thenReturn(List.of(new Element(1, "Internal")));
        var result = elementService.getAll();
        Assertions.assertEquals(1, result.size());
    }
}
