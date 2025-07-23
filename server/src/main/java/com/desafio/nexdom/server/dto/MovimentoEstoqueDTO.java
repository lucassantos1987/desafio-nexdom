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
    private int estoqueAtual;

    public MovimentoEstoqueDTO() {}

    public MovimentoEstoqueDTO(Long codigoProduto, String descricaoProduto, int quantidadeMovimentada, int estoqueDisponivel, int estoqueAtual) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidadeMovimentada = quantidadeMovimentada;
        this.estoqueDisponivel = estoqueDisponivel;
        this.estoqueAtual = estoqueAtual;
    }

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

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getTipoMovimentoEstoque() {
        return tipoMovimentoEstoque;
    }

    public void setTipoMovimentacaoEstoque(String tipoMovimentacaoEstoque) {
        this.tipoMovimentoEstoque = tipoMovimentacaoEstoque;
    }

    public int getQuantidadeMovimentada() {
        return quantidadeMovimentada;
    }

    public void setQuantidadeMovimentada(int quantidadeMovimentada) {
        this.quantidadeMovimentada = quantidadeMovimentada;
    }

    public int getEstoqueAnterior() {
        return estoqueAnterior;
    }

    public void setEstoqueAnterior(int estoqueAnterior) {
        this.estoqueAnterior = estoqueAnterior;
    }

    public int getEstoqueDisponivel() {
        return estoqueDisponivel;
    }

    public void setEstoqueDisponivel(int estoqueDisponivel) {
        this.estoqueDisponivel = estoqueDisponivel;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getValorVendaTotal() {
        return valorVendaTotal;
    }

    public void setValorVendaTotal(BigDecimal valorVendaTotal) {
        this.valorVendaTotal = valorVendaTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

}
