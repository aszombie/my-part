<template>
    <div class="main">
        <div class="resetmainBG">
            <img :src="imageBG" ref="imageRef" class="BGimg" id="resetmainBG">
        </div>
        <div class="formDiv">
            <form class="resetForm">
                <div style="text-align: center;" v-if="!isChange"  @keyup.enter="reset">
                    <div style="font-size: 20px;color: #dfa974;font-weight: bold;">請在三分鐘內完成重設密碼的操作</div>
                    <div class="inputDiv">
                        <input type="password" name="password" placeholder="請輸入新密碼" v-model="password">
                    </div>
                    <div class="inputDiv">
                        <input type="password" placeholder="請再輸入一次新密碼" v-model="checkPassword">
                    </div>
                    <div class="error">{{ message }}</div>
                    <button class="resetButton " type="button" @click="reset">下一步</button>
                </div>
                <div v-if="isChange">
                    <div style="display: flex;justify-content: center;">
                        <button class="resetButton"  type="button" @click="backToLogin">完成</button>
                    </div>
                    <div style="display: flex;justify-content: center;">
                        <div style="font-size: 20px;color: #dfa974;font-weight: bold;margin: 30px 0 0 0;">密碼變更成功下次登入時請使用新密碼</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import img3 from '/assets/img/003.png'
import img4 from '/assets/img/004.png'
import img5 from '/assets/img/005.png'
import gsap from 'gsap';
import axiosapi from '@/plugins/axios.js'
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import useMemberStore from '@/stores/member.js';
let objBG = [img3, img4, img5]
const imageBG = ref("");
const imageRef = ref(null);
const memberStore = useMemberStore();
const router = useRouter();
const route = useRoute();
const memberEmail = ref("");
const password = ref("");
const checkPassword = ref("");
let message = ref("");
let isChange = ref(false);
function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
async function changeBG() {
    let i = 0;
    while (true) {
        const originalConsoleWarn = console.warn;
        console.warn = function () { };
        const fadeDuration = 1; // 淡出和淡入的動畫持續時間
        const overlapDuration = 0.9; // 淡出和淡入之間的重疊時間
        gsap.to(imageRef.value, { opacity: 0, duration: fadeDuration, ease: 'power1.in' });
        await delay(fadeDuration * 1000);
        imageBG.value = objBG[i];
        gsap.fromTo(imageRef.value,
            { opacity: 0 },
            { opacity: 1, duration: fadeDuration, ease: 'power1.out' });
        await delay((fadeDuration - overlapDuration) * 1000);
        i = (i + 1) % objBG.length;
        await delay(5000);
        console.warn = originalConsoleWarn;
    }
}
changeBG();


function reset() {
    const token = route.query.token;
    if (password.value === checkPassword.value) {
        let body = {
            "token": token,
            "Password": password.value
        }
        console.log(body);
        axiosapi.post("/bookingHotel/member/resetPasswordConfirm", body).then(function (response) {
            message.value = response.data.message;
            if(response.data.success){
                isChange.value=true;
                memberStore.setMemberInfo("");
                memberStore.setMemberEmail("");
                memberStore.setLogin(false);
            }
        }).catch(function (error) {
    }).finally(function () {
    });
    }else{
        message.value = "請確認兩次輸入的密碼是否相同";
    }
}
function backToLogin(){
    router.push({ name: "login-link" });
}
</script>
<style scoped>
.main {
    overflow: hidden;
    max-height: calc(100vh);
    margin: 0;
}

.resetmainBG {
    background-color: black;
    position: absolute;
    width: 1920px;
    height: 110%;
    background-position: center;
    background-size: cover;
    overflow: hidden;
}

.BGimg {
    background-position: center center;
    background-size: contain;
    background-repeat: no-repeat;
    background-attachment: fixed;
    position: absolute;
    top: -25%;
    overflow: hidden;
}


.error {
    color: red;
}

.formDiv {
    display: flex !important;
    justify-content: center !important;
    height: 100vh;
}

.resetForm {
    position: absolute;
    width: 580px !important;
    padding: 40px 0;
    top: 25%;
    background-color: white;
    border-radius: 30px;
}


input {
    width: 350px;
    height: 50px;
    border: #dfa974, solid, 1px;
    border-radius: 10px;
}

input:focus {
    border: #46A3ff, solid, 1px;
    outline: none;
}

.inputDiv {
    padding: 10px 0;
    margin: 20px;
}

.resetButton {
    background-color: white;
    width: 250px;
    height: 60px;
    font-size: 40px;
    font-weight: bolder;
    color: #dfa974;
    border: #dfa974 3px solid;
}

.resetButton:hover {
    background-color: #FF8040;
}

.resetButton:active {
    background-color: #FFA042;
}

@media(max-width:1280px) {
    .resetForm {
        width: 480px !important;
    }

    input {
        width: 300px !important;
    }
}

@media(max-width:800px) {
    .resetForm {
        width: 400px !important;
    }

    input {
        width: 250px !important;
    }
}

@media(max-width:480px) {
    .resetForm {
        width: 480px !important;
    }

    input {
        width: 250px !important;
    }
}


/* 1600 */
</style>