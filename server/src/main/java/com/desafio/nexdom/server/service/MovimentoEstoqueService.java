package com.desafio.nexdom.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.nexdom.server.dto.MovimentoEstoqueDTO;
import com.desafio.nexdom.server.enums.TipoMovimentacao;
import com.desafio.nexdom.server.exceptions.RecursoNaoEncontradoException;
import com.desafio.nexdom.server.model.MovimentoEstoque;
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
    


    public List<MovimentoEstoqueDTO> listarMovimentacaoEstoque() {
        try {
            return movimentoEstoqueRepository.listarMovimentacaoEstoque();
        } catch (Exception ex) {
            throw ex;
        }         
    }

    @Transactional
    public MovimentoEstoque salvarMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        try {

            if (!movimentoEstoque.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA.getDescricao()) &&
                !movimentoEstoque.getTipoMovimentacao().equals(TipoMovimentacao.SAIDA.getDescricao())) {

                throw new RecursoNaoEncontradoException("Tipo de movimentação inválido.");
            }

            int quantidadeEstoqueAtual = produtoRepository.getQuantidadeEstoque(movimentoEstoque.getProduto().getCodigo());
            int quantidadeEstoqueNovo = calcularEstoqueService.calcularEstoque(quantidadeEstoqueAtual, movimentoEstoque.getQuantidadeMovimentada(), movimentoEstoque.getTipoMovimentacao());

            produtoRepository.updateQuantidadeEstoque(quantidadeEstoqueNovo, movimentoEstoque.getProduto().getCodigo());

            return movimentoEstoqueRepository.save(movimentoEstoque);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
