<template>
  <div v-if="toRegisterCheck" class="modal-overlay">
    <div class="modal-content">
      <slot>
        <div class="lineDiv" @keyup.enter="checkRandom">
          <div class="oneLine">
            <div style="font-size: 20px;color: #dfa974;font-weight: bold;">請在三分鐘內輸入驗證碼並驗證完成</div>
          </div>
          <div class="oneLine">
            <input type="email" name="memberEmail" placeholder="請輸入您的驗證碼" v-model="randomCode">
          </div>
        </div>
        <div class="lineDiv">
          <div class="oneLine">
            <div class="saveButton" @click="checkRandom">驗證</div>
            <div class="saveButton" style="background-color: white; color: black; border: lightgray solid 3px;"
              @click="closeRegisterCheck">取消</div>
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
import { useRouter } from 'vue-router';
import useMemberStore from '@/stores/member.js';
const router = useRouter();
// 定義接收的屬性
const props = defineProps({
  toRegisterCheck: {
    type: Boolean,
    default: true
  },
  responseData: {
    type: Object,
    default: () => ({})
  }
});
// 定義事件
const emit = defineEmits(['close'])

// 關閉彈窗
const closeRegisterCheck = () => {
  emit('closeRegisterCheck') 
}
const memberEmail = ref("");
const message = ref("");
const token = ref("");
const randomCode = ref("");
const memberStore = useMemberStore();
const checkRandomCode = ref(false);
const checkRandomBody = ref("");
function checkRandom() {
  if(randomCode.value===props.responseData.randomCode){
    let body={
      "memberEmail":props.responseData.memberEmail,
      "password":props.responseData.password
    }
    axiosapi.post("/bookingHotel/member/register", body).then(function (response) {
      if (response.data.success) {
        console.log(response);
      memberStore.setMemberInfo(response.data.memberInfo);
      router.push({ name: "setDetail-link" });
    } else {
      message.value = response.data.message;
    }
  }).catch(function (error) {
  }).finally(function () { });
}else{
  message.value="驗證碼不正確";
}
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
