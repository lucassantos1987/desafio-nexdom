package com.desafio.nexdom.server.model;

import java.math.BigDecimal;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimento_estoque")
public class MovimentoEstoque {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false)
    private String tipoMovimentacao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorVenda;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dataVenda;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidadeMovimentada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_produto")
    private Produto produto;

    public MovimentoEstoque() {}

    public MovimentoEstoque(Produto produto, String tipoMovimentacao, BigDecimal valorVenda, Date dataVenda, BigDecimal quantidadeMovimentada) {
        this.produto = produto;
        this.tipoMovimentacao = tipoMovimentacao;
        this.valorVenda = valorVenda;
        this.quantidadeMovimentada = quantidadeMovimentada;
    }

    public Long getCodigo() {
        return codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getQuantidadeMovimentada() {
        return quantidadeMovimentada;
    }

    public void setQuantidadeMovimentada(BigDecimal quantidadeMovimentada) {
        this.quantidadeMovimentada = quantidadeMovimentada;
    }

}
