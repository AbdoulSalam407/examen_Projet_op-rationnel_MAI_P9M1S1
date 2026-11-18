package sn.unchk.cours.controller;

import jakarta.validation.Valid;
import sn.unchk.cours.DTO.CreateClientDTO;
import sn.unchk.cours.entity.Client;
import sn.unchk.cours.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody CreateClientDTO request) {
        Client client = new Client();
        client.setFirstName(request.firstName());
        client.setLastName(request.lastName());
        client.setEmail(request.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody CreateClientDTO request) {
        try {
            Client client = new Client();
            client.setFirstName(request.firstName());
            client.setLastName(request.lastName());
            client.setEmail(request.email());
            return ResponseEntity.ok(clientService.update(id, client));
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
