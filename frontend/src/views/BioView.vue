<template>
  <div>

    <div id="background-container">
      <div class="clickable-area blink" @click="goBack" :style="{
        '--blink-delay': `${blinkDelay}s`,
        bottom: '0px',
        width: '100vw',
        height: '30px',
      }">
      </div>

      <div class="clickable-area blink" @click="bioAlert" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '83%',
        left: '44%',
        width: '11%',
        height: '13%',
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
    bioAlert() {
      alert('Matis Leima alustas muusikuteed Saku Muusikakoolis, jätkas kompositsiooni erialal Georg Otsa nimelise Tallinna Muusikakoolis ja lõpetas 2020. aastal Eesti Muusika- ja Teatriakadeemia elektroakustilise heliloomingu eriala cum laude. Ta on õppinud viiulit, klaverit, trompetit ning omal käel karmoškat ning on olnud erinevates laste- ja noortelaagrites pilliõpetaja, juhendanud ansambleid koosmängu ja arranžeerimise alal. Praegu on Matis õpetaja Tartu eramuusikakoolis Helivõlu ning juhendab hulgaliselt eraõpilasi. Matis teenis aega Kaitseväe Orkestri juures, on õppinud TÜ Viljandi Kultuuriakadeemias pärimusmuusikat ning lõpetas 2016. aastal õpingud Tartu Ülikoolis ajaloo erialal cum laude. Matis mängib seto folkansamblites Kiiora ja Zetod.');
    },
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
#background-container {
  position: fixed;
  /* or 'absolute' */
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 1;
  /* Ensure this is below the content container */
  background-image: url('@/assets/kuju3.webp ') !important;
  background-size: cover !important;
  background-repeat: no-repeat !important;
  background-position: center center !important;
  background-attachment: fixed !important;
  margin: 0 !important;
  overflow: hidden !important;
  /* To prevent scrolling if not needed */
}

#content-container {
  position: relative;
  z-index: 2;
  /* Higher than background container */
}
</style>