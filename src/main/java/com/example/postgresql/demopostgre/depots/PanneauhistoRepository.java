package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Panneauhisto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PanneauhistoRepository extends CrudRepository<Panneauhisto, Integer> {
    List<Panneauhisto> findAll();
    Panneauhisto findByIdphis(@Param("idphis") int idphis);
    List<Panneauhisto> findAllByIdpan(@Param("idpan") int idpan);

    @Transactional
    void deleteByIdphis(int idphis);
}
