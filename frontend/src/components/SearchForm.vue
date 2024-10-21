<template>
    <v-app>
        <v-container>
            <v-card class="pa-5" max-width="800">
                <v-card-title>尋找你的住宿</v-card-title>

                <v-form @submit.prevent="submitForm">
                    <v-row>
                        <!-- 城市选择 -->
                        <v-col cols="12" md="3">
                            <v-select v-model="form.city" :items="cities" label="選擇縣市" required></v-select>
                        </v-col>

                        <!-- 入住日期 -->
                        <v-col cols="12" md="3">
                            <v-menu v-model="menuCheckIn" :close-on-content-click="false" transition="scale-transition"
                                offset-y min-width="auto">
                                <template v-slot:activator="{ on, attrs }">
                                    <v-text-field v-model="form.checkInDate" label="入住日期" prepend-icon="mdi-calendar"
                                        readonly v-bind="attrs" v-on="on"></v-text-field>
                                </template>
                                <v-date-picker v-model="form.checkInDate" @input="menuCheckIn = false"></v-date-picker>
                            </v-menu>
                        </v-col>

                        <!-- 退房日期 -->
                        <v-col cols="12" md="3">
                            <v-menu v-model="menuCheckOut" :close-on-content-click="false" transition="scale-transition"
                                offset-y min-width="auto">
                                <template v-slot:activator="{ on, attrs }">
                                    <v-text-field v-model="form.checkOutDate" label="退房日期" prepend-icon="mdi-calendar"
                                        readonly v-bind="attrs" v-on="on"></v-text-field>
                                </template>
                                <v-date-picker v-model="form.checkOutDate"
                                    @input="menuCheckOut = false"></v-date-picker>
                            </v-menu>
                        </v-col>

                        <!-- 人数选择 -->
                        <v-col cols="12" md="2">
                            <v-text-field v-model="form.guests" label="旅客人數" type="number" min="1" max="10"
                                required></v-text-field>
                        </v-col>

                        <!-- 提交按钮 -->
                        <v-col cols="12" md="1">
                            <v-btn color="primary" type="submit" class="mt-6">搜尋</v-btn>
                        </v-col>
                    </v-row>
                </v-form>
            </v-card>
        </v-container>
    </v-app>
</template>

<script setup>
import { ref } from 'vue';

const form = ref({
    city: '',
    checkInDate: '',
    checkOutDate: '',
    guests: ''
});

const cities = ['台北', '新北', '高雄', '台中', '台南'];

const menuCheckIn = ref(false);
const menuCheckOut = ref(false);

const submitForm = () => {
    console.log('Submitted Form Data:', form.value);
};
</script>

<style>
/* 根据需要调整样式 */
</style>