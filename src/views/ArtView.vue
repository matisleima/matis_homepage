<template>
  <div>
    <div id="background-container">
      <div class="clickable-area blink" @click="goBack" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '0px',
        right: '0px',
        width: '50px',
        height: '100vh',
      }">
      </div>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      blinkDelay: 2, // Fixed initial delay in seconds
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
#background-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 1;
  background-image: url('@/assets/art2.webp') !important;
  background-size: cover !important;
  background-repeat: no-repeat !important;
  background-position: center center !important;
  background-attachment: fixed !important;
  margin: 0 !important;
  overflow: hidden !important;
}
</style>