package ru.on8off.spring.boot.tests.scenarios.steps;

import ru.on8off.spring.boot.tests.controller.dto.ElementDto;
import ru.on8off.spring.boot.tests.scenarios.components.ElementComponent;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ElementSteps {
    @LocalServerPort
    private int port;
    private String host = "http://localhost:";
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ElementComponent elementComponent;
    private static ResponseEntity<ElementDto[]> lastResponse = null;


    @Дано("^тестовые элементы созданы$")
    @Given("^test elements created$")
    public void test_elements_created() throws Throwable {
        log.info("test elements created");
        elementComponent.createElements();
    }

    @Когда("^клиент вызывает получение всех элементов$")
    @When("^the client calls get all elements$")
    public void the_client_gets_all_elements() throws Throwable {
        log.info("the client calls get all elements");
        var response = restTemplate.getForEntity(host + port + "/", ElementDto[].class);
        log.info("response: {}", response);
        lastResponse = response;
    }

    @Тогда("^клиент получает в ответ код (\\d+)$")
    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        log.info("the client receives status code of {}", statusCode);
        final HttpStatus currentStatusCode = lastResponse.getStatusCode();
        MatcherAssert.assertThat("status code is incorrect : " + lastResponse.getBody(), currentStatusCode.value(), Matchers.is(statusCode));
    }


}
