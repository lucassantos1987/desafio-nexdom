package com.desafio.nexdom.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.nexdom.server.model.MovimentoEstoque;

public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long> {
    
}
