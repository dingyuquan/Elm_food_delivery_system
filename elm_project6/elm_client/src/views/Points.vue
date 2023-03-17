<template>
    <div class="wrapper">
        <!-- header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="backpage()"></div>
			<div class="location-box"></div>
			<div class="location-text">积分</div>
		</header>

		<!-- 我的积分部分 -->
		<div class="points">
			<!--我的积分-->
			<div class="myPoints">
				<div class="myPoints-h">我的积分</div>
				<p>{{this.score.credit}}</p>
			</div>
		</div>

		<!-- 积分明细 -->
		<div class="PointsDetail">
			<div class="PointsDetail-h">
				<h4>积分明细</h4>
				<p @click="toPointsDetail()">查看全部 &gt;</p>
			</div>
		</div>

		<!-- 积分商城 -->
		<div class="PointShop">
			<div class="PointShop-h">
				<h4>积分商城</h4>
				<p @click="toPointShop()">去看看 &gt;</p>
			</div>
		</div>
    </div>
</template>

<script>
    export default{
		name: 'Points',
		data() {
			return {
				score:{
					credit:0
				}
			}
		},
		created(){
			this.user = this.$getSessionStorage('user');
			let url = `http://localhost:10800/ScoreController/getCredit/${this.user.userId}`;
			this.$axios.get(url).then(response => {
				console.log(response.data.result);
				this.score.credit = response.data.result;
			}).catch(error => {
				console.error(error);
			});
		},
        methods:{
            backpage(){
				this.$router.go(-1);
			},
			toPointShop(){
				this.$router.push({path:'/pointShop'})
			},
			toPointsDetail(){
				this.$router.push({path:'/pointsDetail'})
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

	/********************* 我的积分部分 *************************/
	.wrapper .points{
		width: 92%;
		height: 40vw;
		margin: 15vw auto 5vw auto;
		border-radius: 8px;
		background-color:#c6d8e56a;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.wrapper .points .myPoints{
		width: 100%;
		height: 35vw;
		display: flex;
		justify-content: center;
		flex-direction: column;
		align-items: center;
	}
	.wrapper .points .myPoints .myPoints-h{
		font-size: 6.5vw;
	}
	.wrapper .points .myPoints p{
		margin-top: 3vw;
		font-size: 5.8vw;
	}
	/********************* 积分明细 *************************/
	.wrapper .PointsDetail{
		width: 92%;
		height: 20vw;
		border-radius: 8px;
		margin: 0 auto;
		background-color: #c6d8e542;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.wrapper .PointsDetail .PointsDetail-h{
		width: 100%;
		height: 10vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.wrapper .PointsDetail .PointsDetail-h h4{
		width: 100%;
		margin-left: 4vw;
		font-size: 4.5vw;
	}
	.wrapper .PointsDetail .PointsDetail-h p{
		width: 35vw;
		margin-right: 3vw;
		font-size: 4.3vw;
		color: rgb(95, 94, 94);
		cursor: pointer;
	}

	/********************* 积分商城 *************************/
	.wrapper .PointShop{
		width: 92%;
		height: 20vw;
		border-radius: 8px;
		margin: 4vw auto;
		background-color: #c6d8e542;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.wrapper .PointShop .PointShop-h{
		width: 100%;
		height: 11vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.wrapper .PointShop .PointShop-h h4{
		width: 100%;
		margin-left: 4vw;
		font-size: 4.5vw;
	}
	.wrapper .PointShop .PointShop-h p{
		width: 35vw;
		margin-right: 3vw;
		font-size: 4.3vw;
		color: rgb(95, 94, 94);
		cursor: pointer;
	}
	.wrapper .PointShop .PointShop-bottom{
		width: 100%;
		height: 9vw;
		margin-left: 7vw;
	}
</style>