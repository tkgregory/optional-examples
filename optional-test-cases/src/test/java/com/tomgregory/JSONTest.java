package com.tomgregory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {
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

    @AllArgsConstructor
    @Getter
    private static final class Guitarist implements Serializable {
        private String lastName;
        private Optional<Integer> ageAtDeath;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static final class BetterGuitarist implements Serializable {
        private String lastName;
        private Integer ageAtDeath;
    }

    @Test
    public void serializesOptionalToJSONBadly() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(new Guitarist("Knopfler", Optional.empty()));

        assertEquals("{\"lastName\":\"Knopfler\",\"ageAtDeath\":{\"empty\":true,\"present\":false}}", json);
    }

    @Test
    public void serializesOptionalWithValueToJSONUsingJDK8Module() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());

        String json = objectMapper.writeValueAsString(new Guitarist("Cobain", Optional.of(27)));

        assertEquals("{\"lastName\":\"Cobain\",\"ageAtDeath\":27}", json);
    }

    @Test
    public void serializesEmptyOptionalToJSONUsingJDK8Module() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());

        String json = objectMapper.writeValueAsString(new Guitarist("Knopfler", Optional.empty()));

        assertEquals("{\"lastName\":\"Knopfler\",\"ageAtDeath\":null}", json);
    }

    @Test
    public void serializesToJSONUsingNonOptionalClassVariable() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(BetterGuitarist.builder().lastName("Knopfler").build());

        assertEquals("{\"lastName\":\"Knopfler\",\"ageAtDeath\":null}", json);
    }

    @Test
    public void ofNullableWithValue() {
        BetterGuitarist guitarist = BetterGuitarist.builder().lastName("Hendrix").ageAtDeath(27).build();

        Optional.ofNullable(guitarist.getAgeAtDeath()).ifPresentOrElse(age -> System.out.println("Died at age " + age), () -> System.out.println("Still alive!"));

        assertEquals("Died at age 27", outputStreamCaptor.toString().trim());
    }

    @Test
    public void ofNullableWithNull() {
        BetterGuitarist guitarist = BetterGuitarist.builder().lastName("Knopfler").build();

        Optional.ofNullable(guitarist.getAgeAtDeath()).ifPresentOrElse(age -> System.out.println("Died at age " + age), () -> System.out.println("Still alive!"));

        assertEquals("Still alive!", outputStreamCaptor.toString().trim());
    }
}
