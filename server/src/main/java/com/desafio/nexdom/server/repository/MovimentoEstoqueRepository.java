package com.desafio.nexdom.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.nexdom.server.dto.MovimentoEstoqueDTO;
import com.desafio.nexdom.server.model.MovimentoEstoque;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long> {
        
    @Query("SELECT new com.desafio.nexdom.server.dto.MovimentoEstoqueDTO(" + //
    "p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " + //
    "me.tipoMovimentacao AS tipoMovimentoEstoque, me.quantidadeMovimentada, " + //
    "p.quantidadeEstoque AS estoqueDisponivel, me.valorVenda, me.dataVenda) " + //
    "FROM MovimentoEstoque me " + //
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo")
    public List<MovimentoEstoqueDTO> listarMovimentacaoEstoque();
}
