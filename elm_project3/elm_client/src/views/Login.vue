<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>用户登录</p>
		</header>

		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					手机号码：
				</div>
				<div class="content">
					<input type="text" v-model="userId" placeholder="手机号码">
				</div>
			</li>
			<li>
				<div class="title">
					密码：
				</div>
				<div class="content">
					<input type="password" v-model="password" placeholder="密码">
				</div>
			</li>
		</ul>

		<div class="button-login">
			<button @click="login">登录</button>
		</div>
		<div class="button-register">
			<button @click="register">去注册</button>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';
	
	export default{
		name:'Login',
		data(){
			return{
				userId:'',
				password:''
			}
		},
		methods:{
			login(){
				const reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
				if (this.userId == '') {
					this.imgShow = false;
					this.$message({
						message: "手机号不能为空！",
						type: 'warning',
						duration: 1500
					});
					return;
				}
				if (this.userId.length != 11 || !reg.test(this.userId)) {
					this.imgShow = false;
					this.$message({
						message: "请填写正确的手机号！",
						type: 'warning',
						duration: 1500
					});
					return;
				}
				if(this.password==''){
					this.$message({
						message: '密码不能为空!',
						type: 'warning',
						duration: 1500
					});
					return;
				}
				
				//登录请求
				this.$axios.post('UserController/getUserByIdByPass', this.$qs.stringify({
					userId: this.userId,
					password:this.password
				})).then(response => {
					let user = response.data;
					if(user==null){
						this.$message.error('用户名或密码不正确!');
					}else{
						//防止数据溢出，不将用户头像放入setSessionStorage（setSessionStorage有容量限制）
						user.userImg='';
						this.$setSessionStorage('user',user);
						this.$router.go(-1);
					}
				}).catch(error => {
					console.error(error);
				});
			},
			register(){
				this.$router.push({path:'register'});
			}
		},
		components:{
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

	/********************* header *************************/
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

	/********************* 表单 *************************/
	.wrapper .form-box {
		width: 100%;
		margin-top: 12vw;
	}

	.wrapper .form-box li {
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;

		display: flex;
		align-items: center;
	}

	.wrapper .form-box li .title {
		flex: 0 0 18vw;
		font-size: 3vw;
		font-weight: 700;
		color: #666;
	}

	.wrapper .form-box li .content {
		flex: 1;
	}

	.wrapper .form-box li .content input {
		border: none;
		outline: none;
		width: 100%;
		height: 4vw;
		font-size: 3vw;
	}

	.wrapper .button-login {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-login button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		color: #fff;
		background-color: #38CA73;
		border-radius: 4px;

		border: none;
		outline: none;
	}

	.wrapper .button-register {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-register button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		color: #666;
		background-color: #EEE;
		border-radius: 4px;

		border: none;
		outline: none;
		border: solid 1px #DDD;
	}
</style>
