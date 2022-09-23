import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'
import Orders from '../views/Orders.vue'
import OrderList from '../views/OrderList.vue'
import UserAddress from '../views/UserAddress.vue'
import Payment from '../views/Payment.vue'

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
