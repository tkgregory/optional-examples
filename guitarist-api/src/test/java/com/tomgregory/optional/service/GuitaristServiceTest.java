package com.tomgregory.optional.service;

import com.tomgregory.optional.model.Guitarist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuitaristServiceTest {
    private GuitaristService guitaristService = new GuitaristService();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void ifPresent() {
        Optional<Guitarist> lookupResult = guitaristService.findByLastName("Hendrix"); // returns Optional with value
        lookupResult.ifPresent(guitarist -> System.out.println(guitarist.getSignatureSong()));

        assertEquals("Purple Haze", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ifPresentOrElse() {
        Optional<Guitarist> lookupResult = guitaristService.findByLastName("Page"); // returns empty Optional
        lookupResult.ifPresentOrElse(guitarist -> System.out.println(guitarist.getSignatureSong()), () -> System.out.println("Guitarist not found!"));

        assertEquals("Guitarist not found!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void orElse() {
        Guitarist defaultGuitarist = new Guitarist("Ed", "Sheeran", "The A team");
        Guitarist guitarist = guitaristService.findByLastName("Page").orElse(defaultGuitarist); // returns empty Optional
        System.out.println("Recommended listening: " + guitarist.getSignatureSong());

        assertEquals("Recommended listening: The A team", outputStreamCaptor.toString().trim());
    }
}
