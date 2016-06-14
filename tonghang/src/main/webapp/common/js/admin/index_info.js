function submitCheck(id){
	console.log($("#checked option:selected").val()+id);
	if($("#checked").val()==1){//说明是通过
		var url = "checkCircle";
		var data = {id:id};
		$.post(url,data,function(result){
			console.log(result);
			LoadAjaxContent('index_info');
		});
	} else {
		
	}
	
}

function user_info(id){
		var url = "getUserById";
		var data = {id:id};
		$.get(url,data,function(data){
			var form = '<div class="wid-3"><img src="'+data.pic+'" alt="头像" /></div>'
			+'<div class="wid-4">'
			+'<div>姓名：'+data.name+'</div>'
			+'<div>手机号码：'+data.phone+'</div>'
			+'<div>性别：'+data.sex+'</div>'
			+'<div>行业：'+data.trade+'</div>'
			+'<div>所在城市：'+data.city+'</div>'
			+'<div>个人标签：'+data.remark+'</div>'
			+'<br/>'
			+'<div>所在公司：'+data.company+'</div>'
			+'<div>职位：'+data.position+'</div>'
			+'<div>毕业院校：'+data.college+'</div>'
			+'</div>'
			+'<div class="wid-3">'
			+'<div>'
			+'	<select>'
			+'		<option>设置用户分组</option>'
			+'		<option value="2">重要客户</option>'
			+'		<option value="1" selected>普通客户（默认分组）</option>'
			+'	</select>'
			+'</div>'
			+'<br/>'
			+'<div>'
			+'	<select>'
			+'		<option>设置用户状态</option>'
			+'		<option value="1" selected>激活</option>'
			+'		<option value="2">冻结</option>'
			+'	</select>'
			+'</div>'
			+'</div>';
			OpenModalBox("病人id:"+id,form);
		});
	}
	function checkCircle(id){
		var form = '<div>'
		+'	<select  id="checked">'
		+'		<option>审核状态</option>'
		+'		<option value="1">通过</option>'
		+'		<option value="2">不通过</option>'
		+'	</select>'
		+'</div>'
		+'<br/>'
		+'<div>'
		+'	<select>'
		+'		<option>不通过原因</option>'
		+'		<option value="1">敏感信息</option>'
		+'		<option value="2">色情信息</option>'
		+'		<option value="3">恶意言论</option>'
		+'		<option value="4">不适合发布的信息</option>'
		+'	</select>'
		+'</div>';
		var buttons = '<div><button type="button" class="btn btn-default" onclick="submitCheck('+id+')">确定</button></div>';
		OpenModalBox("审核",form,buttons);
	}
	var selected;
	function select(data) {
		selected = data;
		switch (selected) {
		case 1:
			LoadAjaxContent('index_info');
			break;
		case 2:
			LoadAjaxContent('get_service_unchecked');
			break;
		default:
			LoadAjaxContent('get_comment_unchecked');
		}
	}
	function uncheck() {
		console.log(selected);
		switch (selected) {
		case 1:
			LoadAjaxContent('index_info');
			break;
		case 2:
			LoadAjaxContent('get_service_unchecked');
			break;
		default:
			LoadAjaxContent('get_comment_unchecked');
		}
	}
	function check() {
		switch (selected) {
		case 1:
			LoadAjaxContent('get_circle_checked');
			break;
		case 2:
			LoadAjaxContent('get_service_checked');
			break;
		default:
			javacript: LoadAjaxContent('get_comment_checked');
		}
	}