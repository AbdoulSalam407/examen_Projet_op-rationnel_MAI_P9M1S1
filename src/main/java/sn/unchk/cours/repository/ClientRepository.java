package sn.unchk.cours.repository;

import sn.unchk.cours.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
