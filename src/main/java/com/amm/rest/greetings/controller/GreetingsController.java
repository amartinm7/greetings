package com.amm.rest.greetings.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.amm.rest.greetings.model.Greetings;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingsController {

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
        return new ResponseEntity<Greetings>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            method= RequestMethod.GET,
            value = "/greetings",
            produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity<Greetings> getGreetings(@RequestParam(value="name", defaultValue="World") String name) {
        final Greetings greetings = new Greetings(1L, "Hello World!");
        return new ResponseEntity<Greetings>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            value = "/greetings",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Greetings> postGreetings(@RequestBody Greetings greetings) {
        System.out.println(greetings);
        return new ResponseEntity<Greetings>(greetings, HttpStatus.OK);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            value = "/doGreetings",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Greetings> postGreetings(@RequestBody String json) {
        System.out.println(json);
        try {
            HashMap<String, Object> map =
                    new ObjectMapper().readValue(json, HashMap.class);
            return new ResponseEntity<Greetings>(new Greetings(Long.valueOf((Integer)map.get("id")), (String)map.get("content")), HttpStatus.OK);
        }catch(IOException ex) {
            return new ResponseEntity<Greetings>(new Greetings(), HttpStatus.BAD_REQUEST);
        }
    }
}
