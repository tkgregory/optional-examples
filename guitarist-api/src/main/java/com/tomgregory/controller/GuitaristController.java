package com.tomgregory.controller;

import com.tomgregory.model.Guitarist;
import com.tomgregory.service.GuitaristService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class GuitaristController {
    private final GuitaristService guitaristService;

    public GuitaristController(GuitaristService guitaristService) {
        this.guitaristService = guitaristService;
    }

    @GetMapping(value = "/guitarist/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Guitarist getGuitarist(@PathVariable String lastName) {
        Guitarist guitarist = guitaristService.findByLastName(lastName);
        if (guitarist.getSignatureSong().equals("Stairway to heaven")) {
            // increment cheese factor
        }
        return guitarist;
    }
}
