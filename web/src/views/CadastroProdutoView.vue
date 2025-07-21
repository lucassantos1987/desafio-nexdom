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

  if (filtroDescricao.value.trim() !== '' || Number(filtroTipoProduto) > 0) {
    await api.get("produto", {
      params: {
        descricao: filtroDescricao.value.toUpperCase,
        tipoProduto: Number(filtroTipoProduto)
      }
    })
    .then((response) => {
      produtos.value = response.data
    })
    .catch((error) => {
      console.error("Erro no retorno dos produtos: ", error);
    })
  } else {

    await api.get("produto")
    .then((response) => {
      produtos.value = response.data
    })
    .catch((error) => {
      console.error("Erro no retorno dos produtos: ", error);
    })
  }
}

 function handleInput(event: Event) {
    const input = event.target as HTMLInputElement;
    filtroDescricao.value = input.value;
}

function consultar() {
  listarProdutos();
}



function limpar() {
  console.log("Limpar");
  console.log(filtroDescricao.value)
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
      :handleInput="handleInput"/>
  </div>
</template>
