package com.tomgregory.optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SerializationTest {
    @AllArgsConstructor
    @Getter
    private static final class Guitarist implements Serializable {
        private String lastName;
        private Optional<Integer> ageAtDeath;
    }

    @Test
    public void throwsNotSerializableException() throws IOException {
        Guitarist guitarist = new Guitarist("Hendrix", Optional.of(27));

        NotSerializableException exception = assertThrows(
                NotSerializableException.class,
                () -> new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(guitarist)
        );

        assertEquals("java.util.Optional", exception.getMessage());
    }
}
