<script setup lang="ts">
import type { ProdutoTipo } from '@/types/produto-tipo';


defineProps<{
  produtoTipo: ProdutoTipo[];
  filtroTipoProduto: string;
  optionChangeFiltroTipoProduto: (e: Event) => void;
  consultar: () => void;
  limpar: () => void;
}>()
</script>

<template>
  <div class="mt-4 pb-20">
    <div>
      <div>
        <div class="bg-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
          focus-within:border-gray-600focus-within:bg-gray-200">

          <select
            id="tipoProduto"
            name="tipoProduto"
            class="w-full outline-0 text-gray-900"
            :value="filtroTipoProduto"
            @change="optionChangeFiltroTipoProduto">
            <option value="">SELECIONE O TIPO DO PRODUTO</option>
            <option value="ELETRÔNICO">ELETRÔNICO</option>
            <option value="ELETRODOMÉSTICO">ELETRODOMÉSTICO</option>
            <option value="MÓVEL">MÓVEL</option>
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
          Limpar
        </button>

      </div>
    </div>

    <div class="mt-4">
      <table class="w-full">
        <thead>
          <tr class="bg-emerald-600 h-[60px] text-white ">
            <th align="left" class="pl-2">Produto</th>
            <th align="right">Quantidade Saída (Dia Movimentação)</th>
            <th class="pr-2" align="right">Quantidade Disponível (Dia Movimentação)</th>
            <th class="pr-2" align="right">Estoque Atual</th>
          </tr>
        </thead>
        <tbody v-for="produto in produtoTipo" :key="produto.codigoProduto">
          <tr class="h-[40px] border-b-1 border-b-gray-400 ">
            <th class="font-light pl-2" align="left">{{ produto.codigoProduto }} - {{ produto.descricaoProduto }}</th>
            <th class="font-light" align="right">{{ produto.quantidadeMovimentada }}</th>
            <th class="font-light" align="right">{{ produto.estoqueDisponivel }}</th>
            <th class="font-light pr-2" align="right">{{ produto.estoqueAtual }}</th>
          </tr>
        </tbody>
        <span v-if="produtoTipo.length === 0">SEM REGISTRO(S)</span>
        <span class="font-semibold" v-else>Total.: {{ produtoTipo.length }} REGISTRO(S)</span>
      </table>
    </div>
  </div>
</template>
