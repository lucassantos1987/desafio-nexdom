package com.desafio.nexdom.server.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import com.desafio.nexdom.server.model.Produto;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class ProdutoRepositoryTest {
    
    @Autowired
    EntityManager entityManager;

    @Autowired
    ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Retornar a lista de produto")
    void findByDescricaoLikeProdutoExisteTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        List<Produto> listaProduto = this.produtoRepository.findByDescricaoLike("TV");

        assertThat(listaProduto.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar a lista de produto")
    void findByDescricaoLikeProdutoNaoExisteTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        List<Produto> listaProduto = this.produtoRepository.findByDescricaoLike("CASA");

        assertThat(listaProduto.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar a lista de produto pelo tipo produto")
    void findByTipoProdutoProdutoExisteTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        List<Produto> listaProduto = this.produtoRepository.findByTipoProduto("ELETRO");

        assertThat(listaProduto.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar a lista de produto pelo tipo produto")
    void findByTipoProdutoProdutoNaoExisteTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        List<Produto> listaProduto = this.produtoRepository.findByTipoProduto("MOVEL");

        assertThat(listaProduto.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar a lista de produto pelo produto e tipo produto")
    void findByDescricaoLikeAndTipoProdutoExisteTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        List<Produto> listaProduto = this.produtoRepository.findByDescricaoLikeAndTipoProduto("TV", "ELETRO");

        assertThat(listaProduto.size() > 0).isTrue();
    }

    @Test
    @DisplayName("Não retornar a lista de produto pelo produto e tipo produto")
    void findByDescricaoLikeAndTipoProdutoNaoExisteTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        List<Produto> listaProduto = this.produtoRepository.findByDescricaoLikeAndTipoProduto("TV", "'MOVEL");

        assertThat(listaProduto.size() > 0).isFalse();
    }

    @Test
    @DisplayName("Retornar estoque maior que zero")
    void getQuantidadeEstoqueMaiorQueZeroTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 10);
        this.createProduto(produto);

        int quantidadeEstoque = this.produtoRepository.getQuantidadeEstoque(produto.getCodigo());

        assertThat(quantidadeEstoque > 0).isTrue();
    }

    @Test
    @DisplayName("Retornar estoque igual a zero")
    void getQuantidadeEstoqueIgualAZeroTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        int quantidadeEstoque = this.produtoRepository.getQuantidadeEstoque(produto.getCodigo());

        assertThat(quantidadeEstoque == 0).isTrue();
    }

    @Test
    @DisplayName("Retornar valor do fornecedor")
    void getValorFornecedorTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        BigDecimal valorFornecedor = this.produtoRepository.getValorFornecedor(produto.getCodigo());

        assertThat(valorFornecedor.doubleValue() > 0).isTrue();
    }

    @Test
    @DisplayName("Atualizar quantidade estoque")
    void updateQuantidadeEstoqueTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        produto.setQuantidadeEstoque(1);

        produtoRepository.updateQuantidadeEstoque(produto.getQuantidadeEstoque(), produto.getCodigo());

        Produto produtoAlterado = produtoRepository.findById(produto.getCodigo()).orElse(null);
        assertThat(produtoAlterado).isNotNull();
        assertThat(produtoAlterado.getQuantidadeEstoque()).isEqualTo(1);

    }

    @Test
    @DisplayName("Atualizar valor fornecedor")
    void updateValorForncedorTest() {
        Produto produto = new Produto("TV", "ELETRO", new BigDecimal(100.0), 0);
        this.createProduto(produto);

        produto.setValorFornecedor(new BigDecimal(150.99));

        produtoRepository.updateValorForncedor(produto.getValorFornecedor(), produto.getCodigo());

        Produto produtoAlterado = produtoRepository.findById(produto.getCodigo()).orElse(null);
        assertThat(produtoAlterado).isNotNull();
        assertThat(produtoAlterado.getValorFornecedor()).isEqualTo(new BigDecimal(150.99));

    }

    private Produto createProduto(Produto produto) {
        Produto novoProduto = produto;
        this.entityManager.persist(novoProduto);

        return novoProduto;
    }

}
