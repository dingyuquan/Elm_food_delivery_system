<template>
    <div class="wrapper">
        <!-- header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="backpage()"></div>
			<div class="location-box"></div>
			<div class="location-text">积分明细</div>
		</header>

        <!-- 积分明细 -->
        <el-tabs class="pointsDetail" v-model="activeName" @tab-click="handleClick">
                <el-tab-pane :stretch=true label=" 获取积分明细 " name="first">
                    <ul v-for="item in gainScoreDetailArr">
                        <div class="pointsDetail-left">
                            <p v-if="item.channelId==1">支付获取</p>
							<p v-if="item.channelId==2">注册获取</p>
							<p v-if="item.channelId==null">获取积分</p>
                            <div class="time">
								<p>获取时间：{{item.createDate}}</p>
								<p>过期时间：{{item.expireDate}}</p>
							</div>
                        </div>
                        <div class="pointsDetail-right">
                            <p>{{item.credit}}</p>
                        </div>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label=" 使用积分明细 " name="second">
                    <ul v-for="item in useScoreDetailArr">
                        <div class="pointsDetail-left">
                            <p v-if="item.channelId==3">订单抵扣</p>
                            <p v-if="item.channelId==4">兑换礼品</p>
							<p v-if="item.channelId==null">花费积分</p>
                            <div class="time">{{item.createDate}}</div>
                        </div>
                        <div class="pointsDetail-right">
                            <p>{{item.credit}}</p>
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
				gainScoreDetailArr:[],
				useScoreDetailArr:[]
            };
        },
        created(){
            this.user = this.$getSessionStorage('user');
            this.$axios.post('ScoreController/listGainLog', this.$qs.stringify({
            	userId: this.user.userId
            })).then(response => {
            	console.log(response.data);
            	this.gainScoreDetailArr = response.data;
            }).catch(error => {
            	console.error(error);
            });
			this.$axios.post('ScoreController/listUseLog', this.$qs.stringify({
				userId: this.user.userId
			})).then(response => {
				console.log(response.data);
				this.useScoreDetailArr = response.data;
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

    /********************* 积分明细 *************************/
    .wrapper .pointsDetail{
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: top;
        flex-direction: column;
        align-items: center;
        margin-top: 12vw;
    }
    .wrapper .pointsDetail ul{
		width: 94vw;
        height: 24vw;
        margin-bottom: 2vw;
        border-radius: 8px;

		display: flex;
		justify-content: center;
		align-items: center;

        background-color: #c6d8e542;
	}
	.wrapper .pointsDetail .pointsDetail-left{
		width: 70%;
		display: flex;
		flex-direction: column;
		justify-content: center;
        margin-left: 3vw;
	}
    .wrapper .pointsDetail .pointsDetail-left p{
        font-size:4.5vw;
    }
    .wrapper .pointsDetail .pointsDetail-left .time{
        color: #888;
        margin-top: 1.5vw;
    }
	.wrapper .pointsDetail .pointsDetail-left .time p{
		font-size: 3.5vw;
	}
	.wrapper .pointsDetail .pointsDetail-right{
		width: 30%;
		display: flex;
		flex-direction: column;
		justify-content: center;
        align-items: center;
        margin-left: 3vw;
	}
	.wrapper .pointsDetail .pointsDetail-right p{
		font-size: 5vw;
	}
</style>