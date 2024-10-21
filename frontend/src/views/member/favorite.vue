<template>
    <div class="main">
        <div class="lineDiv">
            <img :src="memberImage" class="userPhoto">
            <div class="mainTitle" v-if="innerWidth > 800">我的收藏</div>
        </div>
        <div class="lineDiv">
            <table v-if="innerWidth <= 800" tabindex="0" @focus="open" @blur="close">
                <tbody>
                    <tr style="color: #dfa974;" @click="goToFavoritesList">我的收藏</tr>
                    <tr v-if="isOpen == true" @click="toDetail">個人資料</tr>
                    <tr v-if="isOpen == true" @click="toOrder">歷史訂單</tr>
                    <tr v-if="isOpen == true" @click="toHistoryComment">歷史評價</tr>
                    <tr v-if="isOpen==true" @click="openResetPWDFromDetail">變更密碼</tr>
                    <tr v-if="isOpen == true" @click="logout">登出</tr>
                </tbody>
            </table>
        </div>
        <div class="lineDiv">
            <table v-if="innerWidth > 800">
                <tbody>
                    <tr @click="toDetail">個人資料</tr>
                    <hr>
                    <tr @click="toOrder">歷史訂單</tr>
                    <hr>
                    <tr @click="toHistoryComment">歷史評價</tr>
                    <hr>
                    <tr style="color: #dfa974;" @click="goToFavoritesList">我的收藏</tr>
                    <hr>
                    <tr @click="openResetPWDFromDetail">變更密碼</tr>
                    <hr>
                    <tr @click="logout">登出</tr>
                </tbody>
            </table>
            <div>
                <!-- <FavoritesList style="width: 1000px;" ></FavoritesList> -->
                <resetPasswordFromDetail v-if="toResetPWDFromDetail" @closeResetPWDFromDetail="closeResetPWDFromDetail" />
            </div>
        </div>
    </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axiosapi from '@/plugins/axios';
import useMemberStore from '@/stores/member';
import moment from 'moment';
// import FavoritesList from '@/views/FavoritesList.vue'
const memberStore = useMemberStore();
const router = useRouter();
let memberObj = JSON.parse(memberStore.memberInfo);
const memberImage = ref("");
const innerWidth = ref(window.innerWidth);
const isOpen = ref(false);
const comments = ref([]);
const commentObjects = ref([]);
memberImage.value = `data:image/png;base64,${memberObj.image}`;
function logout() {
    memberStore.setMemberInfo("");
    memberStore.setMemberEmail("");
    memberStore.setLogin(false);
    router.push({ name: "home" });
}
onMounted(() => {
    window.addEventListener('resize', windowWidth);
});
function windowWidth() {
    innerWidth.value = window.innerWidth;
}
function open() {
    isOpen.value = true;
}
function close() {
    isOpen.value = false;
}

function goToFavoritesList() {
    router.push('/member/favorite');
}
function toOrder() {
    router.push('/member/order');
}
function toHistoryComment() {
    router.push('/member/historyComment');
}
function toDetail() {
    router.push('/member/detail');
}
import resetPasswordFromDetail from './resetPasswordFromDetail.vue';

const toResetPWDFromDetail = ref(false); // 控制彈窗是否顯示

const openResetPWDFromDetail = () => {
    toResetPWDFromDetail.value = true; // 打開彈窗
};

const closeResetPWDFromDetail = () => {
    toResetPWDFromDetail.value = false; // 關閉彈窗
};
</script>
<style scoped>
.main {
    max-width: 1400px !important;
    width: 100% !important;
    margin: 0 auto 30px !important;
    background-color: white;
}

.userPhoto {
    width: 200px !important;
    height: 200px !important;
    border: #da9c5f solid 5px;
    border-radius: 100px;
    overflow: hidden;
    margin: 50px;
}

.lineDiv {
    display: flex;
    width: 100%;
}

table {
    margin: 0 50px;
    width: 200px !important;
    height: 420px;
}

tr {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 200px !important;
    height: 70px;
    font-weight: bolder;
    font-size: 40px;
}

tr:hover {
    color: #dfa974;
    cursor: pointer;
}

.memberInfo {
    font-size: 30px;
}

.oneLine {
    display: flex;
}

.type {
    width: 150px;
    margin: 10px 0 10px 50px;
}

.data {
    width: 450px;
    border: #84c1FF 2px solid;
    background-color: #ECF5FF;
    margin: 10px 0 10px 0;

}

.mainTitle {
    font-weight: bold;
    font-size: 90px;
    display: flex;
    justify-content: center;
    align-items: center;
}
@media (max-width:991px) {
    .lineDiv {
        justify-content: center;
    }

    .data {
        width: 250px;
    }
}

@media (max-width:800px) {
    table {
        width: 300px !important;
        border: #da9c5f 2px solid;
        height: auto;
    }

    tr {
        font-size: 30px;
        height: 50px;
        width: 300px !important;
        border: #da9c5f 2px solid;
    }

    .lineDiv {
        justify-content: center;
    }
}

@media (max-width:550px) {
    .type {
        margin: auto;
        width: 100px;
        font-size: 20px;
    }

    .lineDiv {
        justify-content: center;
    }

    .data {
        font-size: 20px;
        width: 200px;
    }
}
</style>