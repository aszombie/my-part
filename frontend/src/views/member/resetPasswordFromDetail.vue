<template>
    <div v-if="toResetPWDFromDetail" class="modal-overlay">
      <div class="modal-content">
        <slot>
          <div class="lineDiv">
            <div>
              <div class="oneLine">
                <input type="password" name="password" placeholder="請輸入目前的密碼" v-model="password">
              </div>
            </div>
          </div>
          <div class="lineDiv">
            <div class="oneLine">
              <div class="saveButton" @click="reset">驗證</div>
              <div class="saveButton" style="background-color: white; color: black; border: lightgray solid 3px;" @click="closeResetPWDFromDetail">取消</div>
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
  import { defineProps, defineEmits } from 'vue'
  import { ref } from 'vue';
  import axiosapi from '@/plugins/axios.js'
  import useMemberStore from '@/stores/member';
  import { useRouter } from 'vue-router';
  const router = useRouter();
  // 定義接收的屬性
  const props = defineProps({
    toResetPWDFromDetail: {
      type: Boolean,
      default: true
    }
  })
  
  // 定義事件
  const emit = defineEmits(['close'])
  
  // 關閉彈窗
  const closeResetPWDFromDetail = () => {
    emit('closeResetPWDFromDetail') // 通知父組件關閉彈窗
  }
  const password = ref("");
  const message = ref("");
  const memberStore = useMemberStore();
  const memberEmail= ref(memberStore.memberEmail);
  function reset() {
  let body = {
    "memberEmail":memberEmail.value,
    "password": password.value
  }
  axiosapi.post("/bookingHotel/member/checkPassword", body).then(function (response) {
    if (response.data.success) {
      console.log(response.data);
      router.push({
        name: 'resetPassword-link', query: {
          token: response.data.token
        }
      })
    } else {
      message.value = response.data.message;
    }
  }).catch(function (error) {
  }).finally(function () {
  });
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
    color: white;
    width: 70px;
    height: 35px;
    background-color: #2894FF;
    border-radius: 10px;
    margin: 0 100px;
  }
  .saveButton:hover{
    cursor: pointer;
  }
  </style>
  