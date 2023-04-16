package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Quartier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuartierRepository extends CrudRepository<Quartier, Integer> {
    List<Quartier> findAll();
    Quartier findByIdqua(@Param("idqua") int idqua);
    List<Quartier> findAllByIdvill(@Param("idvill") int idvill);
    List<Quartier> findAllByIdvillOrderByLibelleAsc(@Param("idvill") int idvill);
    List<Quartier> findAllByOrderByLibelleAsc();
    Quartier findByLibelleAndIdvill(String libelle,int idvill);
}
