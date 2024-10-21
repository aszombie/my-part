<template>
  <div v-if="toUpdatePhoto" class="modal-overlay">
    <div class="modal-content">
      <slot>
        <div class="lineDiv">
          <div class="oneLine">
            <img v-if="previewPhoto != ''" class="preview" :src="previewPhoto" alt="">
          </div>
        </div>
        <form>
          <div class="lineDiv" style="margin: 30px 0;">
            <div class="oneLine">
          <input type="file" id="photoInput">
          <div type="submit" class="uploadButton" @click="getPhoto">上傳圖片</div>
        </div>
      </div>  
        </form>
        <div class="lineDiv">
          <div class="oneLine">
            <div class="saveButton" @click="update">儲存</div>
            <div class="saveButton" style="background-color: white; color: black; border: lightgray solid 3px;"
              @click="closeUpdatePhoto">取消</div>
          </div>
          <div class="oneLine">
            <div class="error" v-if="!vCheck">請上傳圖片</div>
          </div>
        </div>
      </slot>
    </div>
  </div>
</template>
<script setup>
import { defineProps, defineEmits, h } from 'vue'
import { ref } from 'vue';
import useMemberStore from '@/stores/member';
import axiosapi from '@/plugins/axios';
const memberStore = useMemberStore();
const memberObj = JSON.parse(memberStore.memberInfo);
const previewPhoto = ref("");
const vCheck = ref(true);
// 定義接收的屬性
const props = defineProps({
  toUpdatePhoto: {
    type: Boolean,
    default: true
  }
})
// 定義事件
const emit = defineEmits(['closeUpdatePhoto'])
// 關閉彈窗
const closeUpdatePhoto = () => {
  emit('closeUpdatePhoto') // 通知父組件關閉彈窗
}

function getPhoto() {
  const photoInput = document.getElementById('photoInput');

  if (!photoInput) {
    console.error('No input element found');
    return;
  }

  const photo = photoInput.files[0];

  if (!photo) {
    console.error('No file selected');
    return;
  }

  const reader = new FileReader();

  reader.onload = function (e) {
    const img = new Image();
    img.src = e.target.result;

    img.onload = function () {
      const canvas = document.createElement('canvas');
      const ctx = canvas.getContext('2d');
      const maxWidth = 200;
      const maxHeight = 200;
      let width = img.width;
      let height = img.height;

      if (width > height) {
        if (width > maxWidth) {
          height *= maxWidth / width;
          width = maxWidth;
        }
      } else {
        if (height > maxHeight) {
          width *= maxHeight / height;
          height = maxHeight;
        }
      }

      canvas.width = width;
      canvas.height = height;
      ctx.drawImage(img, 0, 0, width, height);

      previewPhoto.value = canvas.toDataURL('image/jpeg', 1);
    };

    img.onerror = function () {
      console.error('Failed to load image');
    };
  };

  reader.onerror = function () {
    console.error('Error reading file');
  };

  reader.readAsDataURL(photo);
}

function update() {
  let body = {
    "imageFile": previewPhoto.value,
  }
  if (previewPhoto.value === "") {
    vCheck.value = false;
  } else {
    axiosapi.put(`/bookingHotel/member/updateUserPhoto/${memberObj.id}`, body).then(function (response) {
      if (response.data.success) {
        memberStore.setMemberInfo(response.data.memberInfo);
        window.location.reload();
        closeModal();
      }
    }).catch(function (error) {
      console.log("error", error);
    }).finally(function () { });
  }
  console.log(123);
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

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  max-width: 1000px;
  width: 100%;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
  pointer-events: auto;
}

.lineDiv {
  width: 100%;
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
  background-color: #da9c5f;
  border-radius: 10px;
  margin: 0 100px;
}
.uploadButton {
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 25px;
  color: white;
  width: 150px !important;
  height: 35px;
  background-color: #da9c5f;
  border-radius: 10px;
  margin: 0 auto;
}

.saveButton:hover {
  cursor: pointer;
}

.preview {
  width: 200px !important;
  height: 200px !important;
  border: #da9c5f solid 5px;
  border-radius: 100px;
  overflow: hidden;
  margin: 50px;
}
@media(max-width:600px) {
  .saveButton {
    width: 100px !important;
    margin: 0 auto;
  }
  .uploadButton {
    width: 150px !important;
    height: auto;
    margin: 0 10px;
  }
}
@media(max-width:460px) {
  input {
    width: 84px !important;
    margin: 0 auto;
  }
  .uploadButton {
    width: 150px !important;
    height: auto;
    margin: 0 10px;
    padding: 10px;
    font-size: 20px;
  }
}
</style>