<template>
  <div>
    <div id="background-container">
      <div class="clickable-area blink" @click="goBack" :style="{
        '--blink-delay': `${blinkDelay}s`,
        top: '0px',
        left: '0px',
        width: '50px',
        height: '100vh',
      }"></div>
    </div>

    <div class="alert-container" v-if="alertMessage !== ''">
      {{ alertMessage }}
    </div>

    <div id="content-container">
      <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
        <input type="text" v-model="generalTerm" placeholder="Otsi teoseid..." @keyup.enter="fetchWorks" />
        <button @click="fetchWorks" style="margin-left: 10px;">OTSI</button>
      </div>

      <div class="table-container" v-if="works.length > 0">
        <div class="table-wrapper">
          <table>
            <thead>
              <tr>
                <th v-for="key in visibleColumns" :key="key">{{ getColumnLabel(key) }}</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in works" :key="index">
                <td v-for="key in visibleColumns" :key="key">
                  {{ transformValue(item[key]) }}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      blinkDelay: 2,
      generalTerm: "",
      works: [],
      alertMessage: ""
    };
  },
  computed: {
    visibleColumns() {
      if (this.works.length === 0) return [];

      const keys = Object.keys(this.works[0]);
      return keys.filter((key) => {
        return this.works.some((work) => {
          const value = work[key];
          return value !== null && value !== undefined && value !== false && String(value).trim() !== "";
        });
      });
    }
  },
  methods: {
    async fetchWorks() {
      try {
        const response = await axios.get(`${process.env.VUE_APP_BACKEND_URL}/get-works-general`, {
          params: { generalTerm: this.generalTerm },
        });

        if (response.data && response.data.works) {
          this.works = response.data.works;
          if (response.data.works.length === 0) {
            this.alertMessage = "See otsisõna ei andnud tulemusi";
            setTimeout(() => {
              this.alertMessage = "";
            }, 5000);
          }
        } else {
          this.works = [];
        }
      } catch (error) {
        console.error("Error fetching works:", error);
      }
    },
    getColumnLabel(key) {
      const labels = {
        name: "Nimi",
        music: "Muusika autor",
        lyrics: "Teksti autor",
        samples: "Sämplite autor",
        info: "Info",
        length: "Pikkus",
        year: "Aasta",
        style: "Stiil",
        instruments: "Koosseis",
        mood: "Meeleolu",
        sheet: "Noodistus",
        arr: "Arranžeering",
        collection: "Osa kogumikust",
        fixedMedia: "Fixed Media",
        recBy: "Salvestaja",
        premiereBy: "Esmaesitaja",
        premiereLoc: "Esmaesituse koht",
        alternName: "Alternatiivnimi",
        band: "Seotus bändiga",
        authorRating: "Autori hinnang",
      };
      return labels[key] || key;
    },
    transformValue(value) {
      if (value === false) {
        return "Ei";
      }
      if (value === true) {
        return "Jah";
      }
      return value; // Default: Return the original value
    },
    goBack() {
      this.$router.go(-1);
    }
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
  background-image: url('@/assets/lib7.webp') !important;
  background-size: cover !important;
  background-repeat: no-repeat !important;
  background-position: center center !important;
  background-attachment: fixed !important;
  margin: 0 !important;
  overflow: hidden !important;
}

#content-container {
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  padding: 20px;
  background: rgba(255, 255, 255, 0.8);
  width: auto;
  min-width: 200px;
  max-width: 90%;
  max-height: calc(100vh - 40px - 10%);
  box-sizing: border-box;
}

input {
  margin-right: 10px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 20px;
}

button {
  padding: 5px 10px;
  border: none;
  background-color: #966606;
  color: white;
  border-radius: 20px;
  cursor: pointer;
}

button:hover {
  background-color: #b36b00;
}

.table-container {
  flex-grow: 1;
  overflow-y: hidden;
  margin-top: 20px;
  max-width: 100%;
  position: relative;
}

.table-wrapper {
  overflow-x: auto;
  max-height: calc(100vh - 200px);
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

.table-container::after {
  content: "";
  display: block;
  height: 16px;
}

.table-wrapper::-webkit-scrollbar {
  height: 16px;
}

.table-wrapper::-webkit-scrollbar-thumb {
  background-color: #8f733e;
  border-radius: 5px;
}

.alert-container {
  color: rgb(224, 221, 221);
  background-color: #812727;
  padding: 8px;
  text-align: center;
  border-radius: 8px;
  margin-top: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: fit-content;
  margin-left: auto;
  margin-right: auto;
  z-index: 3;
  position: relative;
}
</style>
