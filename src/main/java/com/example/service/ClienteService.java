package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.Cliente;
import com.example.repository.ClienteRepository;

@Service
public class ClienteService extends GenericService<Cliente, String> {

    public ClienteService(ClienteRepository repository) {
        super(repository);
    }

    public double calcularDescuento(String id) {
        var cliente = getById(id);
        return switch (cliente.tipoCliente()) {
            case VIP -> 0.20; // 20% discount on loans
            case REGULAR -> 0.0; // No discount
        };
    }
}
