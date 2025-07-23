package com.desafio.nexdom.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.nexdom.server.dto.MovimentoEstoqueDTO;
import com.desafio.nexdom.server.enums.TipoMovimentacao;
import com.desafio.nexdom.server.exceptions.RecursoNaoEncontradoException;
import com.desafio.nexdom.server.exceptions.ValorNumericoInvalidoException;
import com.desafio.nexdom.server.model.MovimentoEstoque;
import com.desafio.nexdom.server.model.Produto;
import com.desafio.nexdom.server.repository.MovimentoEstoqueRepository;
import com.desafio.nexdom.server.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class MovimentoEstoqueService {

    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CalcularEstoqueService calcularEstoqueService;
    


    public List<MovimentoEstoqueDTO> listarMovimentacaoEstoque(Long codigoProduto, String tipoMovimentacao) {
        try {

            List<MovimentoEstoqueDTO> movimentacaoEstoque = new ArrayList<>();

            if (codigoProduto <= 0 && tipoMovimentacao.trim().isEmpty()) {
                movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueAll();

            } else if (codigoProduto > 0 && tipoMovimentacao.trim().isEmpty()) {
                movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByProduto(codigoProduto);

            } else if (codigoProduto <= 0 && !tipoMovimentacao.trim().isEmpty()) {
                movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByTipoMovimentacao(tipoMovimentacao);

            } else if (codigoProduto > 0 && !tipoMovimentacao.trim().isEmpty()) {
                movimentacaoEstoque = movimentoEstoqueRepository.findMovimentacaoEstoqueByProdutoAndTipoMovimentacao(codigoProduto, tipoMovimentacao);

            } else {
                throw new RecursoNaoEncontradoException("Paramêtros inválidos.");
            }

            return movimentacaoEstoque;
        } catch (Exception ex) {
            throw ex;
        }         
    }

    @Transactional
    public MovimentoEstoque salvarMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        try {


            System.out.println("Aqui: " + movimentoEstoque.toString());

            Produto produto = produtoRepository.findById(movimentoEstoque.getProduto().getCodigo())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Produto ID: " + movimentoEstoque.getProduto().getCodigo() + " não encontrado."));


            if (!movimentoEstoque.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA.getDescricao()) &&
                !movimentoEstoque.getTipoMovimentacao().equals(TipoMovimentacao.SAIDA.getDescricao())) {

                throw new RecursoNaoEncontradoException("Tipo de movimentação inválido.");
            }

            if (movimentoEstoque.getQuantidadeMovimentada() <= 0) {
                throw new ValorNumericoInvalidoException("Quantidade dever ser maior que zero.");
            }

            if (movimentoEstoque.getValorVenda().doubleValue() <= 0) {
                throw new ValorNumericoInvalidoException("Valor de venda dever ser maior que zero.");
            }

            int quantidadeEstoqueAtual = produtoRepository.getQuantidadeEstoque(produto.getCodigo());
            int quantidadeEstoqueNovo = calcularEstoqueService.calcularEstoque(quantidadeEstoqueAtual, movimentoEstoque.getQuantidadeMovimentada(), movimentoEstoque.getTipoMovimentacao());

            movimentoEstoque.setEstoqueAnterior(quantidadeEstoqueAtual);
            movimentoEstoque.setEstoqueDisponivel(quantidadeEstoqueNovo);

            produtoRepository.updateQuantidadeEstoque(quantidadeEstoqueNovo, produto.getCodigo());

            if (movimentoEstoque.getTipoMovimentacao().equals(TipoMovimentacao.SAIDA.getDescricao())) {
                movimentoEstoque.setValorFornecedor(produtoRepository.getValorFornecedor(produto.getCodigo()));
            } else {
                movimentoEstoque.setValorFornecedor(new BigDecimal(0));
            }

            if (movimentoEstoque.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA.getDescricao())) {
                produtoRepository.updateValorForncedor(movimentoEstoque.getValorVenda(), produto.getCodigo());
            }

            return movimentoEstoqueRepository.save(movimentoEstoque);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
