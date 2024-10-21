<template>
    <header class="header-fixed">
        <div class="menu-item">
            <div>
                <div class="row align-items-center">
                    <div class="col-lg-2">
                        <div class="logo">
                            <RouterLink class="nav-link" to="/">
                                <img src="/img/logo3.png" alt="Logo" class="logo-img">
                            </RouterLink>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="nav-menu">
                            <nav class="mainmenu">
                                <ul>
                                    <li class="active">
                                        <RouterLink class="nav-link" to="/">主頁</RouterLink>
                                    </li>
                                    <li class="nav-item">
                                        <RouterLink class="nav-link active" >
                                            <img :src="cartIcon" alt="購物車" style="width: 24px; height: 24px;" />
                                        </RouterLink>
                                    </li>
                                </ul>
                            </nav>
                            <div class="nav-right search-switch">
                                <ul class="mainmenu">

                                    <!-- 公司部分 -->
                                    <li >
                                        <RouterLink class="nav-link" >公司登入</RouterLink>
                                    </li>
                                    <!-- 會員部分 -->
                                    <li v-if="!memberStore.login">
                                        <RouterLink class="nav-link" to="/member/login">登入</RouterLink>
                                    </li>
                                    <li v-if="memberStore.login">
                                        <RouterLink class="nav-link" to="/member/detail">{{name}}</RouterLink>
                                    </li>
                                    <li v-if="memberStore.login">
                                        <a class="nav-link" href="#" @click="logout">登出</a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
</template>

<script setup>
import useMemberStore from '@/stores/member';
import { nextTick } from 'vue';
import { useRouter } from 'vue-router';
import cartIcon from '@/components/icons/cart-icon.png';
import { onMounted, watchEffect ,ref} from 'vue';

const router = useRouter();
const memberStore = useMemberStore();
const name = ref("");
function isLogin(){
    if(memberStore.login){
        let memberObj = JSON.parse(memberStore.memberInfo);
        name.value =memberObj.name;
        console.log(name.value);
    }
}
function logout() {
    memberStore.setMemberInfo("");
    memberStore.setMemberEmail("");
    memberStore.setLogin(false);
    router.push({ name: "home" });
} 
isLogin();
</script>
<style>
/* 调整 logo 大小 */
.logo-img {
    width: 200px;
    /* 设置 logo 宽度 */
    height: auto;
    /* 保持 logo 的宽高比 */
    display: block;
    /* 确保 logo 图片是块级元素 */
    margin: 0 auto;
    /* 确保 logo 在容器内水平居中 */
    transition: transform 0.3s ease;
    /* 添加过渡效果 */
}

/* 悬停时 logo 不会有任何默认样式变化 */
.logo-img:hover {
    transform: none;
}

/* logo 所在的容器样式 */
.logo {
    text-align: center;
    /* 将 logo 内容居中对齐 */
    padding: 6px 0;
    /* 增加 padding 确保 logo 与顶部的距离 */
}

/* 调整导航菜单和 logo 的对齐方式 */
.nav-menu {
    text-align: center;
    /* 导航内容居中对齐 */
    height: 100px;
}

.nav-menu .nav-right {
    display: inline-block;
    margin-left: 15px;
    /* 与导航菜单保持15px的距离 */
    padding: 10px 0;
    /* 上下各有10px的内边距 */
}

.nav-menu .nav-right i {
    font-size: 14px;
    /* 字体大小 */
    color: #333;
    /* 字体颜色 */
    cursor: pointer;
    /* 鼠标悬停时显示为指针 */
}

/* 对主导航菜单的样式设置 */
.mainmenu {
    display: inline-block;
    /* 主导航菜单水平排列 */
}

.mainmenu li {
    list-style: none;
    /* 去除列表默认的样式 */
    display: inline-block;
    /* 菜单项水平排列 */
    position: relative;
    /* 用于定位下拉菜单 */
    margin-right: 10px;
    /* 菜单项之间的距离 */
}


.mainmenu li .dropdown-menu {
    position: absolute;
    top: 100%; /* 確保下拉選單顯示在導航項下方 */
    left: 0;
    z-index: 1000; /* 提高 z-index 確保其位於其他元素之上 */
    background-color: white; /* 背景設為白色以確保內容清晰 */
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1); /* 添加陰影效果 */
    border-radius: 4px; /* 圓角邊框 */
}

