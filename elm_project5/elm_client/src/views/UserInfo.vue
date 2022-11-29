<template>
    <div class="wrapper">
        <!-- header部分 -->
		<header>
			<p>个人中心</p>
		</header>

        <!-- 信息部分 -->
        <div class="user">
            <img src="../assets/userImg.png">
            <div class="user-info">
                <div class="user-name">
                    <div class="user-name-left">{{this.user.userName}}</div>
                    <p v-if="this.user.userSex == 1" class="fa fa-mars" style="margin-top:1.3vw;color:dodgerblue"></p>
                    <p v-else class="fa fa-venus" style="margin-top:1.3vw;color:hotpink"></p>
                </div>
                <div class="user-phone">{{this.user.userId}}</div>
            </div>
        </div>
        
        <!-- 功能部分 -->
        <ul class="function">
            <li>
                <i class="fa fa-star-o"></i>
                <p>收藏</p>
            </li>
            <li>
                <i class="fa fa-paw"></i>
                <p>足迹</p>
            </li>
            <li @click="toUserPoints">
                <i class="fa fa-credit-card"></i>
                <p>积分</p>
            </li>
            <li @click="toUserBalance">
                <i class="fa fa-cny"></i>
                <p>余额</p>
            </li>
        </ul>

        <!-- 底部菜单部分 -->
	    <Footer></Footer>
    </div>
</template>

<script>
    import Footer from '../components/Footer.vue';
    export default{
        name: 'UserInfo',
        created(){
            this.user = this.$getSessionStorage('user');
        },
        methods:{
            toUserPoints(){
                this.$router.push({path:'/points'});
            },
            toUserBalance(){
                this.$router.push({path:'/balance'});
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

    /************************ 信息部分 ************************/
    .wrapper .user {
        width: 100%;
        height: 30vw;
        margin-top: 12vw;
        margin-left: 5vw;

        display: flex;
        justify-content: center;
        align-items: center;
    }
    .wrapper .user img{
        width: 20vw;
        height: 20vw;
        border-radius: 50px;
    }
    .wrapper .user .user-info{
        width: 100%;
        display: flex;
        flex-direction: column;
		justify-content: center;
        
		padding-left: 10vw;
    }
    .wrapper .user .user-info .user-name{
        width:100%;
        height:9vw;
        display: flex;
        justify-content:left;
    }
    .wrapper .user .user-info .user-name .user-name-left{
        font-size: 5vw;
    }
    .wrapper .user .user-info .user-name p{
        font-size: 5vw;
        padding-left: 3vw;
    }
    .wrapper .user .user-info .user-phone{
        width:100%;
        height:6vw;
        font-size: 4vw;
    }

    /************************ 功能部分 ************************/
    .wrapper .function {
		width: 90%;
		height: 18vw;
        margin-left: 5vw;

		display: flex;
		justify-content: space-around;
		align-items: center;

        border-top: solid 1.5px #DDD;
        border-bottom: solid 1.5px #DDD;
	}

	.wrapper .function li {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		color: rgb(127, 127, 127);
		user-select: none;
		cursor: pointer;
	}

	.wrapper .function li p {
		font-size: 3.5vw;
        margin-top: 0.7vw;
	}

	.wrapper .function li i {
		font-size: 7vw;
	}

</style>