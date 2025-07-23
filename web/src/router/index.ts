import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/cadastro-produto',
      name: 'cadastro-produto',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/CadastroProdutoView.vue'),
    },
    {
      path: '/movimentacao-estoque',
      name: 'movimentacao-estoque',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/MovimentacaoEstoqueView.vue'),
    },
    {
      path: '/consulta-lucro-produto',
      name: 'consulta-lucro-produto',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ConsultaLucroProdutoView.vue'),
    },
    {
      path: '/consulta-produto-tipo',
      name: 'consulta-lucro-tipo',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ConsultaProdutoPorTipoView.vue'),
    },

  ],
})

export default router
