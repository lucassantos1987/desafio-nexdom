<script setup lang="ts">
import ConsultaProdutoPorTipoTabela from '@/components/movimentacao_estoque/ConsultaProdutoPorTipoTabela.vue';
import api from '@/http/api';
import type { ProdutoTipo } from '@/types/produto-tipo';
import { ref } from 'vue';

const produtoTipo = ref<ProdutoTipo[]>([]);
const filtroTipoProduto = ref<string>('');

function optionChangeFiltroTipoProduto(event: Event) {
  const select = event.target as HTMLSelectElement;
  filtroTipoProduto.value = select.value;
}

async function consultar() {
  await api.get("movimentacao_estoque/consuta_tipo_produto", {
    params: {
      tipoProduto: filtroTipoProduto.value
    }
  })
  .then((response) => {
    produtoTipo.value = response.data;
  })
  .catch((error) => {
    alert(error.response.data.message);
  })
}

function limpar() {
  filtroTipoProduto.value = "";
}

</script>

<template>
  <div class="flex flex-col justify-center p-4 mt-8">
    <h1 class="text-4xl font-bold">Consulta de Produto por Tipo</h1>

    <ConsultaProdutoPorTipoTabela
      :produtoTipo="produtoTipo"
      :filtroTipoProduto="filtroTipoProduto"
      :optionChangeFiltroTipoProduto="optionChangeFiltroTipoProduto"
      :consultar="consultar"
      :limpar="limpar"
    />
  </div>
</template>
