import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
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
