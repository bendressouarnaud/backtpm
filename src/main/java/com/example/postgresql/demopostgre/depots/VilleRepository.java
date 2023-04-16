package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Ville;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VilleRepository  extends CrudRepository<Ville, Integer> {
    List<Ville> findAll();
    Ville findByIdvil(int idvil);
    List<Ville> findAllByOrderByLibelleAsc();

    // Get back one :
    List<Ville> findAllByIdvil(int idvil);
}
