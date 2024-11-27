<template>
  <div>
    <!--    <h1>HOME</h1>-->

    <div id="background-container">
      <!--PUU-->
      <div class="clickable-area" @click="goToView('shopRoute')"
           style="top: 65%; left: 62%; right: 10%; width: 25%; height: 25%;"></div>
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

