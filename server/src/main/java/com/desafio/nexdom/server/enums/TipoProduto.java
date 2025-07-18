package com.desafio.nexdom.server.enums;

public enum TipoProduto {
    ELETRONICO(1, "ELETRÔNICO"),
    ELETRODOMESTICO(2, "ELETRODOMÉSTICO"),
    MOVEL(3, "MÓVEL");

    private int codigo;
    private String descricao;

    TipoProduto(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
