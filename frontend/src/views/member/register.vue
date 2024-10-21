<template>
    <div class="main">
        <div class="registermainBG">
            <img :src="imageBG" ref="imageRef" class="BGimg" id="registermainBG">
        </div>
        <div class="formDiv"  @keyup.enter="register">
            <form class="registerForm">
                <div style="text-align: center;">
                    <div class="inputDiv">
                        <input type="email" name="memberEmail" placeholder="電子郵件地址" @blur="checkEmail"
                            v-model="memberEmail">
                        <div class="error" v-if="!(vEmail)">Email格式錯誤</div>
                    </div>
                    <div class="inputDiv">
                        <input type="password" name="password" placeholder="密碼" v-model="password">
                    </div>
                    <div class="inputDiv">
                        <input type="password" placeholder="請再輸入一次密碼" v-model="checkPassword">
                    </div>
                    <div class="error">{{ message }}</div>
                    <button class="registerButton " type="button" @click="register">下一步</button>
                </div>
            </form>
        </div>
    </div>
    <registerCheck v-if="toRegisterCheck" @closeRegisterCheck="closeRegisterCheck" :responseData="responseData" />
</template>
<script setup>
import { ref } from 'vue';
import img3 from '/assets/img/003.png'
import img4 from '/assets/img/004.png'
import img5 from '/assets/img/005.png'
import gsap from 'gsap';
import axiosapi from '@/plugins/axios.js'
import { useRouter } from 'vue-router';
import useMemberStore from '@/stores/member.js';
let objBG = [img3, img4, img5]
const imageBG = ref("");
const imageRef = ref(null);
const memberStore = useMemberStore();
const router = useRouter();
const memberEmail = ref("");
const password = ref("");
const checkPassword = ref("");
const vEmail = ref(true);
const responseData=ref("");
let message = ref("");
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
function checkEmail() {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (emailPattern.test(memberEmail.value)) {
        vEmail.value = true;
    } else {
        vEmail.value = false;
    }
}

import registerCheck from './registerCheck.vue';

const toRegisterCheck = ref(false); // 控制彈窗是否顯示
const openRegisterCheck = () => {
    toRegisterCheck.value = true; // 打開彈窗
};

const closeRegisterCheck = () => {
    toRegisterCheck.value = false; // 關閉彈窗
};

function register() {
    if (memberEmail.value === "") {
        memberEmail.value = null;
    }
    if (password.value === "") {
        password.value = null;
    }
    if (checkPassword.value === "") {
        checkPassword.value = null;
    }
    let body = {
        "memberEmail": memberEmail.value,
        "password": password.value
    }
    axiosapi.defaults.headers.authorization = "";
    memberStore.setMemberInfo("");
    memberStore.setLogin(false);
    message.value = "";
    if (memberEmail.value === null || password.value === null || checkPassword.value === null) {
        message.value = "請確認每個欄位皆已輸入";
    } else {
        checkEmail();
        if (vEmail.value) {
            if (password.value === checkPassword.value) {
                axiosapi.post("/bookingHotel/member/registerCheck", body).then(function (response) {
                    if(response.data.success){
                        responseData.value=response.data;
                        openRegisterCheck();
                    }else {
                        message.value = response.data.message;
                    }
                }).catch(function (error) {
                }).finally(function () { });
            } else {
                message.value = "請確認兩次輸入的密碼是否相同";
            }
        }
    }
}
</script>
<style scoped>
.main {
    overflow: hidden;
    max-height: calc(100vh);
    margin: 0;
}

.registermainBG {
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

.registerForm {
    position: absolute;
    width: 580px !important;
    padding: 40px 0;
    top: 25%;
    background-color: rgba(225,225,225,0.97);
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

.registerButton {
    background-color: white;
    width: 250px;
    height: 60px;
    font-size: 40px;
    font-weight: bolder;
    color: #dfa974;
    border: #dfa974 3px solid;
}

.registerButton:hover {
    background-color: #FF8040;
}

.registerButton:active {
    background-color: #FFA042;
}

@media(max-width:991px) {
    .loginForm {
        width: 480px !important;
    }

    input {
        width: 300px !important;
    }
}
@media(max-width:480px) {
    .loginForm {
        width: 480px !important;
    }

    input {
        width: 250px !important;
    }
}

/* 1600 */
</style>