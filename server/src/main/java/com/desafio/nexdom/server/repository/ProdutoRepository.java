package com.desafio.nexdom.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.nexdom.server.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    @Query("SELECT p FROM Produto p WHERE p.descricao LIKE %:descricao% ESCAPE '\\' ORDER BY p.codigo")
    public List<Produto> findByDescricaoLike(@Param("descricao") String descricao);

    public List<Produto> findByTipoProduto(String tipoProduto);

    @Query("SELECT p FROM Produto p WHERE p.descricao LIKE %:descricao% ESCAPE '\\' AND tipoProduto = :tipoProduto ORDER BY p.codigo")
    public List<Produto> findByDescricaoLikeAndTipoProduto(@Param("descricao") String descricao, @Param("tipoProduto") String tipoProduto);
}
