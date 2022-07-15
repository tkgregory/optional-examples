package com.tomgregory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {
    @AllArgsConstructor
    @Getter
    private static final class Guitarist implements Serializable {
        private String lastName;
        private Optional<Integer> ageAtDeath;
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
}
