<template>
  <div>
    <div id="background-container">
      <div class="clickable-area blink" @click="goBack" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '0px',
        left: '0px',
        width: '50px',
        height: '100vh',
      }">
      </div>
    </div>

    <div id="content-container">

      <!-- Input field and SEND button -->
      <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
        <input type="text" v-model="generalTerm" placeholder="Mis sind huvitab?" @keyup.enter="fetchProducts" />
        <button @click="fetchProducts" style="margin-left: 10px;">OTSI</button>
      </div>



      <!-- Scrollable Table -->
      <div class="table-container" v-if="products[0].name !== ''">
        <table>
          <thead>
            <tr>
              <th>Nimi</th>
              <th>Tüüp</th>
              <th>Ühik</th>
              <th>Kirjeldus</th>
              <th>Hind</th>
              <th>Saadaval</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(product, index) in products" :key="index">
              <td>{{ product.name || "-" }}</td>
              <td>{{ product.type || "-" }}</td>
              <td>{{ product.unit || "-" }}</td>
              <td>{{ product.description || "-" }}</td>
              <td>{{ typeof product.price === 'number' ? product.price + "€" : product.price || "-" }}</td>
              <td>{{ product.inStock ? "Jah" : "Ei" }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      blinkDelay: 2, // Fixed initial delay in seconds
      generalTerm: "", // Binds to the input field
      products: [
        {
          name: "",
          type: "",
          unit: "",
          description: "",
          price: "",
          inStock: false,
          tags: "",
        },
      ], // Holds all product data
    };
  },
  methods: {
    async fetchProducts() {
      try {
        // Reset the products to default blank values
        this.products = [
          {
            name: "",
            type: "",
            unit: "",
            description: "",
            price: "",
            inStock: false,
            tags: "",
          },
        ];

        const response = await axios.get(`${process.env.VUE_APP_BACKEND_URL}/get-products-general`, {
          params: { generalTerm: this.generalTerm },
        });

        if (response.data && response.data.products) {
          // Update the products array with the API response
          this.products = response.data.products;
        } else {
          this.products = []; // Reset to empty if no products found
        }
      } catch (error) {
        console.error("Error fetching products:", error);
        if (
          confirm(
            "Tohoo till ae! Midagi läks valesti! Nüüd oleks paras aeg imestushüüatuste kohta lähemalt lugeda."
          )
        ) {
          window.open(
            "https://www.folklore.ee/justkui/sonastik/index.php?f=2&f1=2&f2=12&m=952&id=1003",
            "_blank"
          );
        }
      }
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
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 1;
  background-image: url('@/assets/lett1.webp') !important;
  background-size: cover !important;
  background-repeat: no-repeat !important;
  background-position: center center !important;
  background-attachment: fixed !important;
  margin: 0 !important;
  overflow: hidden !important;
}

#content-container {
  position: absolute;
  top: 56%;
  /* Place the container in the lower part of the screen */
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  padding: 20px;
  background: rgba(255, 255, 255, 0.8);
  width: auto;
  /* Allow the width to adjust dynamically */
  min-width: 200px;
  max-width: 80%;
  max-height: calc(100vh - 40px - 56%);
  /* Limit the height based on the remaining space */
  overflow-y: auto;
  /* Enable scrolling if content exceeds viewable height */
  box-sizing: border-box;
  /* Includes padding in height calculation */
}

input {
  margin-right: 10px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 5px 10px;
  border: none;
  background-color: #ecc900;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.table-container {
  flex-grow: 1;
  /* Allows the table container to take up remaining space */
  overflow-y: auto;
  /* Enable scrolling if the table content overflows */
  overflow-x: hidden;
  /* Prevent horizontal scrolling */
  margin-top: 20px;
}


table {
  border-collapse: collapse;
  width: 100%;
  margin-bottom: 40px;

}

table,
th,
td {
  border: 1px solid #ddd;
}

th,
td {
  padding: 8px;
  text-align: left;
}
</style>
