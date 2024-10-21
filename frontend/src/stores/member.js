import { defineStore } from "pinia";
import { ref, computed } from "vue";

const useMemberStore = defineStore("member", function () {
    const login = ref(false);
    const islogin = computed(function () {
        return login.value;
    });
    function setLogin(data) {
        login.value = data;
    }
    const memberInfo = ref("");
    function setMemberInfo(data) {
        memberInfo.value = data;
    }
    const memberEmail = ref("");
    function setMemberEmail(data) {
        memberEmail.value = data;
    }
    const memberName = ref("");
    function setMemberName(data) {
        memberName.value = data;
    }
    return {
        memberInfo, setMemberInfo, login, setLogin,memberEmail, setMemberEmail,memberName,setMemberName
    }
}, {
    persist: {
        storage: sessionStorage,
        path: ["memberInfo", "login","memberName"]
    }
});
export default useMemberStore;