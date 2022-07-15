package com.tomgregory.clawcrane;

import java.util.Optional;

public class Arcade {
    public static void main(String[] args) {
        int patienceLevel = 5;
        ClawCraneGame clawCraneGame = new ClawCraneGame();

        Optional<CuddlyToy> cuddlyToyOptional;
        do {
            System.out.println("Playing game");
            cuddlyToyOptional = clawCraneGame.play();
            patienceLevel--;
        }
        while (cuddlyToyOptional.isEmpty() && patienceLevel > 0);

        cuddlyToyOptional.ifPresent(CuddlyToy::squeeze);
        cuddlyToyOptional.ifPresent(toy -> new Son().give(toy));
    }
}