<template>
<nav class="navbar">
  <div class="navbar-brand">
    <div class="navbar-item">
      <h3 class="title is-3">Forum</h3>
    </div>
    <div class="navbar-item is-tab">
      <router-link to="/">Boards</router-link>
    </div>
    <div class="navbar-item is-tab"  v-if="!$store.getters.isLoggedIn">
      <router-link to="/login">Login</router-link>
    </div>
    <div class="navbar-item is-tab" v-else>
      <span class="icon">
        <i class="fas fa-user"></i>
      </span>
      <p> {{ $store.state.user.username }} </p>
      <router-link to="/login"> Log out</router-link>
    </div>
  </div>

</nav>
</template>
