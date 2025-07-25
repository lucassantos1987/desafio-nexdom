package com.desafio.nexdom.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.nexdom.server.dto.LucroProdutoDTO;
import com.desafio.nexdom.server.repository.MovimentoEstoqueRepository;

@Service
public class LucroProdutoService {
    
    @Autowired
    MovimentoEstoqueRepository movimentoEstoqueRepository;

    public List<LucroProdutoDTO> listarLucroTotalProduto(Long codigoProduto) {
        
        try {
            List<LucroProdutoDTO> lucroTotal = new ArrayList<>();

            if (codigoProduto <= 0) {
                lucroTotal = movimentoEstoqueRepository.findTotalLucroProdutoAll();
            } else {
                lucroTotal = movimentoEstoqueRepository.findTotalLucroProdutoByProduto(codigoProduto);
            }            

            List<LucroProdutoDTO> lucroProdutoRetorno = new ArrayList<>();

            for (LucroProdutoDTO lucroProduto : lucroTotal) {

                long count = lucroProdutoRetorno.stream().filter(p -> p.getCodigoProduto() == lucroProduto.getCodigoProduto()).count();

                if (count == 0) {

                    List<Double> valorVenda = movimentoEstoqueRepository.findValorVenda(lucroProduto.getCodigoProduto());
                    List<Double> valorFornecedor = movimentoEstoqueRepository.findValorFornecedorByVenda(lucroProduto.getCodigoProduto());

                    double totalValorVenda = valorVenda.stream().mapToDouble(Double::doubleValue).sum();
                    double totalValorFornecedor = valorFornecedor.stream().mapToDouble(Double::doubleValue).sum();
                    
                    BigDecimal valorLucro = new BigDecimal(totalValorVenda - totalValorFornecedor);

                    LucroProdutoDTO lucro = new LucroProdutoDTO(
                        lucroProduto.getCodigoProduto(),
                        lucroProduto.getDescricaoProduto(),
                        lucroTotal.stream().filter(p -> p.getCodigoProduto() == lucroProduto.getCodigoProduto()).mapToInt(p -> p.getQuantidadeTotalSaida()).sum(),
                        lucroProduto.getValorFornecedor(),
                        valorLucro);

                    lucroProdutoRetorno.add(lucro);
                }
            }

            return lucroProdutoRetorno;

        } catch (Exception ex) {
            throw ex;
        }
    }
}
