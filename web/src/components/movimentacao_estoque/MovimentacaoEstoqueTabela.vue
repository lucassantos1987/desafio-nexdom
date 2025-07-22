<script setup lang="ts">
import type { MovimentacaoEstoque } from '@/types/movimentacao-estoque';
import type Produto from '@/types/produto';

defineProps<{
  filtroProduto: string;
  filtroTipoMovimentacao: string;
  consultar: () => void;
  limpar: () => void;
  optionChangeFiltroProduto: (e: Event) => void;
  optionChangeFiltroTipoMovimentacao: (e: Event) => void;
  produtos: Produto[];
  movimentacaoEstoque: MovimentacaoEstoque[];
}>()
</script>

<template>
  <div class="mt-4 pb-20">
    <div class="border-1 rounded-2xl p-4">
      <h3 class="text-2xl font-medium pb-3">Filtrar Consulta</h3>
      <div class="flex-col columns-2 gap-2">

        <div class="bg-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
          focus-within:border-gray-600focus-within:bg-gray-200">

          <select
            class="w-full outline-0 text-gray-900"
            :value="filtroProduto"
            @change="optionChangeFiltroProduto">
            <option value="">SELECIONE O PRODUTO</option>

            <option v-for="produto in produtos" :value="produto.codigo" :key="produto.codigo">
              {{ produto.descricao }} - {{ produto.tipoProduto }}
            </option>

          </select>
        </div>

        <div class="bg-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
          focus-within:border-gray-600focus-within:bg-gray-200">

          <select
            :value="filtroTipoMovimentacao"
            @change="optionChangeFiltroTipoMovimentacao"
            class="w-full outline-0 text-gray-900">
            <option value="">SELECIONE O TIPO MOVIMENTAÇÃO</option>
            <option value="ENTRADDA">ENTRADA</option>
            <option value="SAÍDA">SAÍDA</option>
          </select>
        </div>
      </div>
      <div class="flex flex-row gap-2 mt-2">
        <button
          @click="consultar()"
          class="w-full h-12 bg-emerald-600 hover:bg-emerald-700 transition-colors
          duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
          Consultar
        </button>
        <button
          @click="limpar()"
          class="w-full h-12 bg-emerald-600 hover:bg-emerald-700 transition-colors
          duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
          Limpar Filtros
        </button>

      </div>
    </div>

    <div class="mt-4">
      <table class="w-full">
        <thead>
          <tr class="bg-emerald-600 h-[60px] text-white ">
            <th align="left" class="pl-2">Produto</th>
            <th align="left">Tipo Movimentação</th>
            <th align="right">Quantidade Movimentada</th>
            <th align="right">Estoque Anterior</th>
            <th align="right">Estoque Disponível</th>
            <th align="right">Valor Venda</th>
            <th align="left" class="pl-8">Data Venda</th>
          </tr>
        </thead>

        <tbody v-for="estoque in movimentacaoEstoque" :key="estoque.codigo">
          <tr class="h-[40px] border-b-1 border-b-gray-400">
            <th class="font-light pl-2" align="left">{{ estoque.codigoProduto }} - {{ estoque.descricaoProduto }}</th>
            <th class="font-light" align="left">{{ estoque.tipoMovimentoEstoque }}</th>
            <th class="font-light" align="right">{{ estoque.quantidadeMovimentada }}</th>
            <th class="font-light" align="right">{{ estoque.estoqueAnterior }}</th>
            <th class="font-light" align="right">{{ estoque.estoqueDisponivel }}</th>
            <th class="font-light" align="right">{{ estoque.valorVenda }}</th>
            <th class="font-light pl-8" align="left">{{ estoque.dataVenda }}</th>
          </tr>
        </tbody>
          <span v-if="movimentacaoEstoque.length === 0">SEM REGISTRO(S)</span>
          <span class="font-semibold" v-else>Total.: {{ movimentacaoEstoque.length }} REGISTRO(S)</span>
      </table>
    </div>
  </div>
</template>
