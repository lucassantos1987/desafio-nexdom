<script setup lang="ts">
import MovimentacaoEstoqueForm from '@/components/movimentacao_estoque/MovimentacaoEstoqueForm.vue';
import MovimentacaoEstoqueTabela from '@/components/movimentacao_estoque/MovimentacaoEstoqueTabela.vue';
</script>

<template>
  <div class="flex flex-col justify-center p-4 mt-8">
    <h1 class="text-4xl font-bold">Movimentacao de Estoque</h1>

    <MovimentacaoEstoqueForm />
    <MovimentacaoEstoqueTabela />

  </div>
</template>
