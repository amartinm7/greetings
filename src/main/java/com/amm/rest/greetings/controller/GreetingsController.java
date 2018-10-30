package com.amm.rest.greetings.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.amm.rest.greetings.model.Greetings;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600, origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class GreetingsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingsController.class);

    private static final String template = "Hello %s!";
    private final AtomicLong counter = new AtomicLong();

        @RequestMapping(
            method= RequestMethod.GET,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Greetings> defaultGreetings(@RequestParam(value="name", defaultValue="World") String name) {
        final Greetings greetings = new Greetings(counter.incrementAndGet(),
                String.format(template, name));
        LOGGER.info(String.format("%s", greetings.toString()));
        return new ResponseEntity<Greetings>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            method= RequestMethod.GET,
            value = "/greetings",
            produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity<Greetings> getGreetings(@RequestParam(value="name", defaultValue="World") String name) {
        final Greetings greetings = new Greetings(1L, "Hello World!");
        LOGGER.info(String.format("%s", greetings.toString()));
        return new ResponseEntity<Greetings>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            value = "/greetings",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Greetings> postGreetings(@RequestBody Greetings greetings) {
        LOGGER.info(String.format("%s", greetings.toString()));
        return new ResponseEntity<Greetings>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            value = "/doGreetings",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Greetings> postGreetings(@RequestBody String json) {
        LOGGER.info(String.format("%s", json));
        try {
            HashMap<String, Object> map =
                    new ObjectMapper().readValue(json, HashMap.class);
            final Greetings greetings = new Greetings(Long.valueOf((String)map.get("id")), (String)map.get("content"));
            LOGGER.info(String.format("%s", greetings.toString()));
            return new ResponseEntity<Greetings>(greetings, HttpStatus.OK);
        }catch(IOException ex) {
            final Greetings greetings = new Greetings(0L,"");
            LOGGER.info(String.format("%s", greetings.toString()));
            return new ResponseEntity<Greetings>(greetings, HttpStatus.BAD_REQUEST);
        }
    }
}
