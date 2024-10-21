<template>
    <div v-if="toHotelMap" class="modal-overlay">
        <div class="modal-content">
            <slot>
                <div class="oneLine">
                    <div class="facility-section">
                        <div class="facility-container dynamic-height">
                            <div class="facility-header">飯店設施</div>
                            <hr>
                            <div class="facility-search">
                                <input v-model="searchQueryMap" class="search-input" placeholder="搜尋設施..."
                                    @input="searchFacilityFromMap(searchQueryMap)" />
                            </div>
                            <hr>

                            <div v-if="filteredFacilities.length > 0" class="list-group"
                                style="max-height: 50vh;overflow-y:auto;">

                                <div v-for="(facility, index) in filteredFacilities.slice(0, currentDisplayedCount)"
                                    :key="facility.id" class="mb-2">
                                    <button
                                        :class="['facility-button', selectedFacilityIds.includes(facility.id) ? 'selected' : '']"
                                        @click="toggleFacility(facility.id); filterHotel(facility.id)">
                                        {{ facility.facilityName }}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="map"></div>
                    <div @click="closeHotelMap" class="closeMap" style="cursor: pointer;"><img :src="closeImg" alt="">
                    </div>
                </div>
            </slot>
        </div>
    </div>
</template>

<script setup>
// 定義接收的屬性
const props = defineProps({
    toHotelMap: {
        type: Boolean,
        default: true
    },
    hotels: {
        type: Object,
        default: () => ({})
    },
    filteredFacilities: {
        type: Array,
        default: () => []
    },
    selectedFacilityIds: {
        type: Array,
        default: () => []
    },
    filterByFacilities: {
        type: Object,
        default: () => ({})
    },
    hasMoreFacilities: {
        type: Boolean,
        default: () => ({})
    },
    whitchCity: {
        type: Object,
        default: () => ({})
    },
    searchQuery: {
        type: Object,
        default: () => ({})
    }
})
watch(() => props.filteredFacilities, (newFacilities) => {
    filteredFacilities.value = newFacilities;
});
// 定義事件
const emit = defineEmits(['closeHotelMap', 'filterHotel', 'searchFacilityFromMap'])

