package com.tomgregory.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.tomgregory.model.Guitarist;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface GuitaristRepository extends CrudRepository<Guitarist, Integer> {
    Optional<Guitarist> findByLastName(String lastName);
}
