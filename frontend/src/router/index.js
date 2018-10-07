import Vue from 'vue'
import Router from 'vue-router'
import BoardView from '@/components/BoardView'
import ThreadView from '@/components/ThreadView'
import LoginView from '@/components/LoginView'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'BoardView',
      component: BoardView
    },
    {
      path: '/thread/:threadId',
      name: 'ThreadView',
      component: ThreadView
    },
    {
      path: '/login',
      name: 'LoginView',
      component: LoginView
    }

  ]
})