// 關閉彈窗
const closeHotelMap = () => {
    emit('closeHotelMap') // 通知父組件關閉彈窗
}
const filterHotel = () => {
    emit('filterHotel', selectedFacilityIds.value, hasMoreFacilities.value)
}
const searchFacilityFromMap = () => {
    emit('searchFacilityFromMap', searchQueryMap.value)
}
import 'leaflet/dist/leaflet.css';  // 引入 Leaflet 的 CSS 文件
import L from 'leaflet'; 
import { onMounted, ref, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import useSearchCriteriaStore from '@/stores/searchCriteria';
import useHotelStore from '@/stores/hotel';
import closeImg from "/assets/img/close.png"
const route = useRoute();
const router = useRouter();
const hotel = ref(props.hotels);
const searchCriteriaStore = useSearchCriteriaStore();
const hotelStore = useHotelStore();
const filteredFacilities = ref(props.filteredFacilities);
const selectedFacilityIds = ref(props.selectedFacilityIds);
const filterByFacilities = ref(props.filterByFacilities);
const hasMoreFacilities = ref(props.hasMoreFacilities);
const whitchCity = ref(props.whitchCity);
const searchQuery = ref(props.searchQuery);
const searchQueryMap = ref(searchQuery.value);
const cities = ref([
    { city_id: 1, name: '台北市', latitude: 25.04777, longitude: 121.51741 },
    { city_id: 2, name: '新北市', latitude: 25.01238, longitude: 121.46549 },
    { city_id: 3, name: '桃園市', latitude: 24.99313, longitude: 121.30104 },
    { city_id: 4, name: '台中市', latitude: 24.16204, longitude: 120.64699 },
    { city_id: 5, name: '台南市', latitude: 22.99734, longitude: 120.21291 },
    { city_id: 6, name: '高雄市', latitude: 22.61262, longitude: 120.30116 },
    { city_id: 7, name: '基隆市', latitude: 25.13256, longitude: 121.73933 },
    { city_id: 8, name: '新竹市', latitude: 24.80189, longitude: 120.97165 },
    { city_id: 9, name: '嘉義市', latitude: 23.47936, longitude: 120.44117 },
    { city_id: 10, name: '新竹縣', latitude: 24.82712, longitude: 121.01292 },
    { city_id: 11, name: '苗栗縣', latitude: 24.56446, longitude: 120.81940 },
    { city_id: 12, name: '彰化縣', latitude: 24.07562, longitude: 120.54464 },
    { city_id: 13, name: '南投縣', latitude: 23.90278, longitude: 120.69055 },
    { city_id: 14, name: '雲林縣', latitude: 23.69952, longitude: 120.52635 },
    { city_id: 15, name: '嘉義縣', latitude: 23.45902, longitude: 120.29282 },
    { city_id: 16, name: '屏東縣', latitude: 22.68315, longitude: 120.48786 },
    { city_id: 17, name: '宜蘭縣', latitude: 24.73072, longitude: 121.76316 },
    { city_id: 18, name: '花蓮縣', latitude: 23.99138, longitude: 121.61985 },
    { city_id: 19, name: '臺東縣', latitude: 22.75566, longitude: 121.15033 },
    { city_id: 20, name: '澎湖縣', latitude: 23.56092, longitude: 119.62946 },
    { city_id: 21, name: '金門縣', latitude: 24.43274, longitude: 118.35950 },
    { city_id: 22, name: '連江縣', latitude: 26.15767, longitude: 119.95598 }
]);
let map;
let markersLayer;
function initMap() {
    map = L.map('map', { closePopupOnClick: false }).setView([cities.value[whitchCity.value - 1].latitude, cities.value[whitchCity.value - 1].longitude], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    markersLayer = L.layerGroup().addTo(map);
    resetMarker();
}
onMounted(function () {
    initMap();
});
function resetMarker() {
    markersLayer.clearLayers();
    for (let i = 0; i < filterByFacilities.value.length; i++) {
        var marker = L.marker([filterByFacilities.value[i].latitude, filterByFacilities.value[i].longitude]
            , { icon: defaultIcon } // 使用強制指定的預設圖標
        ).on('click', function (e) {
            try {
                selectHotel(filterByFacilities.value[i]);
                // e.target.options.closePopup(); 這是個錯誤 但是可以完成某些功能 但好像用不到 先留著
            } catch (e) { }
        });
        marker.addTo(markersLayer);
        let p = new L.Popup({ className: 'filterByFacilitiesPopup', autoClose: false, closeOnClick: false })
            .setContent("<b>" + filterByFacilities.value[i].name + "</b>")
        marker.bindPopup(p).openPopup();
    }
}
const selectHotel = (hotel) => {
    try {
        const query = route.query;
        searchCriteriaStore.setSearchCriteria(query);
        hotelStore.setHotel(hotel);
        router.push('/HotelBooking/roomResults');
    } catch (error) {
        console.log(error)
    }
};

function toggleFacility(facilityId) {
    if (selectedFacilityIds.value.includes(facilityId)) {
        selectedFacilityIds.value = selectedFacilityIds.value.filter(id => id !== facilityId);
    } else {
        selectedFacilityIds.value.push(facilityId);
    }
    filterHotels();
}
function filterHotels() {
    filterByFacilities.value = [];
    if (selectedFacilityIds.value.length === 0) {
        filterByFacilities.value = hotel.value; // 如果没有选择设施，显示所有酒店
    } else {
        filterByFacilities.value = hotel.value.filter(hotel => {
            const allMatch = selectedFacilityIds.value.every(facilityId => hotel.facilities.includes(facilityId));
            console.log(`Hotel ID: ${hotel.hotelID}, Match: ${allMatch}`);
            return allMatch;
        });
    }
    resetMarker();
}
//強制設定圖標
import mark from '/assets/img/marker-icon.png'
import shadow from '/assets/img/marker-shadow.png'
const defaultIcon = new L.Icon({
    iconUrl: mark, // 預設圖標
    shadowUrl: shadow, // 預設陰影
    iconSize: [25, 41], // 圖標尺寸
    iconAnchor: [12, 41], // 錨點位置
    popupAnchor: [1, -34], // 彈出窗口的錨點
    shadowSize: [41, 41] // 陰影尺寸
});
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
    z-index: 66;
}

.modal-content {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    max-width: 90%;
    max-height: 90%;
    width: 100%;
    height: 100%;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    position: relative;
    pointer-events: auto;
}

.leaflet-popup-close-button {
    display: none;
}

#map {
    width: 95%;
    height: 95%;
    margin: auto;
    border: #dfa974 10px solid;
    border-radius: 30px;
}

.oneLine {
    width: 90%;
    height: 90%;
    margin: auto;
    display: flex;
}

.facility-container {
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    transition: height 0.3s;
}

.dynamic-height {
    height: auto;
}

.facility-header {
    font-size: 18px;
    font-weight: bold;
    text-align: center;
}

.facility-search {
    margin: 10px 0;
}

.facility-button {
    width: 100%;
    font-size: 14px;
    font-weight: bold;
    background-color: #fff;
    color: #dfa974;
    border: 2px solid #dfa974;
    border-radius: 5px;
    padding: 8px 0;
    transition: background-color 0.3s, color 0.3s;
    margin-bottom: 8px;
}

.facility-button.selected,
.facility-button:hover {
    background-color: #dfa974;
    color: #fff;
}

.facility-section {
    margin: 0 50px 0 0;
}

.closeMap {
    position: absolute;
    width: 70px;
    height: 70px;
    right: -30px;
    top: -30px;
}
</style>