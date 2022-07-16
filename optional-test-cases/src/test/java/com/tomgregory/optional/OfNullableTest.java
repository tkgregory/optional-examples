package com.tomgregory.optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfNullableTest {
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
    @Builder
    @AllArgsConstructor
    private static final class Guitarist {
        private String lastName;
        private Integer ageAtDeath;
    }

    @Test
    public void ofNullableWithValue() {
        Guitarist guitarist = Guitarist.builder().lastName("Hendrix").ageAtDeath(27).build();

        Optional.ofNullable(guitarist.getAgeAtDeath()).ifPresentOrElse(age -> System.out.println("Died at age " + age), () -> System.out.println("Still alive!"));

        assertEquals("Died at age 27", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ofNullableWithNull() {
        Guitarist guitarist = Guitarist.builder().lastName("Knopfler").build();

        Optional.ofNullable(guitarist.getAgeAtDeath()).ifPresentOrElse(age -> System.out.println("Died at age " + age), () -> System.out.println("Still alive!"));

        assertEquals("Still alive!", outputStreamCaptor.toString().trim());
    }
}
