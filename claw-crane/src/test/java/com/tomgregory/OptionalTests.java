package com.tomgregory;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTests {

    @Test
    public void testPresent() {
        Optional<String> emptyOptional = Optional.of("I am a value");
        assertTrue(emptyOptional.isPresent());
    }

    @Test
    public void testEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        assertTrue(emptyOptional.isEmpty());
    }

    @Test
    public void testOfNullableNotEmpty() {
        Optional<String> emptyOptional = Optional.ofNullable("Jamie");
        assertEquals("Jamie", emptyOptional.orElse(""));
    }

    @Test
    public void testOfNullableEmpty() {
        Optional<String> emptyOptional = Optional.ofNullable(null);
        assertTrue(emptyOptional.isEmpty());
    }


    @Test
    public void testOrElse() {
        Optional<String> emptyOptional = Optional.empty();
        assertEquals("Trevor", emptyOptional.orElse("Trevor"));
    }

    @Test
    public void testOrElseGet() {
        Optional<String> emptyOptional = Optional.empty();
        assertEquals("Sammy", emptyOptional.orElseGet(() -> "Sammy"));
    }
}
