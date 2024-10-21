import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '@/views/member/login.vue';
import Register from '@/views/member/register.vue';
import Detail from '@/views/member/detail.vue';
import updateDetail from '@/views/member/updateDetail.vue';
import setDetail from '@/views/member/setDetail.vue';
import errorPage403 from '@/views/errorPage/403.vue';
import resetPassword from '@/views/member/resetPassword.vue';
import checkRamdon from '@/views/member/checkRamdon.vue';
import historyComment from '@/views/member/historyComment.vue';
import order from'@/views/member/order.vue';
import favorite from'@/views/member/favorite.vue';
import resetPasswordFromDetail from'@/views/member/resetPasswordFromDetail.vue';
// /*-------------------------------------------------*/
// import createHotel from "@/views/Hotel/createHotel.vue"
// import findHotelsByCompanys from "@/views/Hotel/findHotelsByCompanys.vue";
// import ModifyHotel from "@/views/Hotel/modifyHotel.vue";
// import FindRoomsByHotels from "@/views/Room/findRoomsByHotels.vue";
// import createRoom from "@/views/Room/createRoom.vue";
// import ModifyRoom from "@/views/Room/modifyRoom.vue";
// import FindHotel from "@/views/Find/findHotel.vue";
// import HotelResults from "@/views/Find/hotelResults.vue";
// import RoomResults from "@/views/Find/roomResults.vue";
// /*-------------------------------------------------*/
// import Order from '@/views/Order/Order.vue';
// import OrderSummary from '@/views/Order/OrderSummary.vue';
// import OrderDetail from '@/views/Order/OrderDetail.vue';
// import CompanyOrderDetailsView from '@/views/Order/CompanyOrderDetailsView.vue';
// import CompanyControllerOrder from '@/views/Order/CompanyControllerOrder.vue';
// import CustomerControllerOrder from '@/views/Order/CustomerControllerOrder.vue';
// import CustomerOrderDetailsView from '@/views/Order/CustomerOrderDetailsView.vue';
// import AdminControllerOrder from '@/views/Order/AdminControllerOrder.vue';
// import AdminOrderDetailsView from '@/views/Order/AdminOrderDetailsView.vue';
// import PaymentSuccess from '@/views/Order/PaymentSuccess.vue';
// import LinePaymentSuccess from '@/views/Order/LinePaymentSuccess.vue';
// /*-------------------------------------------------*/

// import CompanyLogin from '@/components/CompanyLogin.vue';
// import CompanySignUp from '@/components/CompanySignUp.vue';
// import CompanyEdit from '@/components/CompanyEdit.vue';
// import EmailVerification from '@/components/EmailVerification.vue';
// import ForgetPassword from '@/components/ForgetPassword.vue';
// import CompanyPasswordReset from '@/components/CompanyPasswordReset.vue';
// //import GuestQuestionReply from '@/components/GuestQuestionReply.vue';
// /*-------------------------------------------------*/
// import hotelMap from '@/views/Map/hotelMap.vue';
// /*-------------------------------------------------*/
// import AnswerQustion from '@/views/CompanyHotelQuestion.vue';
// import CompanyAnswerQuestion from '@/views/CompanyAnswerQuestion.vue';
// import FavoritesList from '@/views/FavoritesList.vue';

