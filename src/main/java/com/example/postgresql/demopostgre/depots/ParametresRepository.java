package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Parametres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParametresRepository  extends CrudRepository<Parametres, Integer> {

    Parametres findByIdpar(@Param("idpar") int idpar);

}
