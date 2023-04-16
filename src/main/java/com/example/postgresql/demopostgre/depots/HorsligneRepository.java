package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Horsligne;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HorsligneRepository extends CrudRepository<Horsligne, Integer> {

    List<Horsligne> findAll();
    Horsligne findByIdusr(int idusr);
}
