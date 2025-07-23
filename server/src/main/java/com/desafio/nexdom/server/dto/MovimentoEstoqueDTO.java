package com.desafio.nexdom.server.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class MovimentoEstoqueDTO {

    private Long codigo;
    private Long codigoProduto;
    private String descricaoProduto;
    private String tipoMovimentoEstoque;
    private int quantidadeMovimentada;
    private int estoqueAnterior;
    private int estoqueDisponivel;
    private BigDecimal valorVenda;
    private BigDecimal valorVendaTotal;
    private Date dataVenda;

    public MovimentoEstoqueDTO() {}

    public MovimentoEstoqueDTO(Long codigo, Long codigoProduto, String descricaoProduto, String tipoMovimentoEstoque,
        int quantidadeMovimentada, int estoqueAnterior, int estoqueDisponivel, BigDecimal valorVenda, BigDecimal valorVendaTotal, Date dataVenda) {
            this.codigo = codigo;
            this.codigoProduto = codigoProduto;
            this.descricaoProduto = descricaoProduto;
            this.tipoMovimentoEstoque = tipoMovimentoEstoque;
            this.quantidadeMovimentada = quantidadeMovimentada;
            this.estoqueAnterior = estoqueAnterior;
            this.estoqueDisponivel = estoqueDisponivel;
            this.valorVenda = valorVenda;
            this.valorVendaTotal = valorVendaTotal;
            this.dataVenda = dataVenda;
    }

    public Long getCodigo() {
        return codigo;
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

    public int getEstoqueAnterior() {
        return estoqueAnterior;
    }

    public int getEstoqueDisponivel() {
        return estoqueDisponivel;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public BigDecimal getValorVendaTotal() {
        return valorVendaTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

}
