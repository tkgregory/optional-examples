package com.tomgregory.clawcrane;

import java.util.Optional;
import java.util.Random;

public class ClawCraneGame {
    private static final float CHANCE_OF_WINNING = 0.1f;
    private final Random random = new Random();

    public Optional<CuddlyToy> play() {
        if (random.nextFloat() < CHANCE_OF_WINNING) {
            return Optional.of(new CuddlyToy());
        }

        return Optional.empty();
    }
}