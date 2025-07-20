<script setup lang="ts">
import CadastroProdutoForm from '@/components/CadastroProdutoForm.vue'
import CadastroProdutoTabela from '@/components/CadastroProdutoTabela.vue';
</script>

<template>
  <div class="flex flex-col justify-center p-4 mt-8">
    <h1 class="text-4xl font-bold">Cadastro de Produto</h1>

    <CadastroProdutoForm />
    <CadastroProdutoTabela />
  </div>
</template>
