<template>
  <div>

    <div id="background-container"></div>

    <div id="content-container">
      <h1>GAMES</h1>

    </div>


  </div>
</template>

<script>

</script>

<style scoped>
#background-container {
  position: fixed; /* or 'absolute' */
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 1; /* Ensure this is below the content container */
  background-image: url('@/assets/chest1.png') !important;
  background-size: cover !important;
  background-repeat: no-repeat !important;
  background-position: center center !important;
  background-attachment: fixed !important;
  margin: 0 !important;
  overflow: hidden !important; /* To prevent scrolling if not needed */
}

#content-container {
  position: relative;
  z-index: 2; /* Higher than background container */
}

</style>