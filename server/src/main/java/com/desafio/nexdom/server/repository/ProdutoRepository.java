package com.desafio.nexdom.server.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.nexdom.server.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    @Query("SELECT p FROM Produto p WHERE p.descricao LIKE %:descricao% ESCAPE '\\' ORDER BY p.codigo")
    public List<Produto> findByDescricaoLike(@Param("descricao") String descricao);

    public List<Produto> findByTipoProduto(String tipoProduto);

    @Query("SELECT p FROM Produto p WHERE p.descricao LIKE %:descricao% ESCAPE '\\' AND tipoProduto = :tipoProduto ORDER BY p.codigo")
    public List<Produto> findByDescricaoLikeAndTipoProduto(@Param("descricao") String descricao, @Param("tipoProduto") String tipoProduto);

    @Query("SELECT p.quantidadeEstoque FROM Produto p WHERE codigo = :codigo")
    public int getQuantidadeEstoque(@Param("codigo") Long codigo);

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.quantidadeEstoque = :quantidadeEstoque WHERE p.codigo = :codigo")
    public void updateQuantidadeEstoque(@Param("quantidadeEstoque") int quantidadeEstoque, @Param("codigo") Long codigo);

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.valorFornecedor = :valorFornecedor WHERE p.codigo = :codigo")
    public void updateValorForncedor(@Param("valorFornecedor") BigDecimal valorFornecedor, @Param("codigo") Long codigo);

    @Query("SELECT p.valorFornecedor FROM Produto p WHERE codigo = :codigo")
    public BigDecimal getValorFornecedor(@Param("codigo") Long codigo);


}
