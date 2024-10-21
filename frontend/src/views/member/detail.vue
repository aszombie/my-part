<template>
    <div class="main" >
        <div class="lineDiv">
            <img :src="memberImage" class="userPhoto" @click="openUpdatePhoto" style="cursor: pointer;">
            <div class="mainTitle" v-if="innerWidth > 800">個人資料</div>
            <div v-if="innerWidth > 991" class="updateButton" @click="openModal">更新個人資料</div>
        </div>
        <div class="lineDiv">
            <table v-if="innerWidth <= 800"  tabindex="0" @focus="open" @blur="close">
                <tbody>
                    <tr style="color: #dfa974;"  @click="toDetail">個人資料</tr>
                    <tr v-if="isOpen==true" @click="toOrder">歷史訂單</tr>
                    <tr v-if="isOpen==true" @click="toHistoryComment">歷史評價</tr>
                    <tr v-if="isOpen==true" @click="goToFavoritesList">我的收藏</tr>
                    <tr v-if="isOpen==true" @click="openResetPWDFromDetail">變更密碼</tr>
                    <tr v-if="isOpen==true" @click="logout">登出</tr>
                </tbody>
            </table>
        </div>
        <div class="lineDiv">
            <table v-if="innerWidth > 800">
                <tbody>
                    <tr @click="toDetail" style="color: #dfa974;">個人資料</tr>
                    <hr>
                    <tr @click="toOrder">歷史訂單</tr>
                    <hr>
                    <tr @click="toHistoryComment">歷史評價</tr>
                    <hr>
                    <tr @click="goToFavoritesList">我的收藏</tr>
                    <hr>
                    <tr @click="openResetPWDFromDetail">變更密碼</tr>
                    <hr>
                    <tr @click="logout">登出</tr>
                </tbody>
            </table>
            <div class="memberInfo">
                <div class="oneLine">
                    <div class="type">名稱</div>
                    <div class="data">{{ name }}</div>
                </div>
                <hr>
                <div class="oneLine">
                    <div class="type">性別</div>
                    <div class="data">{{ gender }}</div>
                </div>
                <hr>
                <div class="oneLine">
                    <div class="type">聯絡電話</div>
                    <div class="data">{{ phone }}</div>
                </div>
                <hr>
                <div class="oneLine">
                    <div class="type">會員等級</div>
                    <div class="data">{{ level }}</div>
                </div>
                <hr>
                <div class="oneLine">
                    <div class="type">國籍</div>
                    <div class="data">{{ nation }}</div>
                </div>
                <hr>
                <div class="oneLine">
                    <div class="type">地址</div>
                    <div class="data">{{ address }}</div>
                </div>
                <hr v-if="innerWidth <= 991">
                <div class="oneLine" v-if="innerWidth <= 991">
                    <div class="updateButton" @click="openModal">更新個人資料</div>
                </div>
            </div>
        </div>
    </div>
    <updateDetail v-if="isModalVisible" @close="closeModal" @updateStore="updateStore" />
    <updatePhoto v-if="toUpdatePhoto" @closeUpdatePhoto="closeUpdatePhoto" />
    <resetPasswordFromDetail v-if="toResetPWDFromDetail" @closeResetPWDFromDetail="closeResetPWDFromDetail" />
</template>
<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import useMemberStore from '@/stores/member';
const memberStore = useMemberStore();
const router = useRouter();
let memberObj = JSON.parse(memberStore.memberInfo);
const memberImage = ref("");
const name = ref(memberObj.name);
const gender = ref(memberObj.gender);
const phone = ref(memberObj.phone);
const level = ref(memberObj.level);
const nation = ref(memberObj.nation);
const address = ref(memberObj.address);
const innerWidth = ref(window.innerWidth);
const isOpen = ref(false);
memberImage.value = `data:image/png;base64,${memberObj.image}`;
import updateDetail from './updateDetail.vue';
const isModalVisible = ref(false); // 控制彈窗是否顯示

const openModal = () => {
    isModalVisible.value = true; // 打開彈窗
};

const closeModal = () => {
    isModalVisible.value = false; // 關閉彈窗
};
import updatePhoto from './updatePhoto.vue';

const toUpdatePhoto = ref(false); // 控制彈窗是否顯示

const openUpdatePhoto = () => {
    toUpdatePhoto.value = true; // 打開彈窗
};

const closeUpdatePhoto = () => {
    toUpdatePhoto.value = false; // 關閉彈窗
};
import resetPasswordFromDetail from './resetPasswordFromDetail.vue';

const toResetPWDFromDetail = ref(false); // 控制彈窗是否顯示

const openResetPWDFromDetail = () => {
    toResetPWDFromDetail.value = true; // 打開彈窗
};

const closeResetPWDFromDetail = () => {
    toResetPWDFromDetail.value = false; // 關閉彈窗
};
function updateStore() {
    memberObj = JSON.parse(memberStore.memberInfo);
}
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
    isOpen.value =true;
}
function close() {
    isOpen.value =false;
}

function goToFavoritesList(){
    router.push('/member/favorite');
}
function toOrder(){
    router.push('/member/order');
}
function toHistoryComment(){
    router.push('/member/historyComment');
}
function toDetail(){
    router.push('/member/detail');
}
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
    margin: 0 0 0 100px;
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

.updateButton {
    width: 200px;
    margin: auto 0 auto 100px;
    height: 50px;
    font-size: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #dfa974;
    border-radius: 15px;
    color: white;
    font-weight: bold;
    cursor: pointer;
}

.updateButton:hover {
    background-color: #da9c5f;
}

.updateButton:active {
    background-color: #d58f4b;
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
    table{
        width: 300px!important;
        border: #da9c5f 2px solid;
        height: auto;
    }
    tr{
        font-size: 30px;
        height: 50px;
        width: 300px !important;
        border: #da9c5f 2px solid;
    }
    .lineDiv {
        justify-content: center;
    }
    .memberInfo{
        margin: 0;
    }
}

@media (max-width:550px) {
    .type {
        margin: auto;
        width: 100px;
        font-size: 20px;
    }
    .memberInfo{
        margin: 0;
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