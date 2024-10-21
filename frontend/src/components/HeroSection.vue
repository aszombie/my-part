<template>
    <section class="hero-section">
        <!-- Carousel Section -->
        <div class="hero-slider owl-carousel">
            <div class="hs-item" v-for="(image, index) in images" :key="index" :style="{ backgroundImage: `url(${image})` }"></div>
        </div>

        <!-- Content Section -->
        <div class="container hero-content">
            <div class="row justify-content-center">
                <div class="col-lg-6 text-center">
                    <div class="hero-text">
                        <h1>LuxeNest</h1>
                        <p>Here are the best Hotel booking websites, recommendation for finding suitable hotel rooms.</p>
                        <a href="#" class="primary-btn btn btn-primary">Discover Now</a>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-5 offset-xl-2 offset-lg-1">
                    <div class="booking-form">
                        <h3>尋找你的住宿</h3>
                        <form @submit.prevent="handleSubmit">
                            <div class="select-option mb-3">
                                <label for="city">地區:</label>
                                <select v-model="form.cityId" id="cityId" class="form-select" required>
                                    <option v-for="city in cities" :key="city.city_id" :value="city.city_id">
                                        {{ city.name }}
                                    </option>
                                </select>
                            </div>
                            <div class="check-date mb-3">
                                <label for="date-in">入住日期:</label>
                                <input type="text" class="form-control date-input" id="date-in" v-model="form.checkInDate">
                            </div>
                            <div class="check-date mb-3">
                                <label for="date-out">退房日期:</label>
                                <input type="text" class="form-control date-input" id="date-out" v-model="form.checkOutDate">
                            </div>
                            <div class="select-option mb-3">
                                <label for="guest">旅客人數:</label>
                                <div style="display: inline-flex; align-items: center; justify-content: space-between; width: 100%;">
                                <a-button @mousedown="startDecrement" @mouseup="() => stopChange(decrement)" @mouseleave="clearTimeout(timer)"  :disabled="form.numOfPeople <= 1" style="width: 40px; height: 40px;">-</a-button>
                                <span style="width: 40px; text-align: center; white-space: nowrap; display: inline-block; padding-top: 12%;">{{ form.numOfPeople }} 位</span>
                                <a-button @mousedown="startIncrement" @mouseup="() => stopChange(increment)" @mouseleave="clearTimeout(timer)"  :disabled="form.numOfPeople >= 32" style="width: 40px; height: 40px;">+</a-button>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">搜尋</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import flatpickr from 'flatpickr'
import 'flatpickr/dist/flatpickr.min.css'
// import jQuery from 'jquery'
import 'owl.carousel/dist/assets/owl.carousel.min.css'
import 'owl.carousel'

// Define the images array
const images = [
    '/img/hero/hero-1.jpg',
    '/img/hero/hero-2.jpg',
    '/img/hero/hero-3.jpg'
]

// Initialize the carousel
onMounted(() => {
    $('.hero-slider').owlCarousel({
        items: 1,
        loop: true,
        autoplay: true
    });
    flatpickr('#date-in', {
        dateFormat: 'Y-m-d'
    })

    flatpickr('#date-out', {
        dateFormat: 'Y-m-d'
    })
});

const form = ref({
    cityId: '',
    cityName:'',
    checkInDate: '',
    checkOutDate: '',
    numOfPeople: 1,
});

let interval = null;
let timer = null;
let isLongPress = false;

// 增加人數
const increment = () => {
  if (form.value.numOfPeople < 32) {
    form.value.numOfPeople++;
  } else {
    // 如果達到邊界，停止計時器
    clearInterval(interval);
  }
};

// 減少人數
const decrement = () => {
  if (form.value.numOfPeople > 1) {
    form.value.numOfPeople--;
  } else {
    // 如果達到邊界，停止計時器
    clearInterval(interval);
  }
};

// 開始長按的邏輯，500ms後開始長按
const startIncrement = () => {
  if (form.value.numOfPeople >= 32) return; // 如果已經達到最大值，直接返回
  isLongPress = false;  // 重置長按狀態
  timer = setTimeout(() => {
    isLongPress = true;
    interval = setInterval(increment, 150);
  }, 500);
};

const startDecrement = () => {
  if (form.value.numOfPeople <= 1) return; // 如果已經達到最小值，直接返回
  isLongPress = false;  // 重置長按狀態
  timer = setTimeout(() => {
    isLongPress = true;
    interval = setInterval(decrement, 150); 
  }, 500);
};

