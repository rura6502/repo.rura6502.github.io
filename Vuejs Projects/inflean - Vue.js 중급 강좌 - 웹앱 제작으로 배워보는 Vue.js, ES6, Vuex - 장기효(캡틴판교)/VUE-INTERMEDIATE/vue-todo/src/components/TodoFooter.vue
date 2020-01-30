<template>
  <div class="clearAllContainer">
    <span class="cearAllBtn" v-on:click="clearTodo">Clear All</span>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'

export default {

  methods: {
    ...mapMutations({clearTodo: 'clearAllItems'})
    // clearTodo() {
    //   this.$store.commit('clearAllItems');

    // }
  }

}
</script>

<style scoped>
.clearAllContainer {
  width: 8.5rem;
  height: 50px;
  line-height: 50px;
  background-color: white;
  border-radius: 5px;
  margin: 0 auto;
}
.cearAllBtn {
  color: #e20303;
  display: block;
}
</style>