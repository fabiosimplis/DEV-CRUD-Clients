package br.com.dev.cliente.services;

import br.com.dev.cliente.dto.ClientDTO;
import br.com.dev.cliente.entity.Client;
import br.com.dev.cliente.repositories.ClientRepository;
import br.com.dev.cliente.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não foi encontrado"));
        return new ClientDTO(client);
    }
}
