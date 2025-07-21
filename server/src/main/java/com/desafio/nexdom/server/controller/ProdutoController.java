package com.desafio.nexdom.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.nexdom.server.model.Produto;
import com.desafio.nexdom.server.service.ProdutoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarProduto(@RequestParam String descricao, @RequestParam String tipoProduto) {
        return produtoService.listarProduto(descricao, tipoProduto);
    }

    @GetMapping("/listar")
    public List<Produto> listarProduto() {
        return produtoService.listarProduto("", "");
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> listarProdutoPorCodigo(@PathVariable Long codigo) {
        return produtoService.listarProdutoPorCodigo(codigo)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @PutMapping("alterar/{codigo}")
    public Produto alterarProduto (@PathVariable Long codigo, @RequestBody Produto produto) {

        return produtoService.alterarProduto(produto);
    }
    @DeleteMapping("excluir/{codigo}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long codigo) {
        produtoService.deletarProduto(codigo);
        return ResponseEntity.noContent().build();
    }
    
}
