<template>
    <div class="wrapper">
        <!-- header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="backpage()"></div>
			<div class="location-box"></div>
			<div class="location-text">余额明细</div>
		</header>

        <!-- 余额明细 -->
        <el-tabs class="balanceDtail" v-model="activeName" @tab-click="handleClick">
                <el-tab-pane :stretch=true label="-- 全部 --" name="first">
                    <ul v-for="item in detailArr">
                        <div class="BalanceDetail-left">
                            <p v-if="item.transactionType==0">支付</p>
							<p v-if="item.transactionType==1">充值</p>
							<p v-if="item.transactionType==2">提现</p>
                            <div class="time">{{item.transactionDate}}</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p v-if="item.transactionType==1">{{item.transactionAmount.toFixed(2)}}</p>
							<p v-if="item.transactionType==0||item.transactionType==2">-{{item.transactionAmount.toFixed(2)}}</p>
                        </div>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label="-- 收入 --" name="second">
                    <ul v-for="item in detailArr" v-if="item.transactionType==1">
                        <div class="BalanceDetail-left">
                            <p>充值</p>
                            <div class="time">{{item.transactionDate}}</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>{{item.transactionAmount.toFixed(2)}}</p>
                        </div>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label="-- 支出 --" name="third">
                    <ul v-for="item in detailArr" v-if="item.transactionType==0||item.transactionType==2">
                        <div class="BalanceDetail-left">
                            <p v-if="item.transactionType==0">支付</p>
							<p v-if="item.transactionType==2">提现</p>
                            <div class="time">{{item.transactionDate}}</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>-{{item.transactionAmount.toFixed(2)}}</p>
                        </div>
                    </ul>
                </el-tab-pane>
            </el-tabs>
    </div>
    
</template>

<script>
    export default{
        data() {
            return {
                activeName: 'first',
				detailArr:[]
            };
        },
        created(){
            this.user = this.$getSessionStorage('user');
			<!-- 此处需要改动 -->
            this.$axios.post('VirtualWalletController/getTransactionByUserId', this.$qs.stringify({
            	userId: this.user.userId
            })).then(response => {
            	this.detailArr = response.data;
            }).catch(error => {
            	console.error(error);
            });
        },
        methods:{
            backpage(){
				this.$router.go(-1);
			},
            handleClick(tab, event) {
                
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

    /********************* 余额明细 *************************/
    .wrapper .balanceDtail{
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: top;
        flex-direction: column;
        align-items: center;
        margin-top: 12vw;
    }
    .wrapper .balanceDtail ul{
		width: 94vw;
        height: 20vw;
        margin-bottom: 2vw;
        border-radius: 8px;

		display: flex;
		justify-content: center;
		align-items: center;

        background-color: #c6d8e542;
        cursor: pointer;
	}
	.wrapper .balanceDtail .BalanceDetail-left{
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
        margin-left: 3vw;
	}
    .wrapper .balanceDtail .BalanceDetail-left p{
        font-size:4.5vw;
    }
    .wrapper .balanceDtail .BalanceDetail-left .time{
        font-size: 4vw;
        color: #888;
        margin-top: 1.5vw;
    }
	.wrapper .balanceDtail .BalanceDetail-right{
		width: 100%;
		display: flex;
		justify-content: right;
        margin-right: 3vw;
		align-items: center;
		font-size: 5vw;
	}

</style>