<script setup lang="ts">
import LucroProdutoTabela from '@/components/movimentacao_estoque/LucroProdutoTabela.vue';
import api from '@/http/api';
import type { LucroProduto } from '@/types/lucro-produto';
import type Produto from '@/types/produto';
import { onMounted, ref } from 'vue';

const produtos = ref<Produto[]>([]);
const lucroProduto = ref<LucroProduto[]>([]);
const filtroProduto = ref<string>('0');

async function listarProdutos() {
  await api.get("produto", {
    params: {
      descricao: "",
      tipoProduto: ""
    }
  })
  .then((response) => {
    produtos.value = response.data
  })
  .catch((error) => {
    alert(error.response.data.message);
  })
}

function optionChangeFiltroProduto(event: Event) {
  const select = event.target as HTMLSelectElement;
  filtroProduto.value = select.value;
}

async function consultar() {
  await api.get("lucro_produto", {
    params: {
      codigoProduto: Number(filtroProduto.value)
    }
  })
  .then((response) => {
    lucroProduto.value = response.data
  })
  .catch((error) => {
    alert(error.response.data.message);
  })

}

function limpar() {
  filtroProduto.value = "0";
}

onMounted(() => {
  listarProdutos();
})

</script>

<template>
  <div class="flex flex-col justify-center p-4 mt-8">
    <h1 class="text-4xl font-bold">Consulta Lucro do Produto</h1>

    <LucroProdutoTabela
      :lucroProduto="lucroProduto"
      :produtos="produtos"
      :filtroProduto="filtroProduto"
      :optionChangeFiltroProduto="optionChangeFiltroProduto"
      :consultar="consultar"
      :limpar="limpar"
    />
  </div>
</template>
