package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Taille;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TailleRepository extends CrudRepository<Taille, Integer> {
    List<Taille> findAll();
    Taille findByIdtai(int idtai);
}
