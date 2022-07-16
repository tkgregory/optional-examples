package com.tomgregory.optional;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvancedMethodTest {

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
    @EqualsAndHashCode
    @ToString
    private static final class Guitarist implements Serializable {
        private String name;
        private Integer ageAtDeath;
    }

    @Test
    public void filterTrueWhenPresent() {
        Optional<Guitarist> jimiHendrix = Optional.of(new Guitarist("Jimi Hendrix", 27));

        Optional<Guitarist> filteredOptional = jimiHendrix.filter(guitarist -> guitarist.getAgeAtDeath() < 30);

        assertEquals(jimiHendrix, filteredOptional);
    }

    @Test
    public void filterFalseWhenPresent() {
        Optional<Guitarist> peterGreen = Optional.of(new Guitarist("Peter Green", 73));

        Optional<Guitarist> filteredOptional = peterGreen.filter(guitarist -> guitarist.getAgeAtDeath() < 30);

        assertEquals(Optional.empty(), filteredOptional);
    }

    @Test
    public void filterWhenEmpty() {
        Optional<Guitarist> emptyGuitarist = Optional.empty();

        Optional<Guitarist> filteredOptional = emptyGuitarist.filter(guitarist -> guitarist.getAgeAtDeath() < 30);

        assertEquals(Optional.empty(), filteredOptional);
    }

    @Test
    public void mapWhenPresent() {
        Optional<Guitarist> jimiHendrix = Optional.of(new Guitarist("Jimi Hendrix", 27));
        assertEquals(Optional.of("Jimi Hendrix"), jimiHendrix.map(Guitarist::getName));
    }

    @Test
    public void mapWhenEmpty() {
        Optional<Guitarist> emptyGuitarist = Optional.empty();
        assertEquals(Optional.empty(), emptyGuitarist.map(Guitarist::getName));
    }

    @Test
    public void flatMapWhenPresent() {
        Optional<Guitarist> jimiHendrix = Optional.of(new Guitarist("Jimi Hendrix", 27));
        assertEquals(Optional.of("Jimi Hendrix"), jimiHendrix.flatMap(guitarist -> Optional.of(guitarist.getName())));
    }

    @Test
    public void flatMapWhenEmpty() {
        Optional<Guitarist> emptyGuitarist = Optional.empty();
        assertEquals(Optional.empty(), emptyGuitarist.flatMap(guitarist -> Optional.of(guitarist.getName())));
    }

    @Test
    public void stream() {
        GuitaristRepository guitaristRepository = new GuitaristRepository();
        List<String> guitaristsToFindByName = asList("Hendrix", "Sheeran", "Cobain");

        List<Guitarist> foundGuitarists = guitaristsToFindByName.stream()
                .map(guitaristRepository::findGuitaristByName)
                .flatMap(Optional::stream)
                .toList();

        assertEquals(asList(new Guitarist("Hendrix", 27), new Guitarist("Cobain", 27)), foundGuitarists);
    }

    private static final class GuitaristRepository {
        private static final Guitarist HENDRIX = new Guitarist("Hendrix", 27);
        private static final Guitarist COBAIN = new Guitarist("Cobain", 27);

        public Optional<Guitarist> findGuitaristByName(String name) {
            switch (name) {
                case "Hendrix":
                    return Optional.of(HENDRIX);
                case "Cobain":
                    return Optional.of(COBAIN);
                default:
                    return Optional.empty();
            }
        }
    }
}
