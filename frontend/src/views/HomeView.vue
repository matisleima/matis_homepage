<template>
  <div>
    <div id="background-container">
      <div class="clickable-area blink" @click="goToView('shopRoute')" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '65%',
        left: '65%',
        right: '10%',
        width: '20vw',
        height: '25vh'
      }">
      </div>

      <div class="clickable-area blink" @click="goToView('bioRoute')" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '53%',
        left: '46%',
        right: '40%',
        width: '40px',
        height: '60px'
      }">
      </div>

      <div class="clickable-area blink" @click="goToView('musicRoute')" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '6%',
        left: '40%',
        right: '40%',
        width: '250px',
        height: '300px'
      }">
      </div>

      <div class="clickable-area blink" @click="goToView('gamesRoute')" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '40%',
        left: '68%',
        right: '40%',
        width: '100px',
        height: '150px'
      }">
      </div>

      <div class="clickable-area blink" @click="goToView('artRoute')" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '60%',
        left: '2%',
        right: '40%',
        width: '200px',
        height: '250px'
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
    goToView(viewName) {
      this.$router.push({ name: viewName });
    }
  }
}

</script>
