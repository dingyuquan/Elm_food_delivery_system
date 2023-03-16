<template>
    <div class="wrapper">
        <!-- header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="backpage()"></div>
			<div class="location-box"></div>
			<div class="location-text">余额</div>
		</header>

		<!-- 余额部分 -->
		<div class="balance">
			<!--我的余额-->
			<div class="myBalance">
				<div class="myBalance-h">我的余额（元）</div>
				<p>{{this.balance.toFixed(2)}}</p>
			</div>
			<!--充值提现-->
			<div class="recharge-withdraw">
				<div class="recharge">
					<p @click="rechargeDialog = true">充值</p>
					<el-dialog
						title="充值金额"
						:visible.sync="rechargeDialog"
						width="80%"
						center>
						<el-input v-model="rechargeInput" placeholder="￥0.00"></el-input>
						<span slot="footer" class="dialog-footer">
							<el-button @click="rechargeDialog = false">取 消</el-button>
							<el-button type="primary" @click="recharge()">确 定</el-button>
						</span>
					</el-dialog>
				</div>
				<div class="withdraw">
					<p @click="withdrawDialog = true">提现</p>
					<el-dialog
						title="提现金额"
						:visible.sync="withdrawDialog"
						width="80%"
						center>
						<el-input v-model="withdrawInput" placeholder="￥0.00"></el-input>
						<span slot="footer" class="dialog-footer">
							<el-button @click="withdrawDialog = false">取 消</el-button>
							<el-button type="primary" @click="withdraw()">确 定</el-button>
						</span>
					</el-dialog>
				</div>
			</div>
		</div>

		<!-- 余额明细 -->
		<div class="BalanceDetail">
			<div class="BalanceDetail-h">
				<h4>余额明细</h4>
				<p @click="toBalanceDetail()">查看全部 &gt;</p>
			</div>
			
		</div>

    </div>
</template>

<script>
    export default{
		name: 'Balance',
		data() {
			return {
				rechargeDialog: false,
				withdrawDialog: false,
				rechargeInput: '',
				withdrawInput: '',
				balance: 0
			}
		},
		created(){
			this.user = this.$getSessionStorage('user');
			let url = `http://localhost:40100/VirtualWalletController/getBalanceByUserId/${this.user.userId}`;
			this.$axios.get(url).then(response => {
				this.balance = response.data.result;
			}).catch(error => {
				console.error(error);
			});
		},
        methods:{
            backpage(){
				this.$router.go(-1);
			},
            toBalanceDetail(){
                this.$router.push({path:'/balanceDetail'});
            },
			recharge(){
				let url1 = `http://localhost:40100/VirtualWalletController/cashinByWalletId/${this.user.userId}/${this.rechargeInput}`;
				this.$axios.put(url1).then(response => {
					let res = response.data.result;
					if(res == 1){
						this.$message({
							message: '充值成功！',
							type: 'success',
							duration: 1500
						});
						let url2 = `http://localhost:40100/VirtualWalletController/getBalanceByUserId/${this.user.userId}`;
						this.$axios.get(url2).then(response => {
							this.balance = response.data.result;
						}).catch(error => {
							console.error(error);
						});
						this.rechargeDialog = false;
					}else{
						this.$message({
							message: '充值失败！',
							type: 'error',
							duration: 1500
						});
						this.rechargeDialog = false;
					}
					this.rechargeInput = '';
				}).catch(error => {
					console.error(error);
				});
			},
			withdraw(){
				let url3 = `http://localhost:40100/VirtualWalletController/cashoutByWalletId/${this.user.userId}/${this.withdrawInput}`;
				this.$axios.put(url3).then(response => {
					let res = response.data.result;
					if(res == 1){
						this.$message({
							message: '提现成功！',
							type: 'success',
							duration: 1500
						});
						let url4 = `http://localhost:40100/VirtualWalletController/getBalanceByUserId/${this.user.userId}`;
						this.$axios.post(url4).then(response => {
							this.balance = response.data.result;
						}).catch(error => {
							console.error(error);
						});
						this.withdrawDialog = false;
					}else{
						this.$message({
							message: '提现失败！',
							type: 'error',
							duration: 1500
						});
						this.withdrawDialog = false;
					}
					this.withdrawInput = '';
				}).catch(error => {
					console.error(error);
				});
			}
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
	.wrapper header .location-box{
		width: 28%;
	}
	.wrapper header .location-text{
		font-size: 4.8vw;
		font-weight: 500;
		color: #fff;
		position:fixed;
	}
	.wrapper header .fa-angle-left{
		margin-left: -6cm;
		font-weight: 700;
		font-size: 0.8cm;
	}
	/********************* 余额部分 *************************/
	.wrapper .balance{
		width: 92%;
		height: 50vw;
		margin: 15vw auto 5vw auto;
		border-radius: 8px;
		background-color:#c6d8e56a;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.wrapper .balance .myBalance{
		width: 100%;
		height: 35vw;
		display: flex;
		justify-content: center;
		flex-direction: column;
		align-items: center;
	}
	.wrapper .balance .myBalance .myBalance-h{
		font-size: 6.5vw;
	}
	.wrapper .balance .myBalance p{
		margin-top: 3vw;
		font-size: 5.8vw;
	}
	.wrapper .balance .recharge-withdraw{
		width: 100%;
		height: 20vw;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.wrapper .balance .recharge-withdraw .recharge{
		width: 18vw;
		height: 10vw;
		border-radius: 8px;
		margin-right: 15vw;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 5vw;
		background-color:#0099ff62;
	}
	.wrapper .balance .recharge-withdraw .withdraw{
		width: 18vw;
		height: 10vw;
		border-radius: 8px;
		margin-left: 15vw;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 5vw;
		background-color: #0099ff62;
	}
	/********************* 余额明细 *************************/
	.wrapper .BalanceDetail{
		width: 92%;
		height: 15vw;
		border-radius: 8px;
		margin: 0 auto;
		background-color: #c6d8e542;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.wrapper .BalanceDetail .BalanceDetail-h{
		width: 100%;
		height: 10vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.wrapper .BalanceDetail .BalanceDetail-h h4{
		width: 100%;
		margin-left: 4vw;
		font-size: 4.5vw;
	}
	.wrapper .BalanceDetail .BalanceDetail-h p{
		width: 35vw;
		margin-right: 3vw;
		font-size: 4.3vw;
		color: rgb(95, 94, 94);
		cursor: pointer;
	}
</style>