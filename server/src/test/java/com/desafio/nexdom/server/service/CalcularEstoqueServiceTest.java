package com.desafio.nexdom.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.nexdom.server.enums.TipoMovimentacao;
import com.desafio.nexdom.server.exceptions.ValorNumericoInvalidoException;

public class CalcularEstoqueServiceTest {
    
    @Autowired
    @InjectMocks
    private CalcularEstoqueService calcularEstoqueService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Quando o produto tem estoque disponível suficiente")
    void calcularEstoqueComSaldoDisponivel() {

        int retorno = calcularEstoqueService.calcularEstoque(10, 1, TipoMovimentacao.ENTRADA.getDescricao());

        assertEquals(11, retorno);
    }

    @Test
    @DisplayName("Quando o produto tem estoque disponível suficiente")
    void calcularEstoqueComSaldoIndisponivel() {

        Exception thrown = assertThrows(ValorNumericoInvalidoException.class, () -> calcularEstoqueService.calcularEstoque(10, 11, TipoMovimentacao.SAIDA.getDescricao()));

        assertEquals(thrown.getMessage(), "Quantidade em estoque insuficiente");
    }

}
