<template>

  <div class="row">
    <div class="col" style="padding: 5%;">
      <div style="
        color: aliceblue;
        display: flex;
        justify-content: center;
        align-items: center;
        text-align: left;
        padding-top: 5vh;
  padding-bottom: 5vh;">
        Matis Leima alustas muusikuteed Saku Muusikakoolis, jätkas kompositsiooni erialal Georg Otsa nimelises Tallinna
        Muusikakoolis ja lõpetas 2020. aastal Eesti Muusika- ja Teatriakadeemia elektroakustilise heliloomingu eriala
        cum laude. Ta on õppinud viiulit, klaverit, trompetit ning omal käel karmoškat ning on olnud erinevates laste-
        ja noortelaagrites pilliõpetaja, juhendanud ansambleid koosmängu ja arranžeerimise alal. Matis õpetas kümme
        aastat Tartu eramuusikakoolis Helivõlu ning juhendas hulgaliselt eraõpilasi. Matis teenis aega Kaitseväe
        Orkestri juures, on õppinud TÜ Viljandi Kultuuriakadeemias pärimusmuusikat ning lõpetas 2016. aastal õpingud
        Tartu Ülikoolis ajaloo erialal cum laude. 2023. aastal alustas pärast täiendkoolitust tööd tarkvaraarendajana.
        Matis mängib ansamblites Kiiora, Zetod, Senat, H6im ja Tribute to K-Rose ning püüab oma loomingulise tegevusega
        stiilipiire lõhkudes mõista muusika fenomeni selle kõige laiemas tähenduses.
      </div>

      <div class="row">

      </div>

      <div class="row" style="
        color: aliceblue;
        display: flex;
        justify-content: center;
        align-items: center;
        text-align: center;">
        <div class="col">
          <h3><a href="https://setoodysseia.ee" target="_blank" style="color: inherit; text-decoration: none;">seto
              odüsseia</a></h3>
        </div>

        <div class="col">
          <h3><a href="https://folk.ee/et/pood/tooted/karmoska-aabits-2023" target="_blank"
              style="color: inherit; text-decoration: none;">
              karmoškaaabits
            </a></h3>
        </div>
      </div>
    </div>

    <div class="col p-0 m-0" style="overflow: hidden;">
      <img src="@/assets/Maris-Savik_SENAT-GEN_WEB-075.webp" alt="matis"
        style="height:100vh; width:100%; object-fit:cover;">
    </div>

  </div>
</template>

<script>
export default {
  methods: {
    goToView(viewName) {
      this.$router.push({ name: viewName });
    }
  }
}

</script>

<style scoped></style>
