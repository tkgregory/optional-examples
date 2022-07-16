package com.tomgregory.optional.repository;

import com.tomgregory.optional.model.Guitarist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuitaristRepository extends CrudRepository<Guitarist, Integer> {
    Optional<Guitarist> findByLastName(String lastName);
}
