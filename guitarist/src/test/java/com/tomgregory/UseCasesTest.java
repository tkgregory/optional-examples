package com.tomgregory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseCasesTest {
    @AllArgsConstructor
    @Getter
    private static final class Guitarist implements Serializable {
        private String lastName;
        private Optional<Integer> ageAtDeath;
    }

    @Test
    public void throwsNotSerializableException() throws IOException {
        NotSerializableException exception = Assertions.assertThrows(NotSerializableException.class, () -> {
            new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new Guitarist("Hendrix", Optional.of(27)));
        });

        assertEquals("java.util.Optional", exception.getMessage());
    }
}
