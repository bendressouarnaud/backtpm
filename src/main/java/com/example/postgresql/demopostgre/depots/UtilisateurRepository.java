package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    List<Utilisateur> findAll();
    List<Utilisateur> findByRoles(String roles);
    Utilisateur findByIdusr(int idusr);
    Utilisateur findByNom(String nom);
    Utilisateur findByIdentifiant(String identifiant);
    Utilisateur findByIdentifiantAndPassword(String identifiant, String password);
    Utilisateur findByIdentifiantAndPasswordAndActif(String identifiant, String password, int actif);
    Utilisateur findByIdentifiantAndPasswordAndRoles(String identifiant,
                                                     String password, String roles);
    Utilisateur findByIdentifiantAndPasswordAndRolesAndActif(String identifiant,
        String password, String roles, int actif);
    //List<Utilisateur> findByIdIn(String name);
    List<Utilisateur> findByRolesIn(List<String> roles);
}