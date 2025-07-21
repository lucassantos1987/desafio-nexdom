<script setup lang="ts">
import CadastroProdutoForm from '@/components/CadastroProdutoForm.vue'
import CadastroProdutoTabela from '@/components/CadastroProdutoTabela.vue';
import api from '@/http/api';
import type  Produto  from '@/types/produto';
import { ref, onMounted } from 'vue';

const produtos = ref<Produto[]>([])
const filtroDescricao = ref<string>('');
const filtroTipoProduto = ref<string>('');
const editarCadastro = ref<boolean>(false);

const descricao = ref<string>('');
const tipoProduto = ref<string>('');
const valorFornecedor = ref<number>(0);
const quantidadeEstoque = ref<number>(0);

function handleInput(event: Event) {
    const input = event.target as HTMLInputElement;
    filtroDescricao.value = input.value;
}

function optionChange(event: Event) {
  const select = event.target as HTMLSelectElement;
  filtroTipoProduto.value = select.value;
}

async function listarProdutos() {

  await api.get("produto", {
    params: {
      descricao: filtroDescricao.value.trim().toUpperCase(),
      tipoProduto: filtroTipoProduto.value.trim()
    }
  })
  .then((response) => {
    produtos.value = response.data
  })
  .catch((error) => {
    console.error("Erro no retorno dos produtos: ", error.message);
  })
}

function consultar() {
  listarProdutos();
}

function limparcamposFiltros() {
  filtroDescricao.value = "";
  filtroTipoProduto.value = "";
}

function limparCamposCadastro() {
  descricao.value = "";
  tipoProduto.value = "";
  valorFornecedor.value = 0.00;
  quantidadeEstoque.value = 0;
}

function novoCadastro() {
  editarCadastro.value = false;
  limparCamposCadastro();
}

function editar(produto: Produto) {
  editarCadastro.value = true;
  descricao.value = produto.descricao;
  tipoProduto.value = produto.tipoProduto;
  valorFornecedor.value = produto.valorFornecedor;
  quantidadeEstoque.value = produto.quantidadeEstoque;
}

async function salvar(e: { preventDefault: () => void }) {
  e.preventDefault();
}

function cancelar(e: { preventDefault: () => void }) {
  e.preventDefault();
  editarCadastro.value = false;
  limparCamposCadastro();
}

async function excluir(codigo: number) {

  await api.delete(`produto/excluir/${codigo}`, {
    params: {
      codigo: codigo
    }
  })
  .then(() => {
    console.log("Registro excluído com sucesso.")
  })
  .catch((error) => [
    console.error(`Erro: ${error.message}`)
  ])

  listarProdutos();
}


onMounted(() => {
  listarProdutos();
})

</script>

<template>
  <div class="flex flex-col justify-center p-4 mt-8">
    <h1 class="text-4xl font-bold">Cadastro de Produto</h1>

    <CadastroProdutoForm
      :descricao="descricao"
      :tipoProduto="tipoProduto"
      :valorFornecedor="valorFornecedor"
      :quantidadeEstoque="quantidadeEstoque"
      :handleInput="handleInput"
      :optionChange="optionChange"
      :novo="novoCadastro"
      :salvar="salvar"
      :cancelar="cancelar"/>

    <CadastroProdutoTabela
      :produtos="produtos"
      :consultar="consultar"
      :limpar="limparcamposFiltros"
      :editar="editar"
      :excluir="excluir"
      :descricao="filtroDescricao"
      :tipo-produto="filtroTipoProduto"
      :handleInput="handleInput"
      :optionChange="optionChange"/>
  </div>
</template>
