package com.desafio.nexdom.server.enums;

public enum TipoProduto {
    ELETRONICO("ELETRÔNICO"),
    ELETRODOMESTICO("ELETRODOMÉSTICO"),
    MOVEL("MÓVEL");

    private String descricao;

    TipoProduto() {}

    TipoProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
