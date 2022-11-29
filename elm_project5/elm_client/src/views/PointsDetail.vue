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
                <el-tab-pane :stretch=true label="-- 已获取 --" name="first">
                    <ul>
                        <div class="pointsDetail-left">
                            <p>新用户注册</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="pointsDetail-right">
                            <p>+ 200</p>
                            <button>去使用</button>
                        </div>
                    </ul>
                    <ul>
                        <div class="pointsDetail-left">
                            <p>外卖水果新客</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="pointsDetail-right">
                            <p>+ 50</p>
                            <button>去使用</button>
                        </div>
                    </ul>
                    <ul>
                        <div class="pointsDetail-left">
                            <p>外卖水果新客</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="pointsDetail-right">
                            <p>+ 50</p>
                            <button>去使用</button>
                        </div>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label="-- 已使用 --" name="second">
                    <ul>
                        <div class="pointsDetail-left">
                            <p>积分支付</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="pointsDetail-right">
                            <p>- 0.50</p>
                        </div>
                    </ul>
                    <ul>
                        <div class="pointsDetail-left">
                            <p>积分支付</p>
                            <div class="time">2022-11-12 12:00</div>
                        </div>
                        <div class="pointsDetail-right">
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
        height: 20vw;
        margin-bottom: 2vw;
        border-radius: 8px;

		display: flex;
		justify-content: center;
		align-items: center;

        background-color: #c6d8e542;
	}
	.wrapper .pointsDetail .pointsDetail-left{
		width: 65%;
		display: flex;
		flex-direction: column;
		justify-content: center;
        margin-left: 3vw;
	}
    .wrapper .pointsDetail .pointsDetail-left p{
        font-size:4.5vw;
    }
    .wrapper .pointsDetail .pointsDetail-left .time{
        font-size: 4vw;
        color: #888;
        margin-top: 1.5vw;
    }
	.wrapper .pointsDetail .pointsDetail-right{
		width: 35%;
		display: flex;
		flex-direction: column;
		justify-content: center;
        align-items: center;
        margin-left: 3vw;
	}
    .wrapper .pointsDetail .pointsDetail-right button{
        width: 15vw;
        height: 7vw;
        border: none;
		outline: none;
		border-radius: 4px;
        background-color: #0099ff8a;
		color: #fff;
        font-size: 3.2vw;
        margin-top: 1vw;
    }
</style>