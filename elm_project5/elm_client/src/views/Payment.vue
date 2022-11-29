<template>
	<div class="wrapper">

		<!--header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="backpage()"></div>
			<div class="location-box"></div>
			<div class="location-text">在线支付</div>
		</header>

		<!-- 订单信息部分 -->
		<h3>订单信息：</h3>
		<div class="order-info">
			<p>
				{{orders.business.businessName}}
				<i class="fa fa-caret-down" @click="detailetShow"></i>
			</p>
			<p>&#165;{{orders.orderTotal - useScore*0.1}}</p>
		</div>

		<!-- 订单明细部分 -->
		<ul class="order-detailed" v-show="isShowDetailet">
			<li v-for="item in orders.list">
				<p>{{item.food.foodName}} x {{item.quantity}}</p>
				<p>&#165;{{item.food.foodPrice*item.quantity}}</p>
			</li>
			<li>
				<p>配送费</p>
				<p>&#165;{{orders.business.deliveryPrice}}</p>
			</li>
			<li>
				<p>订单总额</p>
				<p>&#165;{{orders.orderTotal}}</p>
			</li>
		</ul>

		<!-- 支付方式部分 -->
		<ul class="payment-type">
			<!-- <li>
				<img src="../assets/alipay.png">
				<i class="fa fa-check-circle"></i>
			</li>
			<li>
				<img src="../assets/wechatpay.png">
			</li> -->
			<p style="margin:3vw auto 2vw 3vw; font-size:4.3vw;">是否使用积分抵扣 :</p>
			<input type="radio" v-model="usePoints" value="1" checked style="width:6vw;height: 3.2vw;" @click="pointsPaymentDialog=true">是
			<input type="radio" v-model="usePoints" value="0" style="width:6vw;height: 3.2vw;">否
			<p v-if="useScore>0" style="margin:3vw auto 2vw 3vw; font-size:4vw;">使用积分抵扣{{useScore*0.1}}元</p>
			<el-dialog
				title="请输入积分抵扣金额"
				:visible.sync="pointsPaymentDialog"
				width="80%"
				center>
				<p>当前积分余额：{{this.score}}</p>
				<p>本订单最大可抵扣金额：{{(orders.orderTotal*0.2).toFixed(2)}}</p>
				<el-input v-model="input" placeholder="￥0.00"></el-input>
				<span slot="footer" class="dialog-footer">
					<el-button @click="pointsPaymentDialog = false">取 消</el-button>
					<el-button type="primary" @click="useScores()">确 定</el-button>
				</span>
			</el-dialog>
		</ul>
		<div class="payment-button">
			<button @click="payment()">确认支付</button>
		</div>


		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Payment',
		data() {
			return {
				orderId: this.$route.query.orderId,
				orders: {
					business:{}
				},
				isShowDetailet:true,
				pointsPaymentDialog:false,
				usePoints: 0,
				input: '',
				score:0,
				useScore:0
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
			this.$axios.post('OrdersController/getOrdersById', this.$qs.stringify({
				orderId: this.orderId
			})).then(response => {
				this.orders = response.data;
			}).catch(error => {
				console.error(error);
			});
			this.$axios.post('ScoreController/getCredit', this.$qs.stringify({
				userId: this.user.userId
			})).then(response => {
				console.log(response.data);
				this.score = response.data;
			}).catch(error => {
				console.error(error);
			});
		},
		mounted(){
			//这里的代码是实现：一旦路由到在线支付组件，就不能回到订单确认组件
			//先将当前路由添加到history对象中
			history.pushState(null,null,document.URL);
			//popstate能监听变化
			window.onpopstate = () => {
				this.$router.push({path:'/index'});
			}
		},
		destroyed(){
			window.onpopstate = null;
		},
		methods:{
			detailetShow(){
				this.isShowDetailet = !this.isShowDetailet;
			},
			backpage(){
				this.$router.go(-1);
			},
			payment(){
				this.$message({
					message: "支付成功",
					type: 'success',
					duration: 1500
				});
			},
			useScores(){
				console.log(this.input);
				if(this.input*10 > this.score){
					this.$message({
						message: "积分不足",
						type: 'warning',
						duration: 1500
					});
					return;
				}
				if(this.input > this.orders.orderTotal*0.2){
					this.$message({
						message: "超过抵扣额度",
						type: 'warning',
						duration: 1500
					});
					return;
				}
				this.useScore = this.input*10;
				this.pointsPaymentDialog = false;
				return;
			}
		},
		components: {
			Footer
		}
	}
</script>

<style scoped>
	/********************* 总容器 *************************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/********************* header部分 *************************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		color: #fff;
		font-size: 4.8vw;

		position: fixed;
		left: 0;
		top: 0;
		z-index: 1000;

		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.wrapper header .location-box{
		width: 28%;
	}
	
	.wrapper header .location-text{
		font-size: 4.8vw;
		font-weight: 500;
		color: #fff;
	}
	
	.wrapper header .fa-angle-left{
		margin-left: -4cm;
		font-weight: 700;
		font-size: 0.8cm;
	}

	/********************* 订单信息 *************************/
	.wrapper h3 {
		box-sizing: border-box;
		padding: 4vw 4vw 0;

		font-size: 4vw;
		font-weight: 300;
		color: #999;
	}

	.wrapper .order-info {
		box-sizing: border-box;
		padding: 4vw;
		font-size: 4vw;
		color: #666;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order-info p:last-child {
		color: orangered;
	}

	/********************* 订单明细 *************************/
	.wrapper .order-detailed {
		width: 100%;
	}

	.wrapper .order-detailed li {
		width: 100%;
		box-sizing: border-box;
		padding: 1vw 4vw;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order-detailed li p {
		font-size: 3vw;
		color: #666;
	}

	/********************* 支付方式 *************************/
	.wrapper .payment-type {
		width: 100%;
	}

	.wrapper .payment-type li {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .payment-type li img {
		width: 33vw;
		height: 11vw;
	}

	.wrapper .payment-type li .fa-check-circle {
		font-size: 5vw;
		color: #38CA73;
	}

	.wrapper .payment-button {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw;
	}

	.wrapper .payment-button button {
		width: 100%;
		height: 10vw;
		border: none;
		outline: none;
		border-radius: 4px;
		background-color: #38CA73;
		color: #fff;
	}
</style>
