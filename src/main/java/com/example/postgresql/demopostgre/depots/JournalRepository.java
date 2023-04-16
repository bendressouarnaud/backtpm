package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Journal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JournalRepository extends CrudRepository<Journal, Integer> {
    List<Journal> findAll();
    Journal findByIdjou(@Param("idjou") int idjou);
    List<Journal> findAllByIdusr(@Param("idusr") int idusr);
    List<Journal> findAllByIdusrAndDatesBetween(int idusr, Date debut,Date fin);
    List<Journal> findAllByDatesBetween(Date debut,Date fin);
}

