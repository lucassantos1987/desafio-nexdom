<script setup lang="ts">
defineProps<{
  filtroProduto: string;
  filtroTipoMovimentacao: string;
  consultar: () => void;
  limpar: () => void;
  optionChangeFiltroProduto: (e: Event) => void;
  optionChangeFiltroTipoMovimentacao: (e: Event) => void;
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
            <option value="ELETRÔNICO">ELETRÔNICO</option>
            <option value="ELETRODOMÉSTICO">ELETRODOMÉSTICO</option>
            <option value="MÓVEL">MÓVEL</option>
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
            <th align="right">Estoque Disponível</th>
            <th align="right">Valor Venda</th>
            <th align="left" class="pl-8">Data Venda</th>
          </tr>
        </thead>
      </table>
    </div>
  </div>
</template>
