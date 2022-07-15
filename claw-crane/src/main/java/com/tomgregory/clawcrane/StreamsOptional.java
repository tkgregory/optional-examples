package com.tomgregory.clawcrane;

import java.util.Arrays;
import java.util.Optional;

public class StreamsOptional {
    public static void main(String[] args) {
        Optional<Integer> match = Arrays.stream(new Integer[]{1, 2, 3, 4, 5}).filter(it -> it == 6).findFirst();

        System.out.println(match.orElse(-1));
        System.out.println(match.flatMap(it -> Optional.of(it * 2)));
        match.ifPresentOrElse(System.out::println, () -> System.out.println("Not present. Sorry!"));
    }
}
