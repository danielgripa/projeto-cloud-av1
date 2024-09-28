package com.example.projeto_av1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projeto_av1.model.Transacao;
import com.example.projeto_av1.model.Cartao;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    // Buscar transações feitas por um cartão em um intervalo de tempo
    List<Transacao> findByCartaoAndDataTransacaoBetween(Cartao cartao, LocalDateTime start, LocalDateTime end);
}
