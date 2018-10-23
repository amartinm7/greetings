package com.amm.rest.greetings.controller;

import com.amm.rest.greetings.model.Greetings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingsControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        Greetings expected = new Greetings(1L,"Hello World!");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                Greetings.class)).isEqualToComparingOnlyGivenFields(expected,"content");
    }

    @Test
    public void greetingShouldReturnTheMessage() throws Exception {
        Greetings expected = new Greetings(1L,"Hello World!");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greetings",
                Greetings.class)).isEqualToComparingOnlyGivenFields(expected,"id","content");
    }

}
