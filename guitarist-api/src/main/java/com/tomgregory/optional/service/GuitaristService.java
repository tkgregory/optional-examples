package com.tomgregory.optional.service;

import com.tomgregory.optional.model.Guitarist;
import com.tomgregory.optional.repository.GuitaristRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuitaristService {
    private final GuitaristRepository guitaristRepository;

    public GuitaristService(GuitaristRepository guitaristRepository) {
        this.guitaristRepository = guitaristRepository;
    }

    public Optional<Guitarist> findByLastName(String lastName) {
        return guitaristRepository.findByLastNameIgnoreCase(lastName);
    }
}
