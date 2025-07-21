<script setup lang="ts">
defineProps<{
  descricao: string;
  tipoProduto: string;
  valorFornecedor: number;
  quantidadeEstoque: number;
  handleInput: (e: Event) => void;
  optionChange: (e: Event) => void;
  novo: () => void;
  salvar: (e: { preventDefault: () => void }) => void;
  cancelar: (e: { preventDefault: () => void }) => void;
}>()
</script>
<template>
  <div class="mt-4">
    <button
      @click="novo"
      class="w-full h-12 bg-emerald-600 hover:bg-emerald-700 transition-colors
      duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
      Novo Cadastro
    </button>
  </div>
  <form class="mt-4 space-y-3">
    <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="text"
          id="descricao"
          name="descricao"
          :value="descricao"
          @input="handleInput"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="DESCRIÇÃO DO PRODUTO"/>
    </div>

    <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <select
          id="tipoProduto"
          name="tipoProduto"
          :value="tipoProduto"
          @change="optionChange"
          class="outline-0 w-full text-gray-900">
          <option value="">SELECIONE O TIPO DO PRODUTO</option>
          <option value="ELETRÔNICO">ELETRÔNICO</option>
          <option value="ELETRODOMÉSTICO">ELETRODOMÉSTICO</option>
          <option value="MÓVEL">MÓVEL</option>
        </select>
    </div>

    <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="number"
          step="0.01"
          inputmode="decimal"
          min="0"
          :value="valorFornecedor"
          @input="handleInput"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="VALOR DO FORNECEDOR"/>
    </div>

    <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="number"
          min="0"
          :value="quantidadeEstoque"
          @input="handleInput"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="QUANTIDADE EM ESTOQUE"/>
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
