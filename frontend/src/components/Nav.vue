<template>
<nav class="navbar">
  <div class="navbar-brand">
    <div class="navbar-item">
      <h3 class="title is-3" style="color: white;">Forum</h3>
    </div>
    <div class="navbar-item is-tab">
      <router-link to="/" class="nav-link">Boards</router-link>
    </div>
    <div class="navbar-item is-tab"  v-if="!$store.getters.isLoggedIn">
      <router-link to="/login" class="nav-link">Login</router-link>
    </div>
    <div class="navbar-item is-tab" v-else>
      <span class="icon">
        <i class="fas fa-user"></i>
      </span>
      <p> {{ $store.state.user.username }} </p>
      <router-link to="/login" class="nav-link"> Log out</router-link>
    </div>
  </div>

</nav>
</template>

<style>
.navbar {
  background: #9825ce;
}

.nav-link {
  color: white;
}

.nav-link:hover {
  color:rgb(252, 223, 255);
}
</style>
