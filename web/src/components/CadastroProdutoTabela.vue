<template>
  <div class="mt-4">
    <table class="w-full">
      <thead>
        <tr class="bg-emerald-600 h-[60px] text-white ">
          <th align="center">Código</th>
          <th align="left">Descrição</th>
          <th align="left">Tipo Do Produto</th>
          <th align="right">Valor Do Fornecedor</th>
          <th align="right" class="pr-1">Quantidade Em Estoque</th>
          <th align="center">Editar</th>
          <th align="center">Excluir</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th align="center">1</th>
          <th align="left">PLAYSTATION 5 SLIM 1TB</th>
          <th align="left">ELETRONICO</th>
          <th align="right">4999.00</th>
          <th align="right" class="pr-1">100</th>
          <th align="center">
            <button
              class="w-full h-10 bg-emerald-600 hover:bg-emerald-700 transition-colors duration-300 text-white rounded-[8px] cursor-pointer">
              Editar
            </button>
          </th>
          <th align="center">
            <button
              class="w-full h-10 bg-emerald-600 hover:bg-emerald-700 transition-colors duration-300 text-white rounded-[8px] cursor-pointer">
              Excluir
            </button>
          </th>
        </tr>
      </tbody>
    </table>
  </div>
</template>
