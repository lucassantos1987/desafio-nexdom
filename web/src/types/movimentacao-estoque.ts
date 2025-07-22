export interface MovimentacaoEstoque {
  codigo: number;
  codigoProduto: number;
  descricaoProduto: string;
  tipoMovimentoEstoque: string;
  quantidadeMovimentada: number;
  estoqueAnterior: number;
  estoqueDisponivel: number;
  valorVenda: number;
  dataVenda: string;
}
