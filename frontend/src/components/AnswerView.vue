<template>
  <div class="answerview">
    <article class="media">
      <figure class="media-left">
        <strong>{{ this.user.name }}</strong>
        <p class="image is-48x48">
          <img src="https://bulma.io/images/placeholders/96x96.png">
        </p>
      </figure>
      <div class="media-content">
        <div class="content">
          <p>
            <br>
            {{ this.answer.content }}
            <br>
            <small><a>Like</a> · <a>Reply</a> · 2 hrs</small>
          </p>
        </div>
      </div>
    </article>
  </div>
</template>

<script>
export default {
  name: 'AnswerView',
  props: {
    answer: Object,
    user: Object
  }
}
</script>
