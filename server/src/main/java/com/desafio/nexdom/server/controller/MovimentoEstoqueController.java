package com.desafio.nexdom.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<MovimentoEstoqueDTO> listarMovimentacaoEstoque(@RequestParam Long codigoProduto, @RequestParam String tipoMovimentacao) {
        return movimentoEstoqueService.listarMovimentacaoEstoque(codigoProduto, tipoMovimentacao);
    }

    @GetMapping("/consuta_tipo_produto")
    public List<MovimentoEstoqueDTO> listarMovimentacaoEstoqueTipoProduto(@RequestParam String tipoProduto) {
        return movimentoEstoqueService.listarMovimentacaoEstoqueTipoProduto(tipoProduto);
    }

    @PostMapping
    public MovimentoEstoque salvarMovimentoEstoque(@RequestBody MovimentoEstoque movimentoEstoque) {
        return movimentoEstoqueService.salvarMovimentoEstoque(movimentoEstoque);
    }    

}
