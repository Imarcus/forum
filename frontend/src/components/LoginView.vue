<template>
<section class="section">
  <div class="loginview container" v-if="!$store.getters.isLoggedIn">
    <div class="box">
    <h2 class="title is-2">Login</h2>
    <label class="label">Username</label>
    <div class="control has-icons-left has-icons-right">
      <input class="input is-success" type="text" placeholder="Text input" v-model="userNameInput">
      <span class="icon is-small is-left">
        <i class="fas fa-user"></i>
      </span>
      <span class="icon is-small is-right">
        <i class="fas fa-check"></i>
      </span>
    </div>
    <label class="label">Password</label>
    <div class="field">
      <p class="control has-icons-left">
        <input class="input" type="password" placeholder="Password" v-model="passwordInput">
        <span class="icon is-small is-left">
          <i class="fas fa-lock"></i>
        </span>
      </p>
    </div>
    <div class="control">
      <button class="button is-primary" @click="login()">Login</button>
    </div>
    </div>
  </div>
  <div class="container" v-else>
    <div class="control">
      <button class="button is-primary" @click="logout()">Log out</button>
    </div>
  </div>
</section>
</template>

<script>
import client from 'api-client'

export default {
  name: 'LoginView',
  data () {
    return {
      users: [],
      userNameInput: '',
      passwordInput: ''
    }
  },
  methods: {
    login () {
      console.log('Log in try with: ' + this.userNameInput + ' and ' + this.passwordInput)
      client.login(this.userNameInput, this.passwordInput).then(loginUser => {
        if (loginUser) {
          this.$store.commit('SET_USER', loginUser)
          console.log('SUCCESS')
        }
      })
    },
    logout () {
      this.$store.commit('SET_USER', { id: '0' })
    }
  },
  created () {
    client.fetchUsers().then(users => { this.users = users })
  }
}
</script>

<style>
</style>
