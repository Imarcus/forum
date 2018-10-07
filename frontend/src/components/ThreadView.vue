<template>
  <div class="threadview">
    <span v-if="loading">Loading…</span>
    <div v-else class="box align-left" >
      <h2 class="title is-2">{{ thread.mainPost.title }}</h2>
      <strong>{{ getUserWithIdFromThread(this.thread.mainPost.userId ).name }}</strong>
      <article class="media ">
        <figure class="media-left">
          <p class="image is-64x64">
            <img src="https://bulma.io/images/placeholders/128x128.png">
          </p>
        </figure>
        <div class="media-content">
          <div class="content">
            <p>
              <br>
                {{ thread.mainPost.content }}
              <br>
              <small><a>Like</a> · <a>Reply</a> · 3 hrs</small>
            </p>
          </div>
          <answer v-for="answer in thread.answers" :key="answer.id" :answer="answer" :user="getUserWithIdFromThread(answer.userId)"></answer>
        </div>
      </article>
      <article class="media" v-if="$store.getters.isLoggedIn">
        <figure class="media-left">
          <p class="image is-64x64">
            <img src="https://bulma.io/images/placeholders/128x128.png">
          </p>
        </figure>
        <div class="media-content">
          <div class="field">
            <p class="control">
              <textarea class="textarea" placeholder="Add a comment..." v-model="answerInput"></textarea>
            </p>
          </div>
          <div class="field">
            <p class="control">
              <button class="button" @click="postAnswer">Post comment</button>
            </p>
          </div>
        </div>
      </article>
      <div v-else>
        <nav class="level">
          <div class="level-item has-text-centered">
            <p class="title">LOG IN TO POST</p>
          </div>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
import client from 'api-client'
import PostView from './PostView'
import AnswerView from './AnswerView'

export default {
  name: 'ThreadView',
  components: {
    'post': PostView,
    'answer': AnswerView
  },
  data () {
    return {
      loading: true,
      answerInput: '',
      thread: {},
      users: []
    }
  },
  created () {
    this.getThread()
  },
  methods: {
    postAnswer () {
      client
        .putAnswerToThread(this.thread.id, { content: this.answerInput, userId: this.$store.state.user.id })
        .then(() => {
          this.getThread()
          this.answerInput = ''
        })
    },
    getThread () {
      client.fetchThreadById(this.$route.params.threadId)
        .then(thread => {
          this.thread = thread
          client.fetchUsersByIds(this.getUserIdsFromThread())
            .then(users => {
              this.users = users
              this.loading = false
            })
        })
    },
    getUserIdsFromThread () {
      const userIds = this.thread.answers.map(answer => answer.userId)
      userIds.push(this.thread.mainPost.userId)
      return [...new Set(userIds)]
    },
    getUserWithIdFromThread (userId) {
      return this.users.filter(user => user.id === userId)[0]
    }
  }
}
</script>

<style scoped>
.align-left {
  text-align: left;
}
</style>
