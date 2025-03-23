<template>
  <div id="app">
    <!-- Audio Element -->
    <audio ref="backgroundAudio" autoplay loop muted>
      <source src="@/assets/background.mp3" type="audio/mpeg" />
    </audio>

    <!-- Mute Button -->
    <button id="mute-button" @click="toggleMute">
      <img :src="isMuted ? require('@/assets/mute.png') : require('@/assets/unmute.png')" alt="Mute/Unmute" />
    </button>

    <!-- Router View for Dynamic Content -->
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      isMuted: true, // Start muted to comply with autoplay policies
      hasUnmutedOnce: false, // Ensure we only unmute on the first click
    };
  },
  methods: {
    unmuteOnFirstClick() {
      const audio = this.$refs.backgroundAudio;

      if (audio && this.isMuted) {
        audio.muted = false;
        this.isMuted = false;
        this.hasUnmutedOnce = true;

        // Attempt to play the audio
        audio.play().catch((err) => {
          console.warn("Audio playback failed:", err);
        });
      }
    },
    toggleMute() {
      const audio = this.$refs.backgroundAudio;

      if (audio) {
        this.isMuted = !this.isMuted;
        audio.muted = this.isMuted;

        if (!this.isMuted) {
          audio.play().catch((err) => console.warn("Audio playback failed", err));
        }
      }
    },
  },
  mounted() {
    // Add a one-time global event listener for the first click
    window.addEventListener("click", this.unmuteOnFirstClick, { once: true });
  },
}
</script>


<style scoped>
#mute-button {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
  background-color: transparent;
  border: none;
  cursor: pointer;
}

#mute-button img {
  width: 24px;
  height: 24px;
}
</style>