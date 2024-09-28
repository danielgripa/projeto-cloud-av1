package com.example.projeto_av1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_av1.repository.TransacaoRepository;
import com.example.projeto_av1.model.Transacao;
import com.example.projeto_av1.model.Cartao;
import com.example.projeto_av1.repository.CartaoRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    // Buscar todas as transações
    public List<Transacao> getAllTransacoes() {
        return transacaoRepository.findAll();
    }

    // Buscar uma transação pelo ID
    public Optional<Transacao> getTransacaoById(int id) {
        return transacaoRepository.findById(id);
    }

    // Criar uma nova transação
    public Transacao saveTransacao(Transacao transacao) throws Exception {
        // Buscar o cartão associado à transação
        Cartao cartao = transacao.getCartao();

        // 1. Verificar se o cartão está ativo
        if (!cartao.getEstaAtivado()) {
            throw new Exception("Cartão não está ativo.");
        }

        // 2. Verificar se o valor da transação não excede o limite do cartão
        if (transacao.getValor() > cartao.getSaldo()) {
            throw new Exception("Limite insuficiente.");
        }

        // 3. Verificar alta frequência de transações (mais de 3 transações em 2 minutos)
        LocalDateTime agora = transacao.getDataTransacao();
        LocalDateTime doisMinutosAtras = agora.minus(2, ChronoUnit.MINUTES);

        List<Transacao> transacoesRecentes = transacaoRepository
                .findByCartaoAndDataTransacaoBetween(cartao, doisMinutosAtras, agora);

        if (transacoesRecentes.size() >= 3) {
            throw new Exception("Alta frequência de transações em curto intervalo.");
        }

        // 4. Verificar transações duplicadas (mesmo valor e comerciante em 2 minutos)
        long transacoesDuplicadas = transacoesRecentes.stream()
                .filter(t -> t.getValor() == transacao.getValor() && t.getComerciante().equals(transacao.getComerciante()))
                .count();

        if (transacoesDuplicadas >= 2) {
            throw new Exception("Transação duplicada.");
        }

        // Atualizar o saldo do cartão
        cartao.setSaldo(cartao.getSaldo() - transacao.getValor());
        cartaoRepository.save(cartao);

        // Salvar a transação
        return transacaoRepository.save(transacao);
    }

    // Deletar uma transação pelo ID
    public void deleteTransacao(int id) {
        transacaoRepository.deleteById(id);
    }
}
