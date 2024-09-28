package com.example.projeto_av1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_av1.repository.ClienteRepository;
import com.example.projeto_av1.model.Cliente;

import java.util.Optional;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Buscar todos os clientes
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Buscar um cliente pelo ID
    public Optional<Cliente> getClienteById(int id) {
        return clienteRepository.findById(id);
    }

    // Criar um novo cliente
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Deletar um cliente pelo ID
    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }
}
