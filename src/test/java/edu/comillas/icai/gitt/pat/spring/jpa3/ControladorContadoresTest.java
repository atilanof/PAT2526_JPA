package edu.comillas.icai.gitt.pat.spring.jpa3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ControladorContadoresTest {
    private final TestRestTemplate restTemplate = new TestRestTemplate("admin@e.m", "admin");

    @Test
    public void contadorNoExistenteTest() {
        // When ...
        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:8080/api/contadores/no-existo",
                HttpMethod.GET, HttpEntity.EMPTY, Void.class);
        // Then ...
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

