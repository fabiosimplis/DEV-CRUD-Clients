package br.com.dev.cliente.controllers;

import br.com.dev.cliente.dto.ClientDTO;
import br.com.dev.cliente.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClientService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        ClientDTO dto = service.getClientById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> getList(Pageable pageable){
         Page<ClientDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
}
