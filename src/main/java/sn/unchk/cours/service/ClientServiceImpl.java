package sn.unchk.cours.service;

import sn.unchk.cours.entity.Client;
import sn.unchk.cours.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found: " + id));
        existing.setFirstName(client.getFirstName());
        existing.setLastName(client.getLastName());
        existing.setEmail(client.getEmail());
        return clientRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found: " + id);
        }
        clientRepository.deleteById(id);
    }
}
