<template>
  <main class="bg-i container min-vh-100">

    <div class="gi-nav shadow fixed-top">
      <div class="container d-flex align-items-center justify-content-between">
        <div>
          <a href="#">
            <h1 class="text-danger text-center fw-bold">Pizze!</h1>

          </a>
        </div>

        <input class="w-50 me-2 bg-light-subtle border border-danger border-3" v-model="searchPizza" type="text"
          name="nome" placeholder="cerca la tua pizza preferita">


      </div>
    </div>

    <div class="margin">


      <div class="container d-flex align-items-center justify-content-center">
        <div class="card shadow position-relative">

          <form class="row p-2 " v-if="showCreateForm" @submit.prevent="storePizza">
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class=" fw-bold" for="nome">nome</label>

                </div>
                <div class="col">

                  <input class=" w-100" type="text" name="nome" v-model="newPizza.nome">
                </div>
              </div>
            </div>
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class=" fw-bold" for="descrizione">descrizione</label>

                </div>
                <div class="col">

                  <input class=" w-100" type="text" name="descrizione" v-model="newPizza.descrizione">
                </div>
              </div>
            </div>
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class="fw-bold" for="foto">foto</label>
                </div>
                <div class="col">

                  <input class=" w-100" type="text" name="foto" v-model="newPizza.foto">
                </div>
              </div>
            </div>
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class=" fw-bold" for="prezzo">prezzo</label>

                </div>
                <div class="col">

                  <input class=" w-100" type="number" name="prezzo" v-model="newPizza.prezzo">
                </div>
              </div>

            </div>
            <div class="col-6">
              <input class="btn btn-danger m-2" type="submit" value="CREATE">
              <button class="btn btn-warning" @click="puliziaForm">CANCEL</button>

            </div>
          </form>

          <form class="row p-2" v-if="showUpdateForm" @submit.prevent="updatePizza">
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class="fw-bold" for="nome">nome</label>
                </div>
                <div class="col">
                  <input class="w-100" type="text" name="nome" v-model="newPizza.nome">
                </div>
              </div>
            </div>
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class="fw-bold" for="descrizione">descrizione</label>
                </div>
                <div class="col">
                  <input class="w-100" type="text" name="descrizione" v-model="newPizza.descrizione">
                </div>
              </div>
            </div>
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class="fw-bold" for="photo">Photo</label>
                </div>
                <div class="col">
                  <input class="w-100" type="text" name="photo" v-model="newPizza.foto">
                </div>
              </div>
            </div>
            <div class="col-6">
              <div class="row">
                <div class="col-2">
                  <label class="fw-bold" for="prezzo">prezzo</label>
                </div>
                <div class="col">
                  <input class="w-100" type="number" name="prezzo" v-model="newPizza.prezzo">
                </div>
              </div>
            </div>
            <div class="col-6">
              <input class="btn btn-danger m-2" @click="updatePizza(pizza.id)" type="submit" value="Update">

              <button class="btn btn-warning" @click="puliziaForm">CANCEL</button>
            </div>
          </form>
          <table v-if="!showCreateForm && !showUpdateForm" class="table table-light">
            <thead>
              <tr class="text-center">
                <th scope="col">Nome</th>
                <th scope="col">Prezzo</th>
                <th scope="col">Azioni</th>
              </tr>
            </thead>
            <tbody>
              <tr class="text-center" v-for="pizza in pizzeFiltrate" :key="pizza.id">
                <td> {{ pizza.nome }}</td>
                <td>{{ pizza.prezzo }}&euro;</td>
                <td class="d-flex align-items-center justify-content-center">
                  <a class="btn btn-warning text-light mx-2 d-none">
                    <i class="fa-solid fa-eye"></i>
                  </a>
                  <button class="btn btn-primary mx-2" @click="openUpdateForm(pizza.id)">
                    <i class="fa-solid fa-pencil"></i>
                  </button>

                  <button class="btn btn-danger mx-2" @click="deletePizza(pizza.id)"><i
                      class="fa-solid fa-trash"></i></button>

                </td>

              </tr>
            </tbody>
            <div class="gi-crea">
              <div @click="showCreateForm = !showCreateForm" class="fw-bold btn btn-danger text-white shadow">Crea la tua
                pizza! <i class="fa-solid fa-pizza-slice text-light bg-danger"></i>
              </div>

            </div>
          </table>
        </div>

      </div>
    </div>

  </main>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import axios from "axios";

const API_URL = "http://localhost:8080/api/v1.0"

const pizze = ref(null);

const pizzaNulla = {
  nome: null,
  descrizione: null,
  foto: null,
  prezzo: null
};
const newPizza = ref({ ...pizzaNulla });
const showCreateForm = ref(false)
const showUpdateForm = ref(false)
const searchPizza = ref('');
const pizzaId = ref(null);


function getAllPizze() {

  axios.get(API_URL)
    .then(res => {

      const data = res.data;

      pizze.value = data;
    })
    .catch(err => console.log(err));
}


function puliziaForm() {
  showCreateForm.value = false;
  showUpdateForm.value = false;
  newPizza.value = ref({ ...pizzaNulla });
}

function storePizza() {

  axios.post(API_URL, newPizza.value)
    .then(res => {

      const data = res.data;

      puliziaForm();
      getAllPizze();
    })
    .catch(err => console.log(err));
}

function deletePizza(id) {

  axios.delete(API_URL + "/" + id)
    .then(res => {

      getAllPizze();
    })
    .catch(err => console.log(err))
}

const pizzeFiltrate = computed(() => {
  if (!searchPizza.value) {
    return pizze.value;
  }

  const searchPizzaLower = searchPizza.value.toLowerCase();
  return pizze.value.filter((pizza) =>
    pizza.nome.toLowerCase().includes(searchPizzaLower)
  );
})

function openUpdateForm(id) {
  pizzaId.value = id;
  const pizzaToUpdate = pizze.value.find(pizza => pizza.id === id);
  newPizza.value = { ...pizzaToUpdate };
  showUpdateForm.value = true;
}


function updatePizza() {
  const id = pizzaId.value;
  if (id && newPizza.value) {
    axios.put(`${API_URL}/${id}`, newPizza.value)
      .then(res => {
        const data = res.data;
        const index = pizze.value.findIndex(pizza => pizza.id === id);
        if (index !== -1) {
          pizze.value[index] = data;
        }
        showUpdateForm.value = false;
        puliziaForm();
      })
      .catch(err => {
        console.error('Errore durante l\'aggiornamento della pizza:', err);
        // Puoi aggiungere una logica qui per gestire l'errore in modo appropriato.
      });
  }
}



onMounted(() => {
  getAllPizze();
})
</script>


<style scoped></style>