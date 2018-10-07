<template>
  <div>
    <article class="media">
        <figure class="media-left">
          Barbara Middleton
          <p class="image is-64x64">
            <img src="https://bulma.io/images/placeholders/128x128.png">
          </p>
        </figure>
        <div class="media-content">
          <div class="content">
            <p>
              <br>
                {{ post.content }}
              <br>
              <small><a>Like</a> · <a>Reply</a> · 3 hrs</small>
            </p>
          </div>
          <answer v-for="answer in thread.answers" :key="answer.id" :answer="answer"></answer>
        </div>
    </article>
  </div>
</template>

<script>
export default {
  name: 'PostView',
  props: {
    post: Object
  }
}
</script>

<style scoped>
.posts {
  list-style: none;
  text-align: left;
}

.post-item + .post-item {
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
</style>
