<script setup lang="ts">
import CadastroProdutoForm from '@/components/cadastro/CadastroProdutoForm.vue'
import CadastroProdutoTabela from '@/components/cadastro/CadastroProdutoTabela.vue';
import api from '@/http/api';

import type  Produto  from '@/types/produto';
import { ref, onMounted } from 'vue';

const produtos = ref<Produto[]>([])
const filtroDescricao = ref<string>('');
const filtroTipoProduto = ref<string>('');
const editarCadastro = ref<boolean>(false);

const codigo = ref<number>(0);
const descricao = ref<string>('');
const tipoProduto = ref<string>('');
const valorFornecedor = ref<number>(0);
const quantidadeEstoque = ref<number>(0);

function handleInputFiltroDescricao(event: Event) {
    const input = event.target as HTMLInputElement;
    filtroDescricao.value = input.value;
}

function optionChangeFiltroTipoProduto(event: Event) {
  const select = event.target as HTMLSelectElement;
  filtroTipoProduto.value = select.value;
}

function handleInputDescricao(event: Event) {
    const input = event.target as HTMLInputElement;
    descricao.value = input.value;
}

function optionChangeTipoProduto(event: Event) {
  const select = event.target as HTMLSelectElement;
  tipoProduto.value = select.value;
}

function handleInputValorFornecedor(event: Event) {
    const input = event.target as HTMLInputElement;
    valorFornecedor.value = Number(input.value);
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
}

function novoCadastro() {
  editarCadastro.value = false;
  limparCamposCadastro();
}

function editar(produto: Produto) {
  editarCadastro.value = true;
  codigo.value = produto.codigo;
  descricao.value = produto.descricao;
  tipoProduto.value = produto.tipoProduto;
  valorFornecedor.value = produto.valorFornecedor;

}

async function salvar(e: { preventDefault: () => void }) {
  e.preventDefault();

  if (!descricao.value.trim()) {
    return alert("Digite a Descrição do Produto.");
  }

  if (!tipoProduto.value.trim()) {
    return alert("Selecione o Tipo do Produto.");
  }

  if (valorFornecedor.value <= 0) {
    return alert("Valor do Fornecedor deve ser maior que zero.");
  }

  const produtoExiste = produtos.value.some(
    (produto: Produto) =>
      produto.descricao === descricao.value.toUpperCase() &&
      produto.tipoProduto === tipoProduto.value &&
      produto.codigo !== codigo.value,
  );

  if (produtoExiste) {
    return alert("Produto Já Cadastrado com o mesmo Tipo Produto.")
  }

  if (editarCadastro.value === false) {
    const data = {
      descricao: descricao.value.trim().toUpperCase(),
      tipoProduto: tipoProduto.value,
      valorFornecedor: valorFornecedor.value,
      quantidadeEstoque: 0
    }

    await api.post("produto", data)
    .then((response) => {
      if (response.data.codigo > 0) {
        alert("Produto Cadastrado com sucesso.")
      }
    })
    .catch((error) => {
      alert(`Erro Salvar: ${error.message}`);
      console.error(`Erro Salvar: ${error.message}`);
    })

    editarCadastro.value = false;
    limparCamposCadastro();
    listarProdutos();

  } else {

    const data = {
      codigo: codigo.value,
      descricao: descricao.value.trim().toUpperCase(),
      tipoProduto: tipoProduto.value,
      valorFornecedor: valorFornecedor.value,
      quantidadeEstoque: quantidadeEstoque.value
    }

    await api.put(`produto/alterar/${codigo.value}`, data)
    .then((response) => {
      if (response.data.codigo > 0) {
        alert("Produto Alterado com sucesso.")
      }
    })
    .catch((error) => {
      alert(`Erro Alteração: ${error.message}`);
      console.error(`Erro Alteração: ${error.message}`)
    })

    editarCadastro.value = false;
    limparCamposCadastro();
    listarProdutos();
  }
}

function cancelar(e: { preventDefault: () => void }) {
  e.preventDefault();
  editarCadastro.value = false;
  limparCamposCadastro();
}

async function excluir(produto: Produto) {

  if (confirm(`Deseja excluir produto ${produto.codigo} - ${produto.descricao} ?
    \n\nATENÇÃO: Todos os registros de movimentação de estoque também serão excluídos`)) {

    await api.delete(`produto/excluir/${produto.codigo}`, {
      params: {
        codigo: produto.codigo
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
      :handleInputDescricao="handleInputDescricao"
      :optionChangeTipoProduto="optionChangeTipoProduto"
      :handleInputValorFornecedor="handleInputValorFornecedor"
      :novo="novoCadastro"
      :salvar="salvar"
      :cancelar="cancelar"/>

    <CadastroProdutoTabela
      :produtos="produtos"
      :consultar="consultar"
      :limpar="limparcamposFiltros"
      :editar="editar"
      :excluir="excluir"
      :filtroDescricao="filtroDescricao"
      :filtroTipoProduto="filtroTipoProduto"
      :handleInputFiltroDescricao="handleInputFiltroDescricao"
      :optionChangeFiltrotipoPriduto="optionChangeFiltroTipoProduto"/>
  </div>
</template>
