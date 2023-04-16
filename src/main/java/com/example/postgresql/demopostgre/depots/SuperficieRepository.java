package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Superficie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuperficieRepository extends CrudRepository<Superficie, Integer> {
    List<Superficie> findAll();
}
