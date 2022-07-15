package com.tomgregory.service;

import com.tomgregory.model.Guitarist;
import com.tomgregory.repository.GuitaristRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuitaristService {
    private final GuitaristRepository guitaristRepository;

    public GuitaristService(GuitaristRepository guitaristRepository) {
        this.guitaristRepository = guitaristRepository;
    }

    public Guitarist findByLastName(String lastName) {
        return null;
    }

    public Iterable<Guitarist> findAll() {
        return guitaristRepository.findAll();
    }
}
