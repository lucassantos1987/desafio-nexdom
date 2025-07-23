package com.desafio.nexdom.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.nexdom.server.dto.LucroProdutoDTO;
import com.desafio.nexdom.server.service.LucroTotalProdutoService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/lucro_produto")
public class LucroTotalProdutoController {
    
    @Autowired
    private LucroTotalProdutoService lucroTotalProdutoService;

    @GetMapping
    public List<LucroProdutoDTO> listarLucroProduto(@RequestParam Long codigoProduto) {
        return lucroTotalProdutoService.listarLucroTotalProduto(codigoProduto);
    }
    
}
