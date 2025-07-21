<script setup lang="ts">
import CadastroProdutoForm from '@/components/CadastroProdutoForm.vue'
import CadastroProdutoTabela from '@/components/CadastroProdutoTabela.vue';
import api from '@/http/api';
import type  Produto  from '@/types/produto';
import { ref, onMounted } from 'vue';

const produtos = ref<Produto[]>([])
const filtroDescricao = ref<string>('');
const filtroTipoProduto = ref<string>('');

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
    console.error("Erro no retorno dos produtos: ", error);
  })
}

 function handleInput(event: Event) {
    const input = event.target as HTMLInputElement;
    filtroDescricao.value = input.value;
}

function optionChange(event: Event) {
  const select = event.target as HTMLSelectElement;
  filtroTipoProduto.value = select.value;
}

function consultar() {
  listarProdutos();
}

function limpar() {
  console.log("Limpar");

  console.log(filtroDescricao.value);
  console.log(filtroTipoProduto.value);
}

function editar() {
  console.log("Editar")
}

function excluir() {
  console.log("Excluir")
}


onMounted(() => {
  listarProdutos();
})

</script>

<template>
  <div class="flex flex-col justify-center p-4 mt-8">
    <h1 class="text-4xl font-bold">Cadastro de Produto</h1>

    <CadastroProdutoForm />
    <CadastroProdutoTabela
      :produtos="produtos"
      :consultar="consultar"
      :limpar="limpar"
      :editar="editar"
      :excluir="excluir"
      :descricao="filtroDescricao"
      :tipo-produto="filtroTipoProduto"
      :handleInput="handleInput"
      :optionChange="optionChange"/>
  </div>
</template>
