package com.desafio.nexdom.server.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.desafio.nexdom.server.dto.LucroProdutoDTO;
import com.desafio.nexdom.server.dto.MovimentoEstoqueDTO;
import com.desafio.nexdom.server.model.MovimentoEstoque;
import com.desafio.nexdom.server.model.Produto;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class MovimentoEstoqueRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MovimentoEstoqueRepository movimentoEstoqueRepository;

    @Test
    @DisplayName("Retornar lista com as movimentaçoes de estoque")
    void findMovimentacaoEstoqueAllExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueAll();

       assertThat(movimentoEstoque.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com as movimentaçoes de estoque")
    void findMovimentacaoEstoqueAllNaoExisteTest() {
        
       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueAll();

       assertThat(movimentoEstoque.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com as movimentaçoes de estoque pelo produto")
    void findMovimentacaoEstoqueByProdutoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByProduto(produto.getCodigo());

       assertThat(movimentoEstoque.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com as movimentaçoes de estoque pelo produto")
    void findMovimentacaoEstoqueByProdutoNaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByProduto(produto.getCodigo() + 1L);

       assertThat(movimentoEstoque.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com as movimentaçoes de estoque pelo tipo de movimentacao")
    void findMovimentacaoEstoqueByTipoMovimentacaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByTipoMovimentacao(estoque.getTipoMovimentacao());

       assertThat(movimentoEstoque.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com as movimentaçoes de estoque pelo tipo de movimentacao")
    void findMovimentacaoEstoqueByTipoMovimentacaoNaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByTipoMovimentacao("SAIDA");

       assertThat(movimentoEstoque.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com as movimentaçoes de estoque pelo produto e tipo de movimentacao")
    void findMovimentacaoEstoqueByProdutoAndTipoMovimentacaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByProdutoAndTipoMovimentacao(estoque.getProduto().getCodigo(), estoque.getTipoMovimentacao());

       assertThat(movimentoEstoque.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com as movimentaçoes de estoque pelo produto e tipo de movimentacao")
    void findMovimentacaoEstoqueByProdutoAndTipoMovimentacaoNaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByProdutoAndTipoMovimentacao(estoque.getProduto().getCodigo() + 99L, estoque.getTipoMovimentacao());

       assertThat(movimentoEstoque.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com o lucro do produto")
    void findTotalLucroProdutoAllExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "SAÍDA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<LucroProdutoDTO> lucro = movimentoEstoqueRepository.findTotalLucroProdutoAll();

       assertThat(lucro.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com o lucro do produto")
    void findTotalLucroProdutoAllNaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByProdutoAndTipoMovimentacao(estoque.getProduto().getCodigo() + 99L, estoque.getTipoMovimentacao());

       assertThat(movimentoEstoque.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com o lucro do produto pelo produto")
    void findTotalLucroProdutoByProdutoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "SAÍDA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<LucroProdutoDTO> lucro = movimentoEstoqueRepository.findTotalLucroProdutoByProduto(estoque.getProduto().getCodigo());

       assertThat(lucro.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com o lucro do produto pelo produto")
    void findTotalLucroProdutoByProdutoNaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<LucroProdutoDTO> lucro = movimentoEstoqueRepository.findTotalLucroProdutoByProduto(estoque.getProduto().getCodigo() + 99L);

       assertThat(lucro.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com os valores de Venda da Saída do produto")
    void findValorVendaExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "SAÍDA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<Double> valorVenda = movimentoEstoqueRepository.findValorVenda(estoque.getProduto().getCodigo());

       assertThat(valorVenda.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com os valores de Venda da Saída do produto")
    void findValorVendaNaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<Double> valorVenda = movimentoEstoqueRepository.findValorVenda(estoque.getProduto().getCodigo());

       assertThat(valorVenda.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com os valores de Fornecedor na Saída(Venda) do produto")
    void findValorFornecedorByVendaExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "SAÍDA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<Double> valorVenda = movimentoEstoqueRepository.findValorFornecedorByVenda(estoque.getProduto().getCodigo());

       assertThat(valorVenda.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com os valores de Fornecedor na Saída(Venda) do produto")
    void findValorFornecedorByVendaNaoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<Double> valorVenda = movimentoEstoqueRepository.findValorFornecedorByVenda(estoque.getProduto().getCodigo());

       assertThat(valorVenda.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com os movimentações de saída")
    void findMovimentacaoEstoqueByTipoProdutoAllExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "SAÍDA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByTipoProdutoAll();

       assertThat(movimentacaoEstoque.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar lista com os movimentações de saída")
    void findMovimentacaoEstoqueByTipoProdutoAllNaoExisteTest() {
        
       List<MovimentoEstoqueDTO> movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByTipoProdutoAll();

       assertThat(movimentacaoEstoque.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar lista com os movimentações de saída pelo tipo do produto")
    void findMovimentacaoEstoqueByTipoProdutoByTipoProdutoExisteTest() {
        
        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "SAÍDA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByTipoProdutoByTipoProduto(estoque.getProduto().getTipoProduto());

       assertThat(movimentacaoEstoque.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não Retornar lista com os movimentações de saída pelo tipo do produto")
    void findMovimentacaoEstoqueByTipoProdutoByTipoProdutoNaoExisteTest() {

        String dateString = "2025-07-23";
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        MovimentoEstoque estoque = new MovimentoEstoque(produto, "ENTRADA", new BigDecimal(100), new BigDecimal(100), sqlDate, 100, 0, 0);

       this.createProduto(produto);
       this.createMovimentacaoEstoque(estoque);

       List<MovimentoEstoqueDTO> movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByTipoProdutoByTipoProduto("MOVEL");

       assertThat(movimentacaoEstoque.size() > 0).isFalse();
    }
    
    private Produto createProduto(Produto produto) {
        Produto novoProduto = produto;
        this.entityManager.persist(novoProduto);

        return novoProduto;
    }

    private MovimentoEstoque createMovimentacaoEstoque(MovimentoEstoque estoque) {

        MovimentoEstoque novoEstoque = estoque;

        this.entityManager.persist(novoEstoque);

        return novoEstoque;
    }


}
