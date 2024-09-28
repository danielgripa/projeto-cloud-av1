package com.example.projeto_av1.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull(message = "Data e hora da transação são obrigatórios")
    private LocalDateTime dataTransacao;

    @Column
    @NotNull(message = "Valor da transação é obrigatório")
    private double valor;

    @Column
    @NotBlank(message = "Informação sobre o comerciante é obrigatória")
    private String comerciante;

    // Relacionamento ManyToOne: Uma transação pertence a um cartão
    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;
}