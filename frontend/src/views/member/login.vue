<template>
    <div class="main">
        <div class="loginMainBG">
            <img :src="imageBG" ref="imageRef" class="BGimg" id="loginMainBG">
        </div>
        <div class="formDiv"  @keyup.enter="login">
            <form class="loginForm">
                <div style="text-align: center;">
                    <div class="inputDiv">
                        <input type="email" name="memberEmail" placeholder="電子郵件地址" @blur="checkEmail"  v-model="memberEmail">
                        <div class="error" v-if="!(vEmail)">Email格式錯誤</div>
                    </div>
                    <div class="inputDiv">
                        <input type="password" name="password" placeholder="密碼" v-model="password" >
                    </div>
                    <div class="error">{{ message }}</div>
                    <button class="loginButton " type="button" @click="login">登入</button>
                    <div class="oneLine">
                        <div class="forgetPWD" @click="openForgetPWD">忘記密碼?</div>
                    </div>
                    <hr>
                    <div style="color: #dfa974; font-size: 30px; font-weight: bolder;">第一次使用LuxeNest?</div>
                    <button class="registerButton " type="button" @click="toRegister">建立帳戶</button>
                </div>
            </form>
        </div>
    </div>
    <forgetPWD v-if="toForgetPWD" @closeForgetPWD="closeForgetPWD" />
</template>
<script setup>
import { ref } from 'vue';
import img1 from '/assets/img/001.png'
import img2 from '/assets/img/002.png'
import img6 from '/assets/img/006.jpg'
import gsap from 'gsap';
import Swal from 'sweetalert2';
import axiosapi from '@/plugins/axios.js'
import { useRouter } from 'vue-router';
import useMemberStore from '@/stores/member.js';
// import useCompanyStore from '@/stores/company';


let objBG = [img1, img2,img6]
const imageBG = ref("");
const imageRef = ref(null);
const vEmail=ref(true);
const memberStore = useMemberStore();
// const companyStore = useCompanyStore();
const router = useRouter();
const memberEmail = ref("");
const password = ref("");
let message = ref("");
function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
async function changeBG() {
    let i = 0;

    while (true) {
        const originalConsoleWarn = console.warn;
        console.warn = function () { };
        const fadeDuration = 0.5; // 淡出和淡入的動畫持續時間
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
function checkEmail(){
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(emailPattern.test(memberEmail.value)){
        vEmail.value=true;
    }else{
        vEmail.value=false;
    }
}

function login() {
    if (memberEmail.value === "") {
        memberEmail.value = null;
    }
    if (password.value === "") {
        password.value = null;
    }
    let body = {
        "memberEmail": memberEmail.value,
        "password": password.value
    }
    axiosapi.defaults.headers.authorization = "";
    memberStore.setMemberInfo("");
    memberStore.setMemberEmail("");
    memberStore.setLogin(false);
    checkEmail();
    if(vEmail.value){
        axiosapi.post("/bookingHotel/member/login", body).then(function (response) {
            if (response.data.success) {
                memberStore.setMemberInfo(response.data.memberInfo);
                memberStore.setMemberEmail(response.data.memberEmail);
                memberStore.setLogin(true);
                // companyStore.setCompanyName("");
                // companyStore.setCompanyId("");
                // companyStore.setLogin(false);  // Set company login to false
                router.push({ name: "home" });
            } else {
                message.value = response.data.message;
            }
        }).catch(function (error) {
        }).finally(function () {
        });
    }
}
function toRegister() {
    router.push({ name: "register-link" });
}
function toCompany() {
    router.push({ path: "/companyLogin" });
}

import forgetPWD from './forgetPWD.vue';

const toForgetPWD = ref(false); // 控制彈窗是否顯示f

const openForgetPWD = () => {
    toForgetPWD.value = true; // 打開彈窗
    console.log(123);
};

const closeForgetPWD = () => {
    toForgetPWD.value = false; // 關閉彈窗
};
</script>
<style scoped>
.main {
    overflow: hidden;
    max-height: calc(100vh);
    margin: 0;
}

.loginMainBG {
    background-color: black;
    position: absolute;
    width: 1920px;
    height: 110%;
    /* top:50px;
    margin: 50px 0 0 0; */
    background-position: center center;
    background-size: contain;
    background-repeat: no-repeat;
    background-attachment: fixed;
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

.loginForm {
    position: absolute;
    width: 580px !important;
    padding: 40px 0;
    top: 25%;
    /* background-color: white; */
    background-color: rgba(225,225,225,0.97);
    border-radius: 30px;
}


input {
    opacity: 1;
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
    opacity: 1 !important;
    padding: 10px 0;
    margin: 20px;
}

.loginButton {
    background-color: white;
    width: 250px;
    height: 60px;
    font-size: 40px;
    font-weight: bolder;
    color: #dfa974;
    border: #dfa974 3px solid;

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
.forgetPWD {
    border-radius: 10px;
    color: #0080FF;
    cursor: pointer;
    width: 100px;
}

.forgetPWD:hover {
    background-color: #D2E9FF;
}

.forgetPWD:active {
    background-color: #C4E1FF;
}

.oneLine {
    display: flex;
    justify-content: center;
    margin: 10px 0;
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