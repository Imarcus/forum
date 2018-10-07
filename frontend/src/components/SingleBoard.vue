<template>
<div class="container">
  <nav class="panel">
    <p class="panel-heading">
      {{ board.name }}
      <button class="button" @click="toggleBoardModal()">Post Thread </button>
    </p>
    <ul v-for="thread in threads" :key="thread.id">
      <a class="panel-block is-active">
        <span class="panel-icon">
          <i class="fas fa-book" aria-hidden="true"></i>
        </span>
        <router-link :to="{ name: 'ThreadView', params: { threadId: thread.id}}" tag="h2">{{thread.mainPost.title}}</router-link>
      </a>
    </ul>
  </nav>
  <div class="modal" :class="{ 'is-active': isCreateThreadModalActive }">
    <div class="modal-background" @click="toggleBoardModal()"></div>
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">Post thread in {{ board.name }}</p>
        <button class="delete" aria-label="close" @click="toggleBoardModal()"></button>
      </header>
      <section v-if="!$store.getters.isLoggedIn" class="modal-card-body">Log in to post</section>
      <section v-else class="modal-card-body">
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">Subject</label>
          </div>
          <div class="field-body">
            <div class="field">
              <div class="control">
                <input class="input" type="text" placeholder="Title of submission" v-model="threadTitle">
              </div>
            </div>
          </div>
        </div>

        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">Question</label>
          </div>
          <div class="field-body">
            <div class="field">
              <div class="control">
                <textarea class="textarea" placeholder="Explain your problem" v-model="threadContent"></textarea>
              </div>
            </div>
          </div>
        </div>
      </section>
      <footer class="modal-card-foot">
        <button class="button is-success" @click="postThread()" v-if="$store.getters.isLoggedIn">Post thread</button>
        <button class="button" @click="toggleBoardModal()">Cancel</button>
      </footer>
    </div>
  </div>
</div>
</template>

<script>
import client from 'api-client'

export default {
  name: 'SingleBoard',
  props: {
    board: Object
  },
  data () {
    return {
      isCreateThreadModalActive: false,
      threads: [],
      threadTitle: '',
      threadContent: ''
    }
  },
  methods: {
    postThread () {
      const thread = {
        mainPost: {
          title: this.threadTitle,
          content: this.threadContent,
          userId: this.$store.getters.getUser.id
        }
      }
      client.createThread(thread, this.board.id).then(() => { this.getThreads() })
      this.toggleBoardModal()
    },
    toggleBoardModal () {
      this.isCreateThreadModalActive = !this.isCreateThreadModalActive
    },
    getThreads () {
      client.fetchThreadsByBoardId(this.board.id).then(threads => { this.threads = threads })
    }
  },
  created () {
    this.getThreads()
  }
}
</script>

<style>
</style>
