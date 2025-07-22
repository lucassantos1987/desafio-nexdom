package com.desafio.nexdom.server.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class MovimentoEstoqueDTO {

    private Long codigoProduto;
    private String descricaoProduto;
    private String tipoMovimentoEstoque;
    private int quantidadeMovimentada;
    private int estoqueDisponivel;
    private BigDecimal valorVenda;
    private Date dataVenda;

    public MovimentoEstoqueDTO() {}

    public MovimentoEstoqueDTO(Long codigoProduto, String descricaoProduto, String tipoMovimentoEstoque,
        int quantidadeMovimentada, int estoqueDisponivel, BigDecimal valorVenda, Date dataVenda) {
            this.codigoProduto = codigoProduto;
            this.descricaoProduto = descricaoProduto;
            this.tipoMovimentoEstoque = tipoMovimentoEstoque;
            this.quantidadeMovimentada = quantidadeMovimentada;
            this.estoqueDisponivel = estoqueDisponivel;
            this.valorVenda = valorVenda;
            this.dataVenda = dataVenda;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public String getTipoMovimentoEstoque() {
        return tipoMovimentoEstoque;
    }

    public int getQuantidadeMovimentada() {
        return quantidadeMovimentada;
    }

    public int getEstoqueDisponivel() {
        return estoqueDisponivel;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

}
