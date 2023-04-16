package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Secteur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecteurRepository extends CrudRepository<Secteur, Integer> {
    List<Secteur> findAll();
    Secteur findByIdsec(@Param("idsec") int idsec);
    List<Secteur> findAllByIdquar(@Param("idquar") int idquar);
    List<Secteur> findAllByIdquarOrderByLibelleAsc(@Param("idquar") int idquar);
    // findAllByIdquarOrderByLibelleAsc

    Secteur findByLibelleAndIdquar(String libelle,int idquar);
}
