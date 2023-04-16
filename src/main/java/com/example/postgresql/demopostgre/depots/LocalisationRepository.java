package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Localisation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocalisationRepository extends CrudRepository<Localisation, Integer> {
    List<Localisation> findAll();
}