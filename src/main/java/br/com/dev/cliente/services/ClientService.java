package br.com.dev.cliente.services;

import br.com.dev.cliente.dto.ClientDTO;
import br.com.dev.cliente.entity.Client;
import br.com.dev.cliente.repositories.ClientRepository;
import br.com.dev.cliente.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clients = repository.findAll(pageable);
        return clients.map(c -> new ClientDTO(c));

    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client client = new Client();
        copyDtoToEntity(dto, client);

        client = repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client client = repository.getReferenceById(id);
            copyDtoToEntity(dto, client);
            client = repository.save(client);
            return new ClientDTO(client);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }

    @Transactional
    public void delete(Long id){

        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Recurso não encontrado");
        else
            repository.deleteById(id);

    }
}
