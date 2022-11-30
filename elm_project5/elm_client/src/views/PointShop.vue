<template>
    <div class="wrapper">
        <!-- header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="backpage()"></div>
			<div class="location-box"></div>
			<div class="location-text">积分商城</div>
		</header>

        <ul class="present">
			<li>
				<div class="present-left">
                    <p>卫生纸</p>
                    <div class="presentPoints">80</div>
                </div>
                <div class="present-right">
                    <button @click="exchange(80)">兑换</button>
                </div>
			</li>
            <li>
				<div class="present-left">
                    <p>鼠标</p>
                    <div class="presentPoints">60</div>
                </div>
                <div class="present-right">
                    <button @click="exchange(60)">兑换</button>
                </div>
			</li>
            <li>
				<div class="present-left">
                    <p>笔记本电脑</p>
                    <div class="presentPoints">50</div>
                </div>
                <div class="present-right">
                    <button @click="exchange(50)">兑换</button>
                </div>
			</li>
			<li>
				<div class="present-left">
			        <p>篮球</p>
			        <div class="presentPoints">25</div>
			    </div>
			    <div class="present-right">
			        <button @click="exchange(25)">兑换</button>
			    </div>
			</li>
			<li>
				<div class="present-left">
			        <p>水杯</p>
			        <div class="presentPoints">1</div>
			    </div>
			    <div class="present-right">
			        <button @click="exchange(1)">兑换</button>
			    </div>
			</li>
		</ul>
    </div>
</template>

<script>
    export default {
		data() {
			return {
				credit:0,
			}
		},
		created(){
			this.user = this.$getSessionStorage('user');
			this.$axios.post('ScoreController/getCredit', this.$qs.stringify({
				userId: this.user.userId
			})).then(response => {
				console.log(response.data);
				this.credit = response.data;
			}).catch(error => {
				console.error(error);
			});
		},
        methods:{
            backpage(){
				this.$router.go(-1);
			},
            exchange(score){
				console.log(score);
                this.$confirm('是否兑换该礼品?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    center: true
                    }).then(() => {
						console.log(this.credit);
						if(score>this.credit){
							this.$message({
							    type: 'warning',
							    message: '积分不足!',
								duration: 1000
							});
						}else{
							this.$axios.post('ScoreController/expendCredit', this.$qs.stringify({
								userId: this.user.userId,
								credit: score,
								channelId: 4
							})).then(response => {
								let res = response.data;
								if(res > 0){
									this.$message({
									    type: 'success',
									    message: '兑换成功!',
										duration: 1000
									});
								}
							}).catch(error => {
								console.error(error);
							});
						}
                    }).catch(() => {
                        this.$message({
                            message: '已取消',
							duration: 1500
                        });          
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
		position:fixed;
	}
	
	.wrapper header .fa-angle-left{
		margin-left: -6cm;
		font-weight: 700;
		font-size: 0.8cm;
	}
    /********************* 礼品兑换 *************************/
    .wrapper .present{
        width: 100%;
        margin-top: 12vw;

        display: flex;
		justify-content: center;
        flex-direction: column;
        align-items: center;
    }
    .wrapper .present li{
        width: 94vw;
        height: 20vw;
        margin-top: 2vw;
        border-radius: 8px;
        background-color: #c6d8e542;

        display: flex;
		justify-content: center;
        align-items: center;
    }
    .wrapper .present li .present-left{
        width: 60%;
		display: flex;
		flex-direction: column;
		justify-content: center;
        margin-left: 3vw;
    }
    .wrapper .present li .present-left p{
        width: 100%;
        height: 10vw;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 5vw;
    }
    .wrapper .present li .present-left .presentPoints{
        width: 100%;
        height: 10vw;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 4.5vw;
    }
    .wrapper .present li .present-right{
        width: 40%;
		display: flex;
		justify-content: center;
        align-items: center;
        margin-right: 3vw;
    }
    .wrapper .present li button{
        width: 15vw;
        height: 8vw;
        border: none;
		outline: none;
		border-radius: 4px;
        background-color: #0099ff8a;
		color: #fff;
        font-size: 4vw;
    }
</style>