package com.desafio.nexdom.server.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false, length = 30)
    private String descricao;
    
    @Column(nullable = false)
    private String tipoProduto;
    
    @Column(nullable = false, precision = 10, scale =  2)
    private BigDecimal valorFornecedor;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private int quantidadeEstoque;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<MovimentoEstoque> movimentacaoEstoque = new ArrayList<>();

    public Produto () {}

    public Produto(String descricao, String tipoProduto, BigDecimal valorFornecedor, int quantidadeEstoque) {
        this.descricao = descricao;
        this.tipoProduto = tipoProduto;
        this.valorFornecedor = valorFornecedor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Produto(Long codigo, String descricao, String tipoProduto, BigDecimal valorFornecedor, int quantidadeEstoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipoProduto = tipoProduto;
        this.valorFornecedor = valorFornecedor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    
    public BigDecimal getValorFornecedor() {
        return valorFornecedor;
    }

    public void setValorFornecedor(BigDecimal valorFornecedor) {
        this.valorFornecedor = valorFornecedor;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

}
