package com.desafio.nexdom.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.nexdom.server.model.MovimentoEstoque;
import com.desafio.nexdom.server.repository.MovimentoEstoqueRepository;

import jakarta.transaction.Transactional;

public class MovimentoEstoqueService {
    
    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;

    public List<MovimentoEstoque> listarMovimentacaoEstoque() {
        try {
            return movimentoEstoqueRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }         
    }

    @Transactional
    public MovimentoEstoque salvarMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        try {
            return movimentoEstoqueRepository.save(movimentoEstoque);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
