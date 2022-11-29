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
                    <ul>
                        <div class="BalanceDetail-left">
                            <p>余额支付</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>- 0.50</p>
                        </div>
                    </ul>
                    <ul>
                        <div class="BalanceDetail-left">
                            <p>银行卡充值</p>
                            <div class="time">2022-11-1 12:00</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>+ 20.00</p>
                        </div>
                    </ul>
                    <ul>
                        <div class="BalanceDetail-left">
                            <p>余额支付</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>- 0.50</p>
                        </div>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label="-- 收入 --" name="second">
                    <ul>
                        <div class="BalanceDetail-left">
                            <p>银行卡充值</p>
                            <div class="time">2022-11-1 12:00</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>+ 20.00</p>
                        </div>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label="-- 支出 --" name="third">
                    <ul>
                        <div class="BalanceDetail-left">
                            <p>余额支付</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>- 0.50</p>
                        </div>
                    </ul>
                    <ul>
                        <div class="BalanceDetail-left">
                            <p>余额支付</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="BalanceDetail-right">
                            <p>- 0.50</p>
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
                activeName: 'first'
            };
        },
        created(){
            
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