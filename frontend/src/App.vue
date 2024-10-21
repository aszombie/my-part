

<template>
  <div class="app-container">
    <Header />
    <main class="main-content">
      <RouterView />
    </main>
    <Footer />
  </div>
</template>

  <script setup>
  import Header from './components/Header.vue';
  import SearchForm from './components/SearchForm.vue';
  import Footer from './components/Footer.vue';
  import { RouterView } from 'vue-router';

  </script>

<style>
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.main-content {
  flex: 1;
}

Footer {
  padding: 1rem; 
  text-align: center; 
}
</style>
