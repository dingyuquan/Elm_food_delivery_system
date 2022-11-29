import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'
import Orders from '../views/Orders.vue'
import OrderList from '../views/OrderList.vue'
import UserAddress from '../views/UserAddress.vue'
import Payment from '../views/Payment.vue'
import AddUserAddress from '../views/AddUserAddress.vue'
import EditUserAddress from '../views/EditUserAddress.vue'
import Register from '../views/Register.vue'
import UserInfo from '../views/UserInfo.vue'
import Balance from '../views/Balance.vue'
import Points from '../views/Points.vue'
import BalanceDetail from '../views/BalanceDetail.vue'
import PointShop from '../views/PointShop.vue'
import PointsDetail from '../views/PointsDetail.vue'

Vue.use(VueRouter)

const routes = [{
		path: '/',
		name: 'home',
		component: Index
	},
	{
		path: '/index',
		name: 'home',
		component: Index
	},
	{
		path: '/businessList',
		name: 'BusinessList',
		component: BusinessList
	},
	{
		path: '/businessInfo',
		name: 'BusinessInfo',
		component: BusinessInfo
	},
	{
		path: '/login',
		name: 'Login',
		component: Login
	},
	{
		path: '/orders',
		name: 'Orders',
		component: Orders
	},
	{
		path: '/orderList',
		name: 'OrderList',
		component: OrderList
	},
	{
		path: '/UserAddress',
		name: 'UserAddress',
		component: UserAddress
	},
	{
		path: '/Payment',
		name: 'Payment',
		component: Payment
	},
	{
		path: '/addUserAddress',
		name: 'AddUserAddress',
		component: AddUserAddress
	},
	{
		path: '/editUserAddress',
		name: 'EditUserAddress',
		component: EditUserAddress
	},
	{
		path: '/register',
		name: 'Register',
		component: Register
	},
	{
		path: '/userInfo',
		name: 'UserInfo',
		component: UserInfo
	},
	{
		path: '/balance',
		name: 'Balance',
		component: Balance
	},
	{
		path: '/points',
		name: 'Points',
		component: Points
	},
	{
		path: '/balanceDetail',
		name: 'BalanceDetail',
		component: BalanceDetail
	},
	{
		path: '/pointShop',
		name: 'PointShop',
		component: PointShop
	},
	{
		path: '/pointsDetail',
		name: 'PointsDetail',
		component: PointsDetail
	}
]

// 解决重复路由报异常问题
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

export default router
