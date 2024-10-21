<template>
  <div v-if="toForgetPWD" class="modal-overlay">
    <div class="modal-content">
      <slot>
        <div class="lineDiv">
          <div>
            <div v-if="checkRandomCode == false" class="oneLine" @keyup.enter="sendMemberEmail">
              <input type="email" name="memberEmail" placeholder="請輸入您註冊時的電子郵件" v-model="memberEmail">
            </div>
            <div v-else="checkRandomCode==true" @keyup.enter="checkRandom">
              <div class="oneLine">
                <div style="font-size: 20px;color: #dfa974;font-weight: bold;">請在三分鐘內輸入驗證碼並驗證完成</div>
              </div>
              <div class="oneLine">
                <input type="email" name="memberEmail" placeholder="請輸入您的驗證碼" v-model="randomCode">
              </div>
            </div>
          </div>
        </div>
        <div class="lineDiv">
          <div v-if="checkRandomCode == false" class="oneLine">
            <div class="saveButton" @click="sendMemberEmail">送出</div>
            <div class="saveButton" style="background-color: white; color: black; border: lightgray solid 3px;"
              @click="closeForgetPWD">取消</div>
          </div>
          <div v-else="checkRandomCode==true" class="oneLine">
            <div class="saveButton" @click="checkRandom">驗證</div>
            <div class="saveButton" style="background-color: white; color: black; border: lightgray solid 3px;"
              @click="closeForgetPWD">取消</div>
          </div>
          <div class="oneLine">
            <div class="error">{{ message }}</div>
          </div>
        </div>
      </slot>
    </div>
  </div>
</template>

<script setup>
// daniel890909@gmail.com
import { defineProps, defineEmits } from 'vue'
import { ref } from 'vue';
import axiosapi from '@/plugins/axios.js'
import { jwtDecode } from 'jwt-decode';
import { useRouter } from 'vue-router';
const router = useRouter();
// 定義接收的屬性
const props = defineProps({
  toForgetPWD: {
    type: Boolean,
    default: true
  }
})

// 定義事件
const emit = defineEmits(['close'])

// 關閉彈窗
const closeForgetPWD = () => {
  emit('closeForgetPWD') // 通知父組件關閉彈窗
}
const memberEmail = ref("");
const message = ref("");
const token = ref("");
const randomCode = ref("");
const checkRandomCode = ref(false);
const checkRandomBody = ref("");
function sendMemberEmail() {
  let body = {
    "memberEmail": memberEmail.value
  }
  axiosapi.post("/bookingHotel/member/resetPassword", body).then(function (response) {
    if (response.data.success) {
      token.value = response.data.token;
      const decodeToken = jwtDecode(token.value);
      const detail = JSON.parse(decodeToken.sub);
      let mailInfo = {
        "memberEmail": memberEmail.value,
        "randomCode": detail.randomCode
      }//創造能讓用戶輸入驗證碼並進行驗證的環境
      axiosapi.post("/bookingHotel/member/sendEmail", mailInfo)
      message.value = "";
      checkRandomCode.value = true;
      checkRandomBody.value = {
        "randomCode": detail.randomCode,
        "token": token.value
      }

    } else {
      message.value = response.data.message;
    }
  }).catch(function (error) {
  }).finally(function () {
  });
}
function checkRandom() {
  axiosapi.post("/bookingHotel/member/checkRamdonCode", checkRandomBody.value).then(function (response) {
    const decodeToken = jwtDecode(token.value);
    const detail = JSON.parse(decodeToken.sub);
    if (randomCode.value == detail.randomCode) {
      router.push({
        name: 'resetPassword-link', query: {
          token: response.data.token
        }
      })
    } else {
      message.value = "驗證碼不正確";
    }
  })
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  pointer-events: auto;
}

.error {
  color: red;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  max-width: 700px;
  width: 100%;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
  pointer-events: auto;
}

.inputDiv {
  padding: 10px 0;
  margin: 20px;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  border: none;
  background: none;
  cursor: pointer;
}

.lineDiv {
  width: 100%;
}

input {
  font-size: 40px;
  width: 500px;
  height: 80px;
  border: gold, solid, 1px;
  border-radius: 10px;
  margin: 30px;
}

input:focus {
  border: #46A3ff, solid, 1px;
  outline: none;
}

.oneLine {
  display: flex;
  justify-content: center;
}
.saveButton {
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 25px;
  width: 70px;
  height: 35px;
  color: #dfa974;
  border: #dfa974 3px solid;
  padding: 5px;
  margin: 0 100px;
}

.saveButton:hover {
  cursor: pointer;
}

@media(max-width:600px) {
  .saveButton {
    width: 100px !important;
    margin: 0 auto;
  }
  input {
    font-size: 30px;
    width: 400px;
  }
}
@media(max-width:450px){
  input {
    font-size: 22px;
    width: 300px;
  }
}
</style>
