package com.desafio.nexdom.server.enums;

public enum TipoMovimentacao {
    ENTRADA("ENTRADA"),
    SAIDA("SAÍDA");

    private String descricao;

    TipoMovimentacao() {}

    TipoMovimentacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
