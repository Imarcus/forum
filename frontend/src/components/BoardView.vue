<template>
<section class="section">
  <div class="boardview">
    <span v-if="loading">Loading…</span>
    <ul v-else class="boards">
      <li
        v-for="board in boards"
        :key="board.id"
        class="board-item">
        <single-board :board="board"></single-board>
      </li>
    </ul>
  </div>
</section>
</template>

<script>
import SingleBoard from './SingleBoard'
import client from 'api-client'

export default {
  name: 'BoardView',
  components: {
    'single-board': SingleBoard
  },
  data () {
    return {
      loading: false,
      boards: []
    }
  },
  created () {
    this.loading = true
    client.fetchBoards().then(boards => {
      this.boards = boards
      this.loading = false
    })
  }
}
</script>

<style scoped>
.boards {
  list-style: none;
  text-align: left;
}
.posts {
  list-style: none;
  text-align: left;
}

.post-item + .post-item {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
