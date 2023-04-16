package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Requetepanneau;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequetepanneauRepository extends CrudRepository<Requetepanneau, Integer> {
    List<Requetepanneau> findAll();
    Requetepanneau findByIdrqtpn(@Param("idrqtpn") int idrqtpn);
    Requetepanneau findByIdpanAndEtat(int idpan, int etat);
    List<Requetepanneau> findAllByIduserAndEtat(int iduser, int etat);
}
