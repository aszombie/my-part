<template>
    <div class="main">
        <div class="lineDiv">
            <img :src="memberImage" class="userPhoto">
            <div class="mainTitle" v-if="innerWidth > 800">歷史評價</div>
        </div>
        <div class="lineDiv">
            <table v-if="innerWidth <= 800" tabindex="0" @focus="open" @blur="close">
                <tbody>
                    <tr style="color: #dfa974;" @click="toHistoryComment">歷史評價</tr>
                    <tr v-if="isOpen == true" @click="toDetail">個人資料</tr>
                    <tr v-if="isOpen == true" @click="toOrder">歷史訂單</tr>
                    <tr v-if="isOpen == true" @click="goToFavoritesList">我的收藏</tr>
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
                    <tr style="color: #dfa974;" @click="toHistoryComment">歷史評價</tr>
                    <hr>
                    <tr @click="goToFavoritesList">我的收藏</tr>
                    <hr>
                    <tr @click="openResetPWDFromDetail">變更密碼</tr>
                    <hr>
                    <tr @click="logout">登出</tr>
                </tbody>
            </table>
           <!-- <div class="memberInfo" v-if="comments == ''">
                <div class="card">
                    <div class="comment">
                        <div>目前沒有任何評論</div>
                    </div>
                </div>
            </div>
             <div class="memberInfo" v-else>
                <div v-for="(comment, index) in commentObjects" :key="index">
                    <div class="card">
                        <div class="comment">
                            <div class="hotel">{{ comment.hotelName }}</div>
                            <div class="date">日期: {{ comment.date }}</div>
                            <div class="commentText">{{ comment.text }}<br></br></div>
                            <div class="level">評論等級: {{ comment.level }}</div>
                        </div>
                    </div>
                </div>
            </div>-->
        </div>
    </div> 
    <resetPasswordFromDetail v-if="toResetPWDFromDetail" @closeResetPWDFromDetail="closeResetPWDFromDetail" />
</template>
<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axiosapi from '@/plugins/axios';
import useMemberStore from '@/stores/member';
import moment from 'moment';
const memberStore = useMemberStore();
const router = useRouter();
let memberObj = JSON.parse(memberStore.memberInfo);
const memberImage = ref("");
const innerWidth = ref(window.innerWidth);
const isOpen = ref(false);
const comments = ref([]);
const commentObjects = ref([]);
import resetPasswordFromDetail from './resetPasswordFromDetail.vue';

const toResetPWDFromDetail = ref(false); // 控制彈窗是否顯示

const openResetPWDFromDetail = () => {
    toResetPWDFromDetail.value = true; // 打開彈窗
};

const closeResetPWDFromDetail = () => {
    toResetPWDFromDetail.value = false; // 關閉彈窗
};
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
// function getComments() {
//     let body = {
//         "memberId": memberObj.id
//     }
//     axiosapi.post(`/bookingHotel/comment/findAllByMemberId`, body).then(function (response) {
//         comments.value = response.data.comment;
//         console.log(comments.value);
//         comments.value.forEach(comment => {
//             axiosapi.get(`/bookingHotel/Hotel/findHotel/${comment.hotelId}`, body).then(function (response) {
//                 const commentObj = {
//                     hotelName: response.data.name,
//                     order: comment.orderId,
//                     text: comment.comment,
//                     level: comment.commentLevel,
//                     date: moment(comment.date).format('YYYY-MM-DD')
//                 };
//                 commentObjects.value.push(commentObj);
//             })
//         });
//     }).catch(function (error) {
//         console.log("error", error);
//     }).finally(function () { });
// }
// getComments();
</script>
<style scoped>
.main {
    max-width: 1400px !important;
    width: 100% !important;
    margin: 0 auto 30px !important;
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

.comment {
    width: 1000px;
    height: auto;
    position: relative;
    margin: 20px 20px;
    padding: 10px;
    grid-template-columns: 300px 1fr 120px;
    gap: 20px;
    border: 1px solid #ddd;
    padding: 15px;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    cursor: pointer;
    transition: background-color 0.3s, transform 0.3s;
}

.comment:hover {
    background-color: #f1f1f1;
    /* 懸停變色 */
    transform: translateY(-5px);
    /* 增加懸浮效果 */
}

.hotel {
    position: relative;
    margin-bottom: 10px;
    color: #d58f4b;
}

.date {
    position: absolute;
    right: 10px;
    bottom: 10px;
    color: gray;
}

.commentText {
    word-wrap: break-word;
    width: 75%;
    font-size: 30px;
    margin-top: 10px;
    margin-bottom: 30px;
}

.level {
    position: absolute;
    right: 10px;
    top: 10px;
    color: #d58f4b;
}

.card {
    margin: 20px;
    border-radius: 15px;
    /* 圓角邊框 */
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
    /* 加強陰影 */
    background: white;
    /* 卡片背景色 */
}

@media (max-width:991px) {
    .lineDiv {
        justify-content: center;
    }

    .data {
        width: 250px;
    }

    .comment {
        width: 600px;
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

    .comment {
        width: 440px;
    }

    .data {
        font-size: 20px;
        width: 200px;
    }
}
</style>