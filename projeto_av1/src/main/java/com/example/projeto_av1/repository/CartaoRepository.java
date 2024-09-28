package com.example.projeto_av1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projeto_av1.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
    // Adicione consultas específicas aqui, se necessário
}
