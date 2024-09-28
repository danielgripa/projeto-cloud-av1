package com.example.projeto_av1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_av1.repository.CartaoRepository;
import com.example.projeto_av1.model.Cartao;

import java.util.Optional;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    // Buscar todos os cartões
    public List<Cartao> getAllCartoes() {
        return cartaoRepository.findAll();
    }

    // Buscar um cartão pelo ID
    public Optional<Cartao> getCartaoById(int id) {
        return cartaoRepository.findById(id);
    }

    // Criar um novo cartão
    public Cartao saveCartao(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    // Deletar um cartão pelo ID
    public void deleteCartao(int id) {
        cartaoRepository.deleteById(id);
    }
}
