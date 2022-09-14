import { createRouter, createWebHistory } from 'vue-router'
import Index from '../views/index.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Index
  },
  {
	path: '/',
	name: 'Index',
	component: Index
  }
]

// 解决重复路由报异常问题
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
