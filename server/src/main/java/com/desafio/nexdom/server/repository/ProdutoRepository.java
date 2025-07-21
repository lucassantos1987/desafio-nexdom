package com.desafio.nexdom.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.nexdom.server.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    @Query("SELECT p FROM Produto p WHERE p.descricao LIKE %:descricao% ESCAPE '\\' ORDER BY p.descricao")
    public List<Produto> findByDescricaoLike(String descricao);

    public List<Produto> findByTipoProduto(String tipoProduto);

    public List<Produto> findByDescricaoLikeAndTipoProduto(String descricao, String tipoProduto);
}
