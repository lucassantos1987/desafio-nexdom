<script setup lang="ts">
import type  Produto  from '@/types/produto';

defineProps<{
  produtos: Produto[];
  consultar: () => void;
  limpar: () => void;
  editar: (produto: Produto) => void;
  excluir: (produto: Produto) => void;
  filtroDescricao: string;
  filtroTipoProduto: string;
  handleInputFiltroDescricao: (e: Event) => void;
  optionChangeFiltrotipoPriduto: (e: Event) => void;
}>()

</script>

<template>
  <div class="mt-4 pb-20">
    <div class="border-1 rounded-2xl p-4">
      <h3 class="text-2xl font-medium pb-3">Filtrar Consulta</h3>
      <div class="flex-col columns-2 gap-2">
        <div class="bg-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
          focus-within:border-gray-600focus-within:bg-gray-200">

          <input
            type="text"
            id="descricao"
            name="descricao"
            :value="filtroDescricao"
            @input="handleInputFiltroDescricao"
            class="w-full outline-0 placeholder-gray-800 text-gray-800 uppercase"
            placeholder="DESCRIÇÃO DO PRODUTO"/>
        </div>

        <div class="bg-gray-100 h-12 border border-gray-400 rounded-xl px-4 flex items-center gap-2
          focus-within:border-gray-600focus-within:bg-gray-200">

          <select
            id="tipoProduto"
            name="tipoProduto"
            class="w-full outline-0 text-gray-900"
            :value="filtroTipoProduto"
            @change="optionChangeFiltrotipoPriduto">
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
          Limpar Filtros
        </button>

      </div>
    </div>

    <div class="mt-4">
      <table class="w-full">
        <thead>
          <tr class="bg-emerald-600 h-[60px] text-white ">
            <th align="center">Código</th>
            <th align="left">Descrição</th>
            <th align="left">Tipo Do Produto</th>
            <th align="right">Valor Do Fornecedor</th>
            <th align="right" class="pr-1">Quantidade Em Estoque</th>
            <th align="center">Editar</th>
            <th align="center">Excluir</th>
          </tr>
        </thead>
        <tbody v-for="produto in produtos" :key="produto.codigo">
          <tr>
            <th class="font-light" align="center">{{ produto.codigo }}</th>
            <th class="font-light" align="left">{{ produto.descricao }}</th>
            <th class="font-light" align="left">{{ produto.tipoProduto }}</th>
            <th class="font-light" align="right">{{ produto.valorFornecedor }}</th>
            <th align="right" class="pr-1 font-light">{{ produto.quantidadeEstoque
             }}</th>
            <th align="center">
              <button
                @click="editar(produto)"
                class="w-full h-10 bg-emerald-600 hover:bg-emerald-700 transition-colors duration-300 text-white rounded-[8px] cursor-pointer">
                Editar
              </button>
            </th>
            <th align="center">
              <button
                @click="excluir(produto)"
                class="w-full h-10 bg-emerald-600 hover:bg-emerald-700 transition-colors duration-300 text-white rounded-[8px] cursor-pointer">
                Excluir
              </button>
            </th>
          </tr>
        </tbody>
        <span v-if="produtos.length === 0">SEM REGISTRO(S)</span>
      </table>
    </div>
  </div>
</template>
