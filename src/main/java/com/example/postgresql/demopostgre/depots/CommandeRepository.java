package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Commande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {
    List<Commande> findAll();
    Commande findByIdcom(@Param("idcom") int idcom);
    Commande findByIdpan(@Param("idpan") int idpan);
    //long deleteByIdcom(int idcom);

    @Transactional
    void deleteByIdcom(int idcom);
}