const routes = [
  { path: "/", name: "home", component: HomeView },
  { path: "/member/login", name: "login-link", component: Login },
  { path: "/member/register", name: "register-link", component: Register },
  { path: "/member/detail", name: "detail-link", component: Detail, meta: { requiresAuth: true , userType: 'member', roles: ['一般會員'] } },
  { path: "/member/updateDetail", name: "updateDetail-link", component: updateDetail, meta: { requiresAuth: true  ,userType: 'member', roles: ['一般會員'] } },
  { path: "/member/setDetail", name: "setDetail-link", component: setDetail },
  { path: "/403", name: "Forbidden", component: errorPage403 },
  { path: "/member/resetPassword", name: "resetPassword-link", component: resetPassword },
  { path: "/member/checkRamdon", name: "checkRamdon-link", component: checkRamdon },
  { path: "/member/historyComment", name: "historyComment-link", component: historyComment },
  { path: "/member/order", name: "order-link", component: order },
  { path: "/member/favorite", name: "favorite-link", component: favorite },
  { path: "/member/resetPasswordFromDetail", name: "resetPasswordFromDetail-link", component: resetPasswordFromDetail },
//   // /*-------------------------------------------------*/
//   { path: "/Hotel/createHotel", name: "createHotel-link", component: createHotel , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] }},
//   { path: "/Hotel/findHotelsByCompanys", name: "findHotelsByCompanys-link", component: findHotelsByCompanys , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] }},
//   { path: "/Hotel/modifyHotel/:id", name: "modifyHotel-link", component: ModifyHotel, props: route => ({ id: Number(route.params.id) }) , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] }},
//   { path: "/Room/findRoomsByHotels/:id", name: "findRoomsByHotels-link", component: FindRoomsByHotels, props: route => ({ id: Number(route.params.id) }) , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] } },
//   { path: "/Room/createRoom/:id", name: "createRoom-link", component: createRoom, props: route => ({ id: Number(route.params.id) }) , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] } },
//   { path: "/Room/modifyRoom/:id", name: "modifyRoom-link", component: ModifyRoom, props: route => ({ id: Number(route.params.id) }) , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] } },
//   { path: "/HotelBooking/findHotel", name: "findHotel-link", component: FindHotel },
//   { path: "/HotelBooking/hotelResults", name: "hotelResults-link", component: HotelResults },
//   { path: "/HotelBooking/roomResults", name: "roomResults-link", component: RoomResults },
//   /*-------------------------------------------------*/
//   { path: '/order', name: "Order", component: Order , meta: { requiresAuth: true , userType: 'member', roles: ['一般會員'] }},
//   { path: '/orderdetail', name: "OrderDetail", component: OrderDetail , meta: { requiresAuth: true , userType: 'member', roles: ['一般會員'] }},
//   { path: '/paymentSuccess', name: "paymentSuccess", component: PaymentSuccess , meta: { requiresAuth: true , userType: 'member', roles: ['一般會員'] }},
//   { path: '/linepaymentSuccess', name: "LinepaymentSuccess", component: LinePaymentSuccess , meta: { requiresAuth: true , userType: 'member', roles: ['一般會員'] }},
//   { path: "/adminOrder", name: "adminOrder-link", component: AdminControllerOrder },
//   { path: "/companyOrderDetail/:orderId", name: "OrderCompanyDetailsView", component: CompanyOrderDetailsView, props: true  , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] } },
//   { path: "/adminOrderDetail/:orderId", name: "OrderDetailsView", component: AdminOrderDetailsView, props: true },
//   { path: "/customerOrderDetailsView/:orderId", name: "OrderCustomerDetailsView", component: CustomerOrderDetailsView, props: true , meta: { requiresAuth: true , userType: 'member', roles: ['一般會員'] }},
//   { path: "/companyOrder", name: "companyOrder-link", component: CompanyControllerOrder , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] } },
//   { path: "/customerOrder", name: "customerOrder-link", component: CustomerControllerOrder , meta: { requiresAuth: true , userType: 'member', roles: ['一般會員'] }},
//   { path: '/order-summary', name: "OrderSummary", component: OrderSummary },
//   /*-------------------------------------------------*/
//   { path: "/companyLogin", component: CompanyLogin },
//   { path: "/companySignUp", component: CompanySignUp },
//   { path: "/companyEdit", component: CompanyEdit , meta: { requiresAuth: true, userType: 'company', roles: ['公司'] }},
//   { path: '/verify', component: EmailVerification },
//   { path: '/forgetPassword', component: ForgetPassword },
//   { path: '/companyPasswordReset', component: CompanyPasswordReset },
//   //{ path: '/questionReply', component: GuestQuestionReply },

//   /*-------------------------------------------------*/
//   { path: '/company/addAnswerList', name: "addAnswerList", component: AnswerQustion },
//   { path: '/company/addAnswer/:id', name: "addAnswer", component: CompanyAnswerQuestion, props: route => ({ id: Number(route.params.id) }) },
//   { path: '/member/FavoritesList', name: "FavoritesList", component: FavoritesList }

];
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes,
});

//-----------------------------
import useMemberStore from '@/stores/member';
// import useCompanyStore from '@/stores/company';

router.beforeEach(function (to, from, next) {
  let memberLevel = '訪客';
  const memberStore = useMemberStore();

  // const CompanyStore = useCompanyStore();
  if (memberStore.login) {
    memberLevel="一般會員";
  // }else if(CompanyStore.login){
  //   memberLevel="公司";
  }else{
    memberLevel='訪客';

  }
  if (to.meta.requiresAuth && memberLevel == '訪客') {
    if (to.meta.userType === 'company') {
    //   next('/companyLogin');
    // } else {
      next('/member/login');
    }
  } else if (to.meta.roles && !to.meta.roles.includes(memberLevel)) {
    next('/errorPage/403');
  } else {
    next();
  }
});
//-----------------------------
export default router