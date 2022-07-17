package com.tomgregory.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicMethodTest {
    @Test
    public void createGlassesOptionalWithValue() {
        Glasses glasses = new Glasses();
        Optional<Glasses> glassesOptional = Optional.of(glasses);

        assertTrue(glassesOptional.isPresent());
    }

    @Test
    public void createEmptyGlassesOptional() {
        Optional<Glasses> glassesOptional = Optional.empty();

        assertTrue(glassesOptional.isEmpty());
    }

    private static final class Glasses {
    }
}
