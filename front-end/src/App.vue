<template>
  <div class="">
    <div>

    </div>
    <div class="container d-flex align-items-center justify-content-center">
      <div class="card shadow position-relative">
        <table class="table table-light">
          <thead>
            <tr class="text-center">
              <th scope="col">Nome</th>
              <th scope="col">Prezzo</th>
              <th scope="col">Azioni</th>
            </tr>
          </thead>
          <tbody>
            <tr class="text-center" v-for="pizza in pizze" :key="pizza.id">
              <td> {{ pizza.nome }}</td>
              <td>{{ pizza.prezzo }}&euro;</td>
              <td class="d-flex align-items-center justify-content-center">
                <a class="btn btn-warning text-light mx-2">
                  <i class="fa-solid fa-eye"></i>
                </a>
                <a class="btn btn-primary mx-2">
                  <i class="fa-solid fa-pencil"></i>
                </a>
                <form>
                  <button class="btn btn-danger mx-2" type="submit"><i class="fa-solid fa-trash"></i></button>
                </form>
              </td>

            </tr>
          </tbody>
        </table>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from "axios";

const API_URL = "http://localhost:8080/api/v1.0"

const pizze = ref(null);

function getAllPizzas() {

  axios.get(API_URL)
    .then(res => {

      const data = res.data;
      console.log(data);

      pizze.value = data;
    })
    .catch(err => console.log(err));
}

onMounted(() => {
  getAllPizzas();
})
</script>


<style lang="scss" scoped></style>