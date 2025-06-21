package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.model.Cliente;

@Repository
public class ClienteRepository implements GenericRepository<Cliente, String> {
    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public Cliente save(Cliente cliente) {
        var id = cliente.id() != null ? cliente.id() : UUID.randomUUID().toString();
        var savedCliente = new Cliente(id, cliente.nombre(), cliente.email(), cliente.edad(), cliente.tipoCliente());
        clientes.add(savedCliente);
        return savedCliente;
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return clientes.stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(clientes);
    }

    @Override
    public void update(Cliente cliente, String id) {
        clientes.removeIf(c -> c.id().equals(id));
        var updatedCliente = new Cliente(id, cliente.nombre(), cliente.email(), cliente.edad(), cliente.tipoCliente());
        clientes.add(updatedCliente);
    }

    @Override
    public void deleteById(String id) {
        clientes.removeIf(c -> c.id().equals(id));
    }
}
