<template>
    <div class="main">

        <div class="setDetailmainBG">
            <img :src="imageBG" ref="imageRef" class="BGimg" id="setDetailmainBG">
        </div>
        <div class="formDiv" @keyup.enter="update">
            <form class="setDetailForm">
                <div style="text-align: center;">
                    <div class="inputDiv">
                        <input type="text" name="name" v-model="name" placeholder="請輸入名稱" required @blur="checkName">
                    </div>
                    <div class="error" v-if="!(vName)">請輸入名稱</div>

                    <div class="inputDiv">
                        <input type="text" id="phone" minlength="10" maxlength="10" name="phone" v-model="phone"
                            placeholder="請輸入手機號碼" required @blur="checkPhone">
                    </div>
                    <div v-if='vPhone === "space"' style="display: none;"></div>
                    <div v-else-if='vPhone === "error"' class="error">請輸入完整的手機號碼，且只能輸入數字</div>
                    <div v-else='vPhone==="good"' class="good">手機號碼格式正確</div>

                    <div class="inputDiv">
                        <div class="selectDiv" style="width: 150px; margin: 0 20px 0 0; ">
                            <select name="nation" v-model="nation" required style="width: 150px;">
                                <option value="" disabled selected hidden>國家/地區</option>
                                <option value="阿富汗">阿富汗</option>
                                <option value="阿爾巴尼亞">阿爾巴尼亞</option>
                                <option value="阿爾及利亞">阿爾及利亞</option>
                                <option value="美屬薩摩亞">美屬薩摩亞</option>
                                <option value="安道爾">安道爾</option>
                                <option value="安哥拉">安哥拉</option>
                                <option value="安圭拉">安圭拉</option>
                                <option value="南極洲">南極洲</option>
                                <option value="安提瓜和巴布達">安提瓜和巴布達</option>
                                <option value="阿根廷">阿根廷</option>
                                <option value="亞美尼亞">亞美尼亞</option>
                                <option value="阿魯巴">阿魯巴</option>
                                <option value="澳洲">澳洲</option>
                                <option value="奧地利">奧地利</option>
                                <option value="亞塞拜然">亞塞拜然</option>
                                <option value="巴哈馬">巴哈馬</option>
                                <option value="巴林">巴林</option>
                                <option value="孟加拉">孟加拉</option>
                                <option value="巴巴多斯">巴巴多斯</option>
                                <option value="白俄羅斯">白俄羅斯</option>
                                <option value="比利時">比利時</option>
                                <option value="貝里斯">貝里斯</option>
                                <option value="貝南">貝南</option>
                                <option value="百慕達">百慕達</option>
                                <option value="不丹">不丹</option>
                                <option value="玻利維亞">玻利維亞</option>
                                <option value="波士尼亞與赫塞哥維納">波士尼亞與赫塞哥維納</option>
                                <option value="波札那">波札那</option>
                                <option value="布威島">布威島</option>
                                <option value="巴西">巴西</option>
                                <option value="英屬印度洋領地">英屬印度洋領地</option>
                                <option value="汶萊">汶萊</option>
                                <option value="保加利亞">保加利亞</option>
                                <option value="布吉納法索">布吉納法索</option>
                                <option value="蒲隆地">蒲隆地</option>
                                <option value="柬埔寨">柬埔寨</option>
                                <option value="喀麥隆">喀麥隆</option>
                                <option value="加拿大">加拿大</option>
                                <option value="維德角">維德角</option>
                                <option value="開曼群島">開曼群島</option>
                                <option value="中非共和國">中非共和國</option>
                                <option value="查德">查德</option>
                                <option value="智利">智利</option>
                                <option value="中國">中國</option>
                                <option value="聖誕島">聖誕島</option>
                                <option value="科科斯群島">科科斯（基林）群島</option>
                                <option value="哥倫比亞">哥倫比亞</option>
                                <option value="葛摩">葛摩</option>
                                <option value="剛果">剛果</option>
                                <option value="剛果民主共和國">剛果民主共和國</option>
                                <option value="庫克群島">庫克群島</option>
                                <option value="哥斯大黎加">哥斯大黎加</option>
                                <option value="象牙海岸">象牙海岸</option>
                                <option value="克羅埃西亞">克羅埃西亞</option>
                                <option value="古巴">古巴</option>
                                <option value="塞浦路斯">塞浦路斯</option>
                                <option value="捷克共和國">捷克共和國</option>
                                <option value="丹麥">丹麥</option>
                                <option value="吉布地">吉布地</option>
                                <option value="多米尼克">多米尼克</option>
                                <option value="多明尼加共和國">多明尼加共和國</option>
                                <option value="東帝汶">東帝汶</option>
                                <option value="厄瓜多">厄瓜多</option>
                                <option value="埃及">埃及</option>
                                <option value="薩爾瓦多">薩爾瓦多</option>
                                <option value="赤道幾內亞">赤道幾內亞</option>
                                <option value="厄立特里亞">厄立特里亞</option>
                                <option value="愛沙尼亞">愛沙尼亞</option>
                                <option value="衣索比亞">衣索比亞</option>
                                <option value="福克蘭群島">福克蘭群島（馬爾維納斯）</option>
                                <option value="法羅群島">法羅群島</option>
                                <option value="斐濟">斐濟</option>
                                <option value="芬蘭">芬蘭</option>
                                <option value="法國">法國</option>
                                <option value="法國本土">法國本土</option>
                                <option value="法屬圭亞那">法屬圭亞那</option>
                                <option value="法屬玻里尼西亞">法屬玻里尼西亞</option>
                                <option value="法屬南方領地">法屬南方領地</option>
                                <option value="加彭">加彭</option>
                                <option value="甘比亞">甘比亞</option>
                                <option value="喬治亞">喬治亞</option>
                                <option value="德國">德國</option>
                                <option value="迦納">迦納</option>
                                <option value="直布羅陀">直布羅陀</option>
                                <option value="希臘">希臘</option>
                                <option value="格陵蘭">格陵蘭</option>
                                <option value="格瑞那達">格瑞那達</option>
                                <option value="瓜德羅普">瓜德羅普</option>
                                <option value="關島">關島</option>
                                <option value="瓜地馬拉">瓜地馬拉</option>
                                <option value="幾內亞">幾內亞</option>
                                <option value="幾內亞比索">幾內亞比索</option>
                                <option value="蓋亞那">蓋亞那</option>
                                <option value="海地">海地</option>
                                <option value="赫德島和麥克唐納群島">赫德島和麥克唐納群島</option>
                                <option value="梵蒂岡">梵蒂岡</option>
                                <option value="宏都拉斯">宏都拉斯</option>
                                <option value="香港">香港</option>
                                <option value="匈牙利">匈牙利</option>
                                <option value="冰島">冰島</option>
                                <option value="印度">印度</option>
                                <option value="印尼">印尼</option>
                                <option value="伊朗">伊朗</option>
                                <option value="伊拉克">伊拉克</option>
                                <option value="愛爾蘭">愛爾蘭</option>
                                <option value="以色列">以色列</option>
                                <option value="義大利">義大利</option>
                                <option value="牙買加">牙買加</option>
                                <option value="日本">日本</option>
                                <option value="約旦">約旦</option>
                                <option value="哈薩克">哈薩克</option>
                                <option value="肯亞">肯亞</option>
                                <option value="吉里巴斯">吉里巴斯</option>
                                <option value="北韓">北韓</option>
                                <option value="南韓">南韓</option>
                                <option value="科威特">科威特</option>
                                <option value="吉爾吉斯">吉爾吉斯</option>
                                <option value="寮國">寮國</option>
                                <option value="拉脫維亞">拉脫維亞</option>
                                <option value="黎巴嫩">黎巴嫩</option>
                                <option value="賴索托">賴索托</option>
                                <option value="賴比瑞亞">賴比瑞亞</option>
                                <option value="利比亞">利比亞</option>
                                <option value="列支敦斯登">列支敦斯登</option>
                                <option value="立陶宛">立陶宛</option>
                                <option value="盧森堡">盧森堡</option>
                                <option value="澳門">澳門</option>
                                <option value="馬達加斯加">馬達加斯加</option>
                                <option value="馬拉威">馬拉威</option>
                                <option value="馬來西亞">馬來西亞</option>
                                <option value="馬爾地夫">馬爾地夫</option>
                                <option value="馬利">馬利</option>
                                <option value="馬爾他">馬爾他</option>
                                <option value="馬紹爾群島">馬紹爾群島</option>
                                <option value="馬提尼克">馬提尼克</option>
                                <option value="茅利塔尼亞">茅利塔尼亞</option>
                                <option value="模里西斯">模里西斯</option>
                                <option value="馬約特">馬約特</option>
                                <option value="墨西哥">墨西哥</option>
                                <option value="密克羅尼西亞聯邦">密克羅尼西亞聯邦</option>
                                <option value="摩爾多瓦">摩爾多瓦</option>
                                <option value="摩納哥">摩納哥</option>
                                <option value="蒙古">蒙古</option>
                                <option value="蒙特內哥羅">蒙特內哥羅</option>
                                <option value="蒙特色拉特">蒙特色拉特</option>
                                <option value="摩洛哥">摩洛哥</option>
                                <option value="莫三比克">莫三比克</option>
                                <option value="緬甸">緬甸</option>
                                <option value="納米比亞">納米比亞</option>
                                <option value="諾魯">諾魯</option>
                                <option value="尼泊爾">尼泊爾</option>
                                <option value="荷蘭">荷蘭</option>
                                <option value="荷屬安地列斯">荷屬安地列斯</option>
                                <option value="新喀里多尼亞">新喀里多尼亞</option>
                                <option value="紐西蘭">紐西蘭</option>
                                <option value="尼加拉瓜">尼加拉瓜</option>
                                <option value="尼日">尼日</option>
                                <option value="奈及利亞">奈及利亞</option>
                                <option value="紐埃">紐埃</option>
                                <option value="諾福克島">諾福克島</option>
                                <option value="北馬其頓">北馬其頓</option>
                                <option value="北馬里亞納群島">北馬里亞納群島</option>
                                <option value="挪威">挪威</option>
                                <option value="阿曼">阿曼</option>
                                <option value="巴基斯坦">巴基斯坦</option>
                                <option value="帛琉">帛琉</option>
                                <option value="巴拿馬">巴拿馬</option>
                                <option value="巴布亞紐幾內亞">巴布亞紐幾內亞</option>
                                <option value="巴拉圭">巴拉圭</option>
                                <option value="秘魯">秘魯</option>
                                <option value="菲律賓">菲律賓</option>
                                <option value="皮特肯群島">皮特肯群島</option>
                                <option value="波蘭">波蘭</option>
                                <option value="葡萄牙">葡萄牙</option>
                                <option value="波多黎各">波多黎各</option>
                                <option value="卡達">卡達</option>
                                <option value="留尼旺">留尼旺</option>
                                <option value="羅馬尼亞">羅馬尼亞</option>
                                <option value="俄羅斯">俄羅斯</option>
                                <option value="盧安達">盧安達</option>
                                <option value="聖赫勒拿">聖赫勒拿</option>
                                <option value="聖克里斯多福及尼維斯">聖克里斯多福及尼維斯</option>
                                <option value="聖露西亞">聖露西亞</option>
                                <option value="聖皮埃與密克隆">聖皮埃與密克隆</option>
                                <option value="聖文森及格瑞那丁">聖文森及格瑞那丁</option>
                                <option value="薩摩亞">薩摩亞</option>
                                <option value="聖馬利諾">聖馬利諾</option>
                                <option value="聖多美普林西比">聖多美普林西比</option>
                                <option value="沙烏地阿拉伯">沙烏地阿拉伯</option>
                                <option value="塞內加爾">塞內加爾</option>
                                <option value="塞爾維亞">塞爾維亞</option>
                                <option value="塞席爾">塞席爾</option>
                                <option value="獅子山">獅子山</option>
                                <option value="新加坡">新加坡</option>
                                <option value="斯洛伐克">斯洛伐克</option>
                                <option value="斯洛維尼亞">斯洛維尼亞</option>
                                <option value="索羅門群島">索羅門群島</option>
                                <option value="索馬利亞">索馬利亞</option>
                                <option value="南非">南非</option>
                                <option value="南喬治亞與南三明治群島">南喬治亞與南三明治群島</option>
                                <option value="西班牙">西班牙</option>
                                <option value="斯里蘭卡">斯里蘭卡</option>
                                <option value="蘇丹">蘇丹</option>
                                <option value="蘇利南">蘇利南</option>
                                <option value="斯瓦爾巴群島和揚馬延島">斯瓦爾巴群島和揚馬延島</option>
                                <option value="史瓦帝尼">史瓦帝尼</option>
                                <option value="瑞典">瑞典</option>
                                <option value="瑞士">瑞士</option>
                                <option value="敘利亞">敘利亞</option>
                                <option value="台灣">台灣</option>
                                <option value="塔吉克">塔吉克</option>
                                <option value="坦尚尼亞">坦尚尼亞</option>
                                <option value="泰國">泰國</option>
                                <option value="多哥">多哥</option>
                                <option value="托克勞">托克勞</option>
                                <option value="東加">東加</option>
                                <option value="千里達及托巴哥">千里達及托巴哥</option>
                                <option value="突尼西亞">突尼西亞</option>
                                <option value="土耳其">土耳其</option>
                                <option value="土庫曼">土庫曼</option>
                                <option value="特克斯和凱科斯群島">特克斯和凱科斯群島</option>
                                <option value="吐瓦魯">吐瓦魯</option>
                                <option value="烏干達">烏干達</option>
                                <option value="烏克蘭">烏克蘭</option>
                                <option value="阿拉伯聯合大公國">阿拉伯聯合大公國</option>
                                <option value="英國">英國</option>
                                <option value="美國">美國</option>
                                <option value="美屬小離島">美屬小離島</option>
                                <option value="烏拉圭">烏拉圭</option>
                                <option value="烏茲別克">烏茲別克</option>
                                <option value="瓦努阿圖">瓦努阿圖</option>
                                <option value="梵蒂岡">梵蒂岡</option>
                                <option value="委內瑞拉">委內瑞拉</option>
                                <option value="越南">越南</option>
                                <option value="瓦利斯和富圖納">瓦利斯和富圖納</option>
                                <option value="西撒哈拉">西撒哈拉</option>
                                <option value="葉門">葉門</option>
                                <option value="尚比亞">尚比亞</option>
                                <option value="辛巴威">辛巴威</option>
                            </select>
                        </div>
                        <div name="gender" class="selectDiv">
                            <select name="gender" v-model="gender" required>
                                <option value="" disabled selected hidden>性別</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="inputDiv">
                        <div>
                            <input type="text" name="address" v-model="address" placeholder="請輸入地址" required
                                @blur="checkAddress">
                            <div class="error" v-if="!(vAddress)">請輸入地址</div>
                        </div>
                    </div>
                    <div class="selectDiv">
                        <select name="level" v-model="level" v-if="level != '管理員'" style="display: none;" required
                            readonly>
                            <option value="管理員">管理員</option>
                            <option value="公司">公司</option>
                            <option value="一般會員">一般會員</option>
                        </select>
                        <select name="level" v-model="level" v-else="level==='管理員'" required>
                            <option value="管理員">管理員</option>
                            <option value="公司">公司</option>
                            <option value="一般會員">一般會員</option>
                        </select>
                    </div>
                    <button class="setDetailButton " type="button" @click="update">下一步</button>
                    <div class="error" v-if="!vCheck">請確認以上資料是否全都填寫正確</div>
                </div>
            </form>
        </div>

    </div>
