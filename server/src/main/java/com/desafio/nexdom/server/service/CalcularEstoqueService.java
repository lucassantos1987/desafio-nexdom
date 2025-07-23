package com.desafio.nexdom.server.service;

import org.springframework.stereotype.Service;

import com.desafio.nexdom.server.exceptions.ValorNumericoInvalidoException;

@Service
public class CalcularEstoqueService {

    public int calcularEstoque(int quantidadeEstoque, int quantidadeMovimentacao, String tipoMovomentacao) {
        try {

            int quantidadeRetornada = 0;

            switch (tipoMovomentacao) {
                case "ENTRADA":
                    quantidadeRetornada = quantidadeEstoque + quantidadeMovimentacao;
                    break;            
                default:
                    quantidadeRetornada = quantidadeEstoque - quantidadeMovimentacao;
                    break;
            }

            if (quantidadeRetornada < 0) {
                throw new ValorNumericoInvalidoException("Quantidade em estoque insuficiente");
            }

            return quantidadeRetornada;
        } catch (Exception ex) {
            throw ex;
        }        
    }
}
