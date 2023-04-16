package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Hopitaux;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HopitauxRepository extends CrudRepository<Hopitaux, Integer> {
    List<Hopitaux> findAll();
}
