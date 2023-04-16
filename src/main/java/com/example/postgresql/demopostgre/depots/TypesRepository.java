package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Types;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypesRepository extends CrudRepository<Types, Integer> {
    List<Types> findAll();
    List<Types> findAllByOrderByLibelleAsc();
    Types findByIdtyp(int idtyp);
}