// 停止長按或單擊
const stopChange = (action) => {
  clearTimeout(timer);     // 清除長按的延時
  clearInterval(interval); // 停止長按的計時器
  if (!isLongPress) {
    // 如果不是長按，則執行單次操作
    action();
  }
};
const cities = ref([
    { city_id: 1, name: '台北市' },
    { city_id: 2, name: '新北市' },
    { city_id: 3, name: '桃園市' },
    { city_id: 4, name: '台中市' },
    { city_id: 5, name: '台南市' },
    { city_id: 6, name: '高雄市' },
    { city_id: 7, name: '基隆市' },
    { city_id: 8, name: '新竹市' },
    { city_id: 9, name: '嘉義市' },
    { city_id: 10, name: '新竹縣' },
    { city_id: 11, name: '苗栗縣' },
    { city_id: 12, name: '彰化縣' },
    { city_id: 13, name: '南投縣' },
    { city_id: 14, name: '雲林縣' },
    { city_id: 15, name: '嘉義縣' },
    { city_id: 16, name: '屏東縣' },
    { city_id: 17, name: '宜蘭縣' },
    { city_id: 18, name: '花蓮縣' },
    { city_id: 19, name: '臺東縣' },
    { city_id: 20, name: '澎湖縣' },
    { city_id: 21, name: '金門縣' },
    { city_id: 22, name: '連江縣' }
]);

const router = useRouter();

const handleSubmit = () => {
    if (!form.value.checkInDate || form.value.checkInDate === '年/月/日') {
    alert('請選擇入住日期');
    return;
    }

    if (!form.value.checkOutDate || form.value.checkOutDate === '年/月/日') {
    alert('請選擇退房日期');
    return;
    }

    const selectedCity = cities.value.find(city => city.city_id === form.value.cityId);
    console.log('選擇城市'+selectedCity.value)
    if (selectedCity) {
        form.value.cityName = selectedCity.name;
    }
    router.push({ path: '/HotelBooking/hotelResults', query: { ...form.value } });
};
</script>

<style scoped>
.hero-section {
    
    position: relative;
    width: 100%;
    padding-top: 50px;
    padding-bottom: 100px;
    overflow: hidden;
    display: flex;
    align-items: center;
}

.hero-slider {
    width: 100%;
    height: 100%;
}

.hero-slider .hs-item {
    background-size: cover;
    background-position: center;
    height: 100%;
    width: 100%;
}

.hero-content {
    position: relative;
    z-index: 5;
    width: 100%;
    max-width: 1140px;
    padding-left: 15px;
    padding-right: 15px;
    margin: 0 auto;
}

.hero-text {
    padding-top: 145px;
    text-align: center;
}

.hero-text h1 {
    font-size: 80px;
    line-height: 90px;
    color: #ffffff;
    margin-bottom: 16px;
}

.hero-text p {
    font-size: 18px;
    color: #ffffff;
    line-height: 28px;
    margin-bottom: 35px;
}
.primary-btn:hover {
    background-color: #dfa974; /* 更改背景颜色 */
    color: #ffffff; /* 更改文本颜色 */
    transition: background-color 0.3s ease, color 0.3s ease; /* 添加过渡效果 */
}

/* Search button hover effect */
.booking-form button:hover {
    background-color: #dfa974; /* 更改背景颜色 */
    color: #ffffff; /* 更改文本颜色 */
    transition: background-color 0.3s ease, color 0.3s ease; /* 添加过渡效果 */
}
.primary-btn {
    display: inline-block;
    font-size: 14px;
    text-transform: uppercase;
    border: 1px solid #dfa974;
    border-radius: 2px;
    color: #dfa974;
    background: transparent;
    height: 46px;
    padding: 0 20px;
    line-height: 46px;
}

.booking-form {
    background: #ffffff;
    padding: 44px 40px 50px 40px;
    border-radius: 4px;
}

.booking-form h3 {
    color: #19191a;
    margin-bottom: 36px;
}

.booking-form .check-date,
.booking-form .select-option {
    margin-bottom: 15px;
}

.booking-form button {
    display: block;
    width: 100%;
    height: 46px;
    margin-top: 30px;
    background: transparent;
    border: 1px solid #dfa974;
    color: #dfa974;
    font-weight: 500;
    text-transform: uppercase;
    border-radius: 2px;
}
</style>
