<template>
  <div>
    <div id="background-container"></div>

    <div id="content-container">
      <h1>TAGASI</h1>

      <!-- Input field and SEND button -->
      <div>
        <input type="text" v-model="generalTerm" placeholder="Mis sind huvitab?" @keyup.enter="fetchProducts" />
        <button @click="fetchProducts">OTSI</button>
      </div>

      <!-- Scrollable Table -->
      <div class="table-container">
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
              <td>{{ product.price || "-" }}</td>
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
  },
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
  position: relative;
  z-index: 2;
  padding: 20px;
  background: rgba(255, 255, 255, 0.8);
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
  background-color: #007bff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.table-container {
  margin-top: 20px;
  max-height: 400px;
  /* Set the height of the scrollable container */
  overflow-y: auto;
  /* Enable vertical scrolling */
  border: 1px solid #ddd;
  /* Optional: Add a border for the scrollable area */
}

table {
  border-collapse: collapse;
  width: 100%;
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
