<script setup lang="ts">
import MovimentacaoEstoqueForm from '@/components/movimentacao_estoque/MovimentacaoEstoqueForm.vue';
import MovimentacaoEstoqueTabela from '@/components/movimentacao_estoque/MovimentacaoEstoqueTabela.vue';
import api from '@/http/api';
import type { MovimentacaoEstoque } from '@/types/movimentacao-estoque';

import type Produto from '@/types/produto';
import { onMounted, ref } from 'vue';

const produtos = ref<Produto[]>([]);
const codigoProduto = ref<string>('0');
const tipoMovimentacao = ref<string>('');
const valorVenda = ref<string>('');
const quantidadeMovimentada = ref<string>('');

const filtroProduto = ref<string>('');
const filtroTipoMovimentacao = ref<string>('');

const movimentacaoEstoque = ref<MovimentacaoEstoque[]>([]);
const produtoObj = ref<Produto>({
  codigo: 0,
  descricao: '',
  tipoProduto: '',
  valorFornecedor: 0,
  quantidadeEstoque: 0
});

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

function novo() {
  limparCamposCadastro();
}

async function salvar(e: { preventDefault: () => void }) {
  e.preventDefault();

  if (Number(codigoProduto.value.trim()) === 0) {
    return alert("Selecione o produto");
  }

  if (tipoMovimentacao.value.trim() === "") {
    return alert("Selecione o Tipo de Movimentação");
  }

  if (quantidadeMovimentada.value.trim() === "" || Number(quantidadeMovimentada.value) <= 0) {
    return alert("Quantidade dever ser maior que zero.");
  }

  if (valorVenda.value.trim() === "" || Number(valorVenda.value) <= 0) {
    return alert("Valor de venda dever ser maior que zero.");
  }

  produtoObj.value = {
    codigo: Number(codigoProduto.value),
    descricao: "",
    tipoProduto: "",
    valorFornecedor: 0,
    quantidadeEstoque: 0,
  }

  const produtoStr = JSON.stringify(produtoObj.value);
  const produto = JSON.parse(produtoStr);

  const data = {
    tipoMovimentacao: tipoMovimentacao.value,
    valorVenda: Number(valorVenda.value),
    quantidadeMovimentada: Number(quantidadeMovimentada.value),
    produto
  }

  await api.post("movimentacao_estoque", data)
  .then((response) => {
    if (response.data.codigo > 0) {
      alert("Movimentação gravada com sucesso.");
      consultar();
      limparCamposCadastro();
    }
  })
  .catch((error) => {
    alert(error.response.data.message);
  })
}

function cancelar(e: { preventDefault: () => void }) {
  e.preventDefault();
  limparCamposCadastro();
}

function optionChangeProduto(event: Event) {
  const select = event.target as HTMLSelectElement;
  codigoProduto.value = select.value;
}

function optionChangeTipoMovimentacao(event: Event) {
  const select = event.target as HTMLSelectElement;
  tipoMovimentacao.value = select.value;
}

function handleInputValorVenda(event: Event) {
  const input = event.target as HTMLInputElement;
  valorVenda.value = input.value;
}

function handleInputQuantidadeMovimentada(event: Event) {
  const input = event.target as HTMLInputElement;
  quantidadeMovimentada.value = input.value;
}

async function consultar() {
  await api.get("movimentacao_estoque", {
    params: {
      codigoProduto: Number(filtroProduto.value),
      tipoMovimentacao: filtroTipoMovimentacao.value
    }
  })
  .then((response) => {
    movimentacaoEstoque.value = response.data;
  })
  .catch((error) => {
    alert(error.response.data.message);
  })
}

function limparCamposFiltros() {
  filtroProduto.value = "";
  filtroTipoMovimentacao.value = "";
}

function limparCamposCadastro() {
  codigoProduto.value = "0";
  tipoMovimentacao.value = "";
  valorVenda.value = "";
  quantidadeMovimentada.value = "";
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
      :produto="codigoProduto"
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
