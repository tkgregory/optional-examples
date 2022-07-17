package com.tomgregory.optional.service;

import com.tomgregory.optional.model.Guitarist;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuitaristService {
    public Optional<Guitarist> findByLastName(String lastName) {
        if (lastName.equalsIgnoreCase("Hendrix")) {
            return Optional.of(new Guitarist("Jimi", "Hendrix", "Purple Haze"));
        }
        return Optional.empty();
    }
}
