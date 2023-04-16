package com.example.postgresql.demopostgre.depots;

import com.example.postgresql.demopostgre.beans.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> findAll();
    Client findByIdcli(int idcli);
}
