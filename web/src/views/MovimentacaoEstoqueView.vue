<script setup lang="ts">
import MovimentacaoEstoqueForm from '@/components/movimentacao_estoque/MovimentacaoEstoqueForm.vue';
import MovimentacaoEstoqueTabela from '@/components/movimentacao_estoque/MovimentacaoEstoqueTabela.vue';
import api from '@/http/api';
import type { MovimentacaoEstoque } from '@/types/movimentacao-estoque';

import type Produto from '@/types/produto';
import { onMounted, ref } from 'vue';

const produtos = ref<Produto[]>([]);
const produto = ref<string>('');
const tipoMovimentacao = ref<string>('');
const valorVenda = ref<number>(0);
const quantidadeMovimentada = ref<number>(0);

const filtroProduto = ref<string>('');
const filtroTipoMovimentacao = ref<string>('');

const movimentacaoEstoque = ref<MovimentacaoEstoque[]>([]);

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
    console.error("Erro no retorno dos produtos: ", error.message);
  })
}

function novo() {
  limparCamposCadastro();
}

function salvar(e: { preventDefault: () => void }) {
  e.preventDefault();
}

function cancelar(e: { preventDefault: () => void }) {
  e.preventDefault();
  limparCamposCadastro();
}

function optionChangeProduto(event: Event) {
  const select = event.target as HTMLSelectElement;
  produto.value = select.value;
}

function optionChangeTipoMovimentacao(event: Event) {
  const select = event.target as HTMLSelectElement;
  tipoMovimentacao.value = select.value;
}

function handleInputValorVenda(event: Event) {
  const input = event.target as HTMLInputElement;
  valorVenda.value = Number(input.value);
}

function handleInputQuantidadeMovimentada(event: Event) {
  const input = event.target as HTMLInputElement;
  quantidadeMovimentada.value = Number(input.value);
}

async function consultar() {
  await api.get("movimentacao_estoque")
  .then((response) => {
    movimentacaoEstoque.value = response.data;
  })
  .catch((error) => {
    console.error(`Erro na consulta: ${error.message}`);
  })
}

function limparCamposFiltros() {
  filtroProduto.value = "";
  filtroTipoMovimentacao.value = "";
}

function limparCamposCadastro() {
  produto.value = "";
  tipoMovimentacao.value = "";
  valorVenda.value = 0.00;
  quantidadeMovimentada.value = 0;
}

function optionChangeFiltroProduto(event: Event) {
  const select = event.target as HTMLSelectElement;
  filtroProduto.value = select.value;
}

function optionChangeFiltroTipoMovimentacao(event: Event) {
  const select = event.target as HTMLSelectElement;
  filtroTipoMovimentacao.value = select.value;
}

onMounted(() => {
  listarProdutos();
  consultar();

})

</script>

<template>
  <div class="flex flex-col justify-center p-4 mt-8">
    <h1 class="text-4xl font-bold">Movimentacao de Estoque</h1>

    <MovimentacaoEstoqueForm
      :novo="novo"
      :salvar="salvar"
      :cancelar="cancelar"
      :produto="produto"
      :tipoMovimentacao="tipoMovimentacao"
      :valorVenda="valorVenda"
      :quantidadeMovimentada="quantidadeMovimentada"
      :optionChangeProduto="optionChangeProduto"
      :optionChangeTipoMovimentacao="optionChangeTipoMovimentacao"
      :handleInputValorVenda="handleInputValorVenda"
      :handleInputQuantidadeMovimentada="handleInputQuantidadeMovimentada"
      :produtos="produtos"
    />
    <MovimentacaoEstoqueTabela
      :filtroProduto="filtroProduto"
      :filtroTipoMovimentacao="filtroTipoMovimentacao"
      :consultar="consultar"
      :limpar="limparCamposFiltros"
      :optionChangeFiltroProduto="optionChangeFiltroProduto"
      :optionChangeFiltroTipoMovimentacao="optionChangeFiltroTipoMovimentacao"
      :produtos="produtos"
      :movimentacaoEstoque="movimentacaoEstoque"
    />

  </div>
</template>
