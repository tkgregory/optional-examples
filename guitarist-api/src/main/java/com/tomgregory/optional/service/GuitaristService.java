package com.tomgregory.optional.service;

import com.tomgregory.optional.model.Guitarist;
import com.tomgregory.optional.repository.GuitaristRepository;
import org.springframework.stereotype.Service;

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
