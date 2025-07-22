package com.desafio.nexdom.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.nexdom.server.dto.MovimentoEstoqueDTO;
import com.desafio.nexdom.server.model.MovimentoEstoque;
import com.desafio.nexdom.server.service.MovimentoEstoqueService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/movimentacao_estoque")
public class MovimentoEstoqueController {

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @GetMapping
    public List<MovimentoEstoqueDTO> listarMovimentacaoEstoque() {
        return movimentoEstoqueService.listarMovimentacaoEstoque();
    }

    
    @PostMapping
    public MovimentoEstoque salvarMovimentoEstoque(@RequestBody MovimentoEstoque movimentoEstoque) {
        return movimentoEstoqueService.salvarMovimentoEstoque(movimentoEstoque);
    }    

}
