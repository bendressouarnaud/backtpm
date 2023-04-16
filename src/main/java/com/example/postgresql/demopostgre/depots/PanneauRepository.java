package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Panneau;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PanneauRepository extends CrudRepository<Panneau, Integer> {
    List<Panneau> findAll();
    Panneau findByIdpan(@Param("idpan") int idpan);
    List<Panneau> findAllByIdsec(@Param("idsec") int idsec);
    Panneau findByDatecreationAndHeurecreationAndDateheure(Date datecreation,
        String heurecreation, String dateheure);
    Panneau findByDatecreationAndHeurecreationAndDateheureAndIdusr(Date datecreation,
        String heurecreation, String dateheure, int Idusr);

    List<Panneau> findAllByDatecreation(String datecreation);
    List<Panneau> findAllByIdpanBetween(int debut,int fin);

    //List<Panneau> findTopByOrderByIdpanDesc(@Param("total") int total);

    List<Panneau> findTop5ByIdsecOrderByDatecreationDesc(@Param("idsec") int idsec);
    //List<Panneau> findTop5OrderByIdpanAsc();
    // findTop10ByLastnameOrderByFirstnameAsc(String lastname);

    // Delete :
    @Transactional
    void deleteByIdpan(int idpan);
}
