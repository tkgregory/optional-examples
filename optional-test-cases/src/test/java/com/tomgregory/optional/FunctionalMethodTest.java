package com.tomgregory.optional;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ConstantConditions")
public class FunctionalMethodTest {

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
    public void testOfNullableWhenPresent() {
        Optional<String> emptyOptional = Optional.ofNullable("Jamie");
        assertEquals("Jamie", emptyOptional.orElse(""));
    }

    @Test
    public void testOfNullableWhenEmpty() {
        Optional<String> emptyOptional = Optional.ofNullable(null);
        assertTrue(emptyOptional.isEmpty());
    }

    @Test
    public void orElseWhenPresent() {
        Optional<String> presentOptional = Optional.of("Jimmy");
        assertEquals("Jimmy", presentOptional.orElse("Trevor"));
    }

    @Test
    public void orElseWhenEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        assertEquals("Trevor", emptyOptional.orElse("Trevor"));
    }

    @Test
    public void orElseGetWhenPresent() {
        Optional<String> presentOptional = Optional.of("Jake");
        assertEquals("Jake", presentOptional.orElseGet(this::generateSubstituteName));
    }

    @Test
    public void orElseGetWhenEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        assertEquals("Karen", emptyOptional.orElseGet(this::generateSubstituteName));
    }

    private String generateSubstituteName() {
        return "Karen";
    }

    @Test
    public void orElseThrowWhenPresent() {
        Optional<String> presentOptional = Optional.of("Jimmy");
        assertDoesNotThrow(() -> presentOptional.orElseThrow());
    }

    @Test
    public void orElseThrowWhenEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        assertThrows(NoSuchElementException.class, emptyOptional::orElseThrow);
    }

    @Test
    public void orElseThrowWithSupplierWhenPresent() {
        Optional<String> presentOptional = Optional.of("Jimmy");
        assertDoesNotThrow(
                () -> presentOptional.orElseThrow(() -> new RuntimeException("Oops"))
        );
    }

    @Test
    public void orElseThrowWithSupplierWhenEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        assertThrows(RuntimeException.class,
                () -> emptyOptional.orElseThrow(() -> new RuntimeException("Oops"))
        );
    }

    @Test
    public void orWhenPresent() {
        Optional<String> presentOptional = Optional.of("Jimmy");
        Optional<String> substituteOptional = Optional.of("Jake");

        assertEquals(presentOptional, presentOptional.or(() -> substituteOptional));
    }

    @Test
    public void orWhenEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        Optional<String> substituteOptional = Optional.of("Jake");

        assertEquals(substituteOptional, emptyOptional.or(() -> substituteOptional));
    }
}
