package com.tomgregory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IfPresentAndGetTest {
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

    @Getter
    @AllArgsConstructor
    private static final class Guitarist implements Serializable {
        private String firstName;
        private String lastName;
        private String signatureSong;
    }

    @Test
    public void canUseIfPresent() throws Exception {
        Optional<Guitarist> guitaristOptional = Optional.of(new Guitarist("Mark", "Knopfler", "Money For Nothing"));

        guitaristOptional.ifPresent(guitarist -> System.out.println(guitarist.getSignatureSong()));

        assertEquals("Money For Nothing", outputStreamCaptor.toString().trim());
    }

    @Test
    public void canUseIsPresentAntiPattern() throws Exception {
        Optional<Guitarist> guitaristOptional = Optional.of(new Guitarist("Mark", "Knopfler", "Money For Nothing"));

        if (guitaristOptional.isPresent()) {
            System.out.println(guitaristOptional.get().getSignatureSong());
        }

        assertEquals("Money For Nothing", outputStreamCaptor.toString().trim());
    }
}
