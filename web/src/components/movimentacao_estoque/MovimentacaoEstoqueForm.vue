<script setup lang="ts">
import type Produto from '@/types/produto';

defineProps<{
  novo: () => void;
  salvar: (e: { preventDefault: () => void }) => void;
  cancelar: (e: { preventDefault: () => void }) => void;
  produto: string;
  tipoMovimentacao: string;
  valorVenda: string;
  quantidadeMovimentada: string;
  optionChangeProduto: (e: Event) => void;
  optionChangeTipoMovimentacao: (e: Event) => void;
  handleInputValorVenda: (e: Event) => void;
  handleInputQuantidadeMovimentada: (e: Event) => void;
  produtos: Produto[]
}>()
</script>

<template>
  <div class="mt-4">
    <button
      @click="novo"
      class="w-full h-12 bg-emerald-600 hover:bg-emerald-700 transition-colors
      duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
      Nova Movimentação Estoque
    </button>
  </div>
  <form class="mt-4 space-y-3">
    <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

      <select
        id="produto"
        name="produto"
        :value="produto"
        @change="optionChangeProduto"
        class="outline-0 w-full text-gray-900">
        <option value="0">SELECIONE O PRODUTO</option>

        <option v-for="produto in produtos" :value="produto.codigo" :key="produto.codigo">
          {{ produto.descricao }} - {{ produto.tipoProduto }}
        </option>
      </select>
    </div>

    <div class="flex-col columns-3 gap-2">
      <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
          focus-within:border-gray-600focus-within:bg-gray-200">

        <select
          id="tipoMovimentacao"
          name="tipoMovimentacao"
          :value="tipoMovimentacao"
          @change="optionChangeTipoMovimentacao"
          class="outline-0 w-full text-gray-900">
          <option value="">SELECIONE O TIPO MOVIMENTAÇÃO</option>
          <option value="ENTRADA">ENTRADA</option>
          <option value="SAÍDA">SAÍDA</option>
        </select>
      </div>

      <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="number"
          step="0.01"
          inputmode="decimal"
          min="0"
          :value="valorVenda"
          @input="handleInputValorVenda"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="VALOR DE VFNDA"/>
      </div>

      <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="number"
          step="0.01"
          inputmode="decimal"
          min="0"
          :value="quantidadeMovimentada"
          @input="handleInputQuantidadeMovimentada"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="QUANTIDADE MOVIMENTADA"/>
      </div>
    </div>
    <div class="flex flex-row gap-2">
      <button
        @click="salvar"
        class="w-full h-12 bg-emerald-600 hover:bg-emerald-700 transition-colors
        duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
        Salvar
      </button>

      <button
        @click="cancelar"
        class="w-full h-12 bg-gray-600 hover:bg-gray-700 transition-colors
        duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
        Cancelar
      </button>

    </div>
  </form>
</template>