</template>
<script setup>
import { ref } from 'vue';
import img5 from '/assets/img/005.png'
import img11 from '/assets/img/011.png'
import img10 from '/assets/img/010.png'
import gsap from 'gsap';
import axiosapi from '@/plugins/axios.js'
import { useRouter } from 'vue-router';
import useMemberStore from '@/stores/member.js';

let objBG = [img5, img11,img10]
const imageBG = ref("");
const imageRef = ref(null);
const memberStore = useMemberStore();
const router = useRouter();
const memberObj = JSON.parse(memberStore.memberInfo);
const name = ref(memberObj.name);
const gender = ref(memberObj.gender);
const phone = ref(memberObj.phone);
const level = ref(memberObj.level);
const nation = ref(memberObj.nation);
const address = ref(memberObj.address);
const vPhone = ref("space");
const vCheck = ref(true);
const vName = ref(true);
const vAddress = ref(true);
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

function update() {
    let body = {
        "name": name.value,
        "gender": gender.value,
        "phone": phone.value,
        "level": level.value,
        "nation": nation.value,
        "address": address.value
    }
    if (gender.value === "" || name.value === "" || vPhone.value === "error" || nation.value === "" || address.value === "") {
        checkName();
        checkAddress();
        checkPhone();
        vCheck.value = false;
    } else {
        axiosapi.put(`/bookingHotel/member/updateDetail/${memberObj.id}`, body).then(function (response) {
            if (response.data.success) {
                memberStore.setMemberInfo(response.data.memberInfo);
                router.push({ name: "login-link" });
            }
        }).catch(function (error) {
            console.log("error", error);
        }).finally(function () { });
    }
}
function checkPhone() {
    if (phone.value.length<10 ||phone.value.length>10 || /\D/.test(phone.value)) {
        vPhone.value = "error";
        event.preventDefault();
    } else {
        vPhone.value = "good";
    }
}
function checkName() {
    if (name.value == "") {
        vName.value = false;
    } else {
        vName.value = true;
    }
}
function checkAddress() {
    if (address.value == "") {
        vAddress.value = false;
    } else {
        vAddress.value = true;
    }
}
</script>
<style scoped>
.main {
    overflow: hidden;
    max-height: calc(100vh);
    margin: 0;
}

.setDetailmainBG {
    background-color: black;
    position: absolute;
    width: 1920px;
    height: 110%;
    /* top: 50px; */
    /* margin: 50px 0 0 0; */
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

.good {
    color: green;
}

span {
    font-weight: bold;
    font-size: 20px;
    margin: 0 10px;
}

.formDiv {
    display: flex !important;
    justify-content: center !important;
    flex-direction: column;
    align-items: center;
    height: 100vh;
}

.setDetailForm {
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
    display: flex;
    justify-content: center;
    width: 580px;
    padding: 10px 0;
}
.setDetailButton {
    background-color: white;
    width: 250px;
    height: 60px;
    font-size: 40px;
    font-weight: bolder;
    color: #dfa974;
    border: #dfa974 3px solid;
}
.selectDiv {
    width: 90px;
}

select {
    width: 100px;
    height: 50px;
    border: #dfa974, solid, 1px !important;
    border-radius: 10px;
}

option {
    text-align: center;
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
</style>