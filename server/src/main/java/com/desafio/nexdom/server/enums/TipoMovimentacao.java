package com.desafio.nexdom.server.enums;

public enum TipoMovimentacao {
    ENTRADA(1, "ENTRADA"),
    SAIDA(2, "SAÍDA");

    private int codigo;
    private String descricao;

    TipoMovimentacao(int codigo, String descricao) {
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
