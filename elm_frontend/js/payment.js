window.onload = function(){
	//获取显示隐藏按钮DOM对象
	let showBtn = document.getElementById('showBtn');
	//获取订单明细DOM对象
	let detailedBox = document.getElementById('detailedBox');
	
	detailedBox.style.display = 'none';
	
	showBtn.onclick = function(){
		if(detailedBox.style.display == 'none'){
			detailedBox.style.display = 'block';
		}
		else{
			detailedBox.style.display = 'none';
		}
	}
}