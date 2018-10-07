import Vue from 'vue'
import Vuex from 'vuex'
import client from 'api-client'
import {SET_THREAD, SET_BOARDS, SET_THREADS, SET_ALL_THREADS, SET_USER} from './mutation-types'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    posts: [],
    boards: [],
    thread: {},
    threads: [],
    user: {
      id: '0'
    }
  },

  getters: {
    getBoardById: (state) => (id) => {
      return state.boards.find(board => board.id === id)
    },
    getThreadsByBoardId: (state) => (id) => {
      const board = state.boards.find(board => board.id === id)
      return state.threads.filter(thread => board.threadIds.includes(thread.id))
    },
    getUser: state => {
      return state.user
    },
    isLoggedIn: state => {
      return state.user.id !== '0'
    }
  },

  mutations: {
    [SET_BOARDS] (state, boards) {
      state.boards = boards
    },
    [SET_THREAD] (state, thread) {
      state.thread = thread
    },
    [SET_THREADS] (state, threads) {
      state.threads = state.threads.concat(threads)
    },
    [SET_ALL_THREADS] (state, threads) {
      state.threads = threads
    },
    [SET_USER] (state, user) {
      state.user = user
    }
  },

  actions: {
    fetchBoards ({ commit }) {
      client
        .fetchBoards()
        .then(boards => commit('SET_BOARDS', boards))
    },
    fetchThreadById ({ commit }, id) {
      client
        .fetchThreadById(id)
        .then(thread => commit('SET_THREAD', thread))
    },
    fetchThreads ({ commit }) {
      client
        .fetchThreads()
        .then(threads => commit('SET_ALL_THREADS', threads))
    },
    fetchThreadsByBoardId ({ commit }, ids) {
      client
        .fetchThreadsByBoardId(ids)
        .then(threads => commit('SET_THREADS', threads))
    }
  }
})
