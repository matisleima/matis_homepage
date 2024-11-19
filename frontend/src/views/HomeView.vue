<template>
  <div>
    <!--    <h1>HOME</h1>-->

    <div id="background-container">
      <!--PUU-->
      <div class="clickable-area" @click="goToView('musicRoute')"
           style="top: 0.9%; left: 3%; width: 50%; height: 50%;"></div>
      <!--KIRST-->
      <div class="clickable-area" @click="goToView('portfolioRoute')"
           style="top: 77%; left: 18%; width: 7.5%; height: 9%;"></div>
      <!--PÄIKE-->
      <div class="clickable-area" @click="goToView('gamesRoute')"
           style="top: 49.5%; left: 51.8%; width: 4%; height: 7.3%;"></div>
      <!--PÕÕSAS-->
      <div class="clickable-area" @click="goToView('bioRoute')"
           style="top: 29.5%; left: 90.2%; width: 6%; height: 28.2%;"></div>
      <!--PAREMPOOLNE INIMENE-->
      <div class="clickable-area" @click="goToView('shopRoute')"
           style="top: 70.5%; left: 49.2%; width: 6%; height: 18.2%;"></div>

    </div>


  </div>
</template>

<script>

export default {
  methods: {
    goToView(viewName) {
      this.$router.push({name: viewName});
    }
  }
}

</script>

