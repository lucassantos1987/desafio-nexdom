package com.desafio.nexdom.server.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.nexdom.server.dto.LucroProdutoDTO;
import com.desafio.nexdom.server.dto.MovimentoEstoqueDTO;
import com.desafio.nexdom.server.model.MovimentoEstoque;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long> {
        
    @Query("SELECT new com.desafio.nexdom.server.dto.MovimentoEstoqueDTO(" + 
    "me.codigo, p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " + 
    "me.tipoMovimentacao AS tipoMovimentoEstoque, me.quantidadeMovimentada, " + 
    "me.estoqueAnterior, me.estoqueDisponivel, me.valorVenda, " +
    "(me.valorVenda * me.quantidadeMovimentada) AS valorVendaTotal, me.dataVenda) " + 
    "FROM MovimentoEstoque me " + 
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " + 
    "ORDER BY p.descricao, me.codigo ASC")
    public List<MovimentoEstoqueDTO> findMovimentacaoEstoqueAll();

    @Query("SELECT new com.desafio.nexdom.server.dto.MovimentoEstoqueDTO(" + 
    "me.codigo, p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " + 
    "me.tipoMovimentacao AS tipoMovimentoEstoque, me.quantidadeMovimentada, " + 
    "me.estoqueAnterior, me.estoqueDisponivel, me.valorVenda, " +
    "(me.valorVenda * me.quantidadeMovimentada) AS valorVendaTotal, me.dataVenda) " + 
    "FROM MovimentoEstoque me " + 
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " +
    "WHERE p.codigo = :codigoProduto " + 
    "ORDER BY p.descricao, me.codigo ASC")
    public List<MovimentoEstoqueDTO> findMovimentacaoEstoqueByProduto(@Param("codigoProduto") Long codigoProduto);

    @Query("SELECT new com.desafio.nexdom.server.dto.MovimentoEstoqueDTO(" + 
    "me.codigo, p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " + 
    "me.tipoMovimentacao AS tipoMovimentoEstoque, me.quantidadeMovimentada, " + 
    "me.estoqueAnterior, me.estoqueDisponivel, me.valorVenda, " +
    "(me.valorVenda * me.quantidadeMovimentada) AS valorVendaTotal, me.dataVenda) " + 
    "FROM MovimentoEstoque me " + 
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " +
    "WHERE me.tipoMovimentacao = :tipoMovimentacao " + 
    "ORDER BY p.descricao, me.codigo ASC")
    public List<MovimentoEstoqueDTO> findMovimentacaoEstoqueByTipoMovimentacao(@Param("tipoMovimentacao") String tipoMovimentacao);

    @Query("SELECT new com.desafio.nexdom.server.dto.MovimentoEstoqueDTO(" + 
    "me.codigo, p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " + 
    "me.tipoMovimentacao AS tipoMovimentoEstoque, me.quantidadeMovimentada, " + 
    "me.estoqueAnterior, me.estoqueDisponivel, me.valorVenda, " +
    "(me.valorVenda * me.quantidadeMovimentada) AS valorVendaTotal, me.dataVenda) " + 
    "FROM MovimentoEstoque me " + 
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " +
    "WHERE p.codigo = :codigoProduto " +
    "AND me.tipoMovimentacao = :tipoMovimentacao " +  
    "ORDER BY p.descricao, me.codigo ASC")
    public List<MovimentoEstoqueDTO> findMovimentacaoEstoqueByProdutoAndTipoMovimentacao(@Param("codigoProduto") Long codigoProduto, @Param("tipoMovimentacao") String tipoMovimentacao);

    @Query("SELECT new com.desafio.nexdom.server.dto.LucroProdutoDTO(" +
    "p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " +
    "me.quantidadeMovimentada, " +
    "p.valorFornecedor) " +
    "FROM MovimentoEstoque me " +
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " +
    "WHERE me.tipoMovimentacao = 'SAÍDA'")
    public List<LucroProdutoDTO> findTotalLucroProdutoAll();

    @Query("SELECT new com.desafio.nexdom.server.dto.LucroProdutoDTO(" +
    "p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " +
    "me.quantidadeMovimentada, " +
    "p.valorFornecedor) " +
    "FROM MovimentoEstoque me " +
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " +
    "WHERE me.tipoMovimentacao = 'SAÍDA' " +
    "AnD p.codigo = :codigoProduto")
    public List<LucroProdutoDTO> findTotalLucroProdutoByProduto(@Param("codigoProduto") Long codigoProduto);

    @Query(value = "SELECT me.valorVenda FROM MovimentoEstoque me WHERE me.produto.codigo = :codigoProduto AND me.tipoMovimentacao = 'SAÍDA'")
    public List<Double> findValorVenda(@Param("codigoProduto") Long codigoProduto);

    @Query(value = "SELECT me.valorFornecedor FROM MovimentoEstoque me WHERE me.produto.codigo = :codigoProduto AND me.tipoMovimentacao = 'SAÍDA'")
    public List<Double> findValorFornecedorByVenda(@Param("codigoProduto") Long codigoProduto);

    @Query("SELECT new com.desafio.nexdom.server.dto.MovimentoEstoqueDTO(" + 
    "p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " + 
    "me.quantidadeMovimentada, " + 
    "me.estoqueDisponivel, p.quantidadeEstoque AS estoqueAtual) " +
    "FROM MovimentoEstoque me " + 
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " +
    "WHERE me.tipoMovimentacao = 'SAÍDA' " +
    "ORDER BY p.descricao, me.codigo ASC")
    public List<MovimentoEstoqueDTO> findMovimentacaoEstoqueByTipoProdutoAll();
    
    @Query("SELECT new com.desafio.nexdom.server.dto.MovimentoEstoqueDTO(" + 
    "p.codigo AS codigoProduto, p.descricao AS descricaoProduto, " + 
    "me.quantidadeMovimentada, " + 
    "me.estoqueDisponivel, p.quantidadeEstoque AS estoqueAtual) " +
    "FROM MovimentoEstoque me " + 
    "INNER JOIN Produto p ON p.codigo = me.produto.codigo " +
    "WHERE me.tipoMovimentacao = 'SAÍDA' " +
    "AND p.tipoProduto = :tipoProduto " +
    "ORDER BY p.descricao, me.codigo ASC")
    public List<MovimentoEstoqueDTO> findMovimentacaoEstoqueByTipoProdutoByTipoProduto(@Param("tipoProduto") String tipoProduto);

}
