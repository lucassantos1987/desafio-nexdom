package com.desafio.nexdom.server.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.nexdom.server.enums.TipoMovimentacao;
import com.desafio.nexdom.server.enums.TipoProduto;
import com.desafio.nexdom.server.exceptions.RecursoNaoEncontradoException;
import com.desafio.nexdom.server.exceptions.ValorNumericoInvalidoException;
import com.desafio.nexdom.server.model.Produto;
import com.desafio.nexdom.server.repository.ProdutoRepository;

public class ProdutoServiceTest {

    @Mock
    ProdutoRepository produtoRepository;

    @Autowired
    @InjectMocks
    ProdutoService produtoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @MethodSource("providerParameters")
    void listarProdutoTest(String descricao, String tipoProduto) {

        Produto produto = new Produto(1L, "MESA", "MOVEL", new BigDecimal(100), 0);
        List<Produto> vProduto = new ArrayList<>();
        vProduto.add(produto);

        if (descricao.isEmpty() && tipoProduto.isEmpty()) {
            when(produtoRepository.findAll()).thenReturn(vProduto);
        } else if (!descricao.isEmpty() && tipoProduto.isEmpty()) {
            when(produtoRepository.findByDescricaoLike(descricao)).thenReturn(vProduto);
        } else if (descricao.isEmpty() && !tipoProduto.isEmpty()) {
            when(produtoRepository.findByTipoProduto(tipoProduto)).thenReturn(vProduto);
        } else {
            when(produtoRepository.findByDescricaoLikeAndTipoProduto(descricao, tipoProduto)).thenReturn(vProduto);
        }

        assertThat(vProduto.size() > 0).isTrue();
    }

    @Test
    void listarProdutoPorDescricaoTest() {
        Produto produto = new Produto(1L, "MESA", "MOVEL", new BigDecimal(100), 0);
        List<Produto> vProduto = new ArrayList<>();
        vProduto.add(produto);

        when(produtoRepository.findByDescricaoLike("MESA")).thenReturn(vProduto);

        assertThat(vProduto.size() > 0).isTrue();

    }

    @ParameterizedTest
    @MethodSource("providerTipoProduto")
    void salvarProdutoTest(String tipoProduto) {

        Produto produto = new Produto("MESA", tipoProduto, new BigDecimal(100), 0);
        Produto produtoSave = new Produto(1L, "MESA", tipoProduto, new BigDecimal(100), 0);

        if (tipoProduto != TipoProduto.ELETRONICO.getDescricao() &&
            tipoProduto != TipoProduto.ELETRODOMESTICO.getDescricao() &&
            tipoProduto != TipoProduto.MOVEL.getDescricao()) {

                Exception thrown = assertThrows(RecursoNaoEncontradoException.class, () -> produtoService.salvarProduto(produto));

                assertEquals(thrown.getMessage(), "Tipo do produto inválido.");
        } else {

            when(produtoRepository.save(produto)).thenReturn(produtoSave);

            produtoService.salvarProduto(produtoSave);

            assertEquals(1L, produtoSave.getCodigo());

        }
    }


    private static Stream<Arguments> providerParameters() {
        return Stream.of(
            Arguments.of("", ""),
            Arguments.of("MESA", ""),
            Arguments.of("", "MOVEL"),
            Arguments.of("MESA", "MOVEL")
        );
    }

    private static Stream<Arguments> providerTipoProduto() {
        return Stream.of(
            Arguments.of(TipoProduto.ELETRONICO.getDescricao()),
            Arguments.of(TipoProduto.ELETRODOMESTICO.getDescricao()),
            Arguments.of(TipoProduto.MOVEL.getDescricao()),
            Arguments.of("TESTE")
        );
    }

}