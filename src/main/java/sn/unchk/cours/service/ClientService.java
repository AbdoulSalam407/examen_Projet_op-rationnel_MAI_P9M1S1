package sn.unchk.cours.service;

import sn.unchk.cours.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Client save(Client client);
    Client update(Long id, Client client);
    void deleteById(Long id);
}