/* 激活状态和悬停状态的菜单项下划线 */
.mainmenu li a:after {
    content: "";
    position: absolute;
    left: 0;
    right: 0; /* 确保下划线与文字两端对齐 */
    bottom: -3px;
    width: auto !important; /* 设置为 auto 以匹配文字宽度 */
    height: 1px;
    background: #dfa974; /* 橘线颜色 */
    opacity: 0;
    transition: opacity 0.3s;
}


/* 激活和悬停时显示下划线 */
.mainmenu li:hover a:after,
.mainmenu li.active a:after {
    width:100%;
    opacity: 1;
}

/* 主菜单项链接样式 */
.mainmenu li a {
    font-size: 14px;
    /* 字体大小 */
    color: #333;
    /* 字体颜色 */
    padding: 10px 0;
    /* 设置上下的内边距 */
    position: relative;
    /* 用于控制伪元素的位置 */
    text-decoration: none;
    /* 禁止浏览器默认下划线 */
    transition: color 0.3s;
}

/* 子菜单（下拉菜单）的样式 */
.mainmenu li .dropdown {
    position: absolute;
    left: 20;
    top: 100%;
    width: 100px;
    background: #fff;
    z-index: 10;
    opacity: 0;
    visibility: visible;
    transition: opacity 0.3s;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

/* 子菜单中的项（li）的样式 */
.mainmenu li .dropdown li {
    list-style: none;
    /* 去除列表默认样式 */
}

/* 子菜单中的链接样式 */
.mainmenu li .dropdown li a {
    font-size: 14px;
    /* 字体大小 */
    color: #333;
    /* 字体颜色 */
    display: block;
    /* 链接独占一行 */
    padding: 6px 10px;
    /* 设置内边距 */
    transition: color 0.3s;
}

/* 悬停子菜单项时改变颜色 */
.mainmenu li .dropdown li a:hover {
    color: #dfa974;
}



/* 移除固定样式，使 header 随页面滚动 */
.header-fixed {
    position: relative;
    /* 不固定在页面顶部 */
    width: 100%;
    background-color: white;
    /* 设置背景色，防止透明 */
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
    /* 添加更轻的阴影 */
}

/* 主菜单项链接样式 */
.mainmenu li a {
    font-size: 14px;
    /* 字体大小 */
    color: #333;
    /* 字体颜色 */
    padding: 10px 0;
    /* 设置上下的内边距 */
    position: relative;
    /* 用于控制伪元素的位置 */
    text-decoration: none;
    /* 禁止浏览器默认下划线 */
    transition: color 0.3s;
}

/* 子菜单（下拉菜单）的样式 */
.mainmenu li .dropdown {
    position: absolute;
    left: 0;
    top: 100%;
    width: 150px;
    background: #fff;
    z-index: 10;
    opacity: 0;
    visibility: visible;
    transition: opacity 0.3s;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

/* 子菜单中的项（li）的样式 */

.mainmenu li .dropdown li {
    list-style: none;
    /*去除列表默认样式 */
}

/* 子菜单中的链接样式 */
.mainmenu li .dropdown li a {
    font-size: 14px;
    /* 字体大小 */
    color: #333;
    /* 字体颜色 */
    display: block;
    /* 链接独占一行 */
    padding: 6px 10px;
    /* 设置内边距 */
    transition: color 0.3s;
}

/* 悬停子菜单项时改变颜色 */
.mainmenu li .dropdown li a:hover {
    color: #dfa974;
}

/* 避免内容被 header 覆盖，给主体内容添加一些顶部填充 */


/* 移除固定样式，使 header 随页面滚动 */
.header-fixed {
    position: relative;
    /* 不固定在页面顶部 */
    width: 100%;
    background-color: white;
    /* 设置背景色，防止透明 */
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
    /* 添加更轻的阴影 */
}

/* 下拉菜单中的项样式 */
.dropdown-item {
    position: relative;
    margin: 5px;
    /* 如果需要调整内边距，可以修改这个值 */
}

.dropdown-item:after {
    content: "";
    position: absolute;
    left: 0;
    right: 0;
    bottom: -3px;
    height: 1px;
    width: auto ; /* 让下划线的宽度与文本长度一致 */
    background: #dfa974; /* 橘线颜色 */
    opacity: 0;
    transition: opacity 0.3s;
}

/* 鼠标悬停或激活时显示下划线 */
.dropdown-item:hover:after,
.dropdown-item.active:after {
    opacity: 1;
}

.dropdown-toggle::after {
    display: none !important; /* 隐藏默认箭头 */
}



</style>