package com.desafio.nexdom.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.nexdom.server.enums.TipoProduto;
import com.desafio.nexdom.server.exceptions.RecursoNaoEncontradoException;
import com.desafio.nexdom.server.model.Produto;
import com.desafio.nexdom.server.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProduto(String descricao, String tipoProduto) {
        try {

            List<Produto> produto = new ArrayList<>();

            if (descricao.trim().isEmpty() && tipoProduto.trim().isEmpty()) {
                produto = produtoRepository.findAll();
            }

            if (!descricao.trim().isEmpty() && tipoProduto.trim().isEmpty()) {
                produto = produtoRepository.findByDescricaoLike(descricao);
            }

            if (descricao.trim().isEmpty() && !tipoProduto.trim().isEmpty()) {
                produto = produtoRepository.findByTipoProduto(tipoProduto);
            } 

            if (!descricao.trim().isEmpty() && !tipoProduto.trim().isEmpty()) {
                produto = produtoRepository.findByDescricaoLikeAndTipoProduto(descricao, tipoProduto);
            }

            return produto;
        } catch (Exception ex) {
            throw ex;
        }        
    }

    public Optional<Produto> listarProdutoPorCodigo(Long codigo) {
        try {
            return produtoRepository.findById(codigo);
        } catch (Exception ex) {
            throw ex;
        }
        
    }

    public List<Produto> listarProdutoPorDescricao(String descricao) {
        try {
            return produtoRepository.findByDescricaoLike(descricao);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public Produto salvarProduto(Produto produto) {
        try {

            if (!produto.getTipoProduto().equals(TipoProduto.ELETRONICO.getDescricao())
            && !produto.getTipoProduto().equals(TipoProduto.ELETRODOMESTICO.getDescricao())
            && !produto.getTipoProduto().equals(TipoProduto.MOVEL.getDescricao())) {

                throw new RecursoNaoEncontradoException("Tipo do produto inválido.");
            }

            return produtoRepository.save(produto);
        } catch (Exception ex) {
            throw ex;
        }
        
    }

    @Transactional
    public Produto alterarProduto(Produto produto) {
        try {
            Produto produtoAlterar = produtoRepository.findById(produto.getCodigo())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Produto ID: " + produto.getCodigo() + " não encontrado."));

            produtoAlterar.setDescricao(produto.getDescricao());
            produtoAlterar.setTipoProduto(produto.getTipoProduto());
            produtoAlterar.setValorFornecedor(produto.getValorFornecedor());
            produtoAlterar.setQuantidadeEstoque(produto.getQuantidadeEstoque());

            return produtoRepository.save(produtoAlterar);

        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public void deletarProduto(Long codigo) {
        try {
        if (!produtoRepository.existsById(codigo)) {
            throw new RecursoNaoEncontradoException("Produto com ID " + codigo + " não encontrado");
        }            
            produtoRepository.deleteById(codigo);
        } catch (Exception ex) {
            throw ex;
        } 
        
    }
}
