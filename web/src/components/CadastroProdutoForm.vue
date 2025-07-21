<template>
  <div class="mt-4">
    <button class="w-full h-12 bg-emerald-600 hover:bg-emerald-700 transition-colors
      duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
      Novo Cadastro
    </button>
  </div>
  <form class="mt-4 space-y-3">
    <div class="bg-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="text"
          id="descricao"
          name="descricao"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="DESCRIÇÃO DO PRODUTO"/>
    </div>

    <div class="bg-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <select id="tipoProduto" name="tipoProduto" class="outline-0 w-full text-gray-900">
          <option value="0">SELECIONE O TIPO DO PRODUTO</option>
          <option value="1">ELETÔNICO</option>
          <option value="2">ELETRODOMÉSTICO</option>
          <option value="3">MÓVEL</option>
        </select>
    </div>

    <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="number"
          step="0.01"
          inputmode="decimal"
          min="0"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="VALOR DO FORNECEDOR"/>
    </div>

    <div class="g-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
        focus-within:border-gray-600focus-within:bg-gray-200">

        <input
          type="number"
          min="0"
          class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
          placeholder="QUANTIDADE EM ESTOQUE"/>
    </div>

    <div class="flex flex-row gap-2">
    <button class="w-full h-12 bg-emerald-600 hover:bg-emerald-700 transition-colors
      duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
      Salvar
    </button>
    <button class="w-full h-12 bg-gray-600 hover:bg-gray-700 transition-colors
      duration-300 text-white rounded-[8px] cursor-pointer text-2xl font-bold">
      Cancelar
    </button>

    </div>
  </form>
</template>
