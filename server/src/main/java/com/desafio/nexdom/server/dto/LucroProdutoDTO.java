package com.desafio.nexdom.server.dto;

import java.math.BigDecimal;

public class LucroProdutoDTO {
    
    private Long codigoProduto;
    private String descricaoProduto;
    private int quantidadeTotalSaida;
    private BigDecimal valorFornecedor;
    private BigDecimal totalLucro;

    public LucroProdutoDTO() {}

    public LucroProdutoDTO(Long codigoProduto, String descricaoProduto, int quantidadeTotalSaida, BigDecimal valorFornecedor) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidadeTotalSaida = quantidadeTotalSaida;
        this.valorFornecedor = valorFornecedor;
    }

    public LucroProdutoDTO(Long codigoProduto, String descricaoProduto, int quantidadeTotalSaida, BigDecimal valorFornecedor, BigDecimal totalLucro) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidadeTotalSaida = quantidadeTotalSaida;
        this.valorFornecedor = valorFornecedor;
        this.totalLucro = totalLucro;
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

    public int getQuantidadeTotalSaida() {
        return quantidadeTotalSaida;
    }

    public void setQuantidadeTotalSaida(int quantidadeTotalSaida) {
        this.quantidadeTotalSaida = quantidadeTotalSaida;
    }

    public BigDecimal getValorFornecedor() {
        return valorFornecedor;
    }

    public void setValorFornecedor(BigDecimal valorFornecedor) {
        this.valorFornecedor = valorFornecedor;
    }

    public BigDecimal getTotalLucro() {
        return totalLucro;
    }

    public void setTotalLucro(BigDecimal totalLucro) {
        this.totalLucro = totalLucro;
    }

}
