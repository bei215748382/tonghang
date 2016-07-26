
function submitCheck(id){
	var url = "checkCircle";
	switch (selected) {
	case 1:
		url = "checkCircle";
		break;
	case 2:
		url = "checkService";
		break;
	default:
		url = "checkComment";
	}
	if($("#checked").val()==1){//说明是通过
		var data = {id:id,checked:1};
		$.post(url,data,function(result){
			console.log("审核通过,url:"+url+",id:"+id);
			LoadAjaxContent('index_info');
			CloseModalBox();
		});
	} else {
		var data = {id:id,checked:2};
		$.post(url,data,function(result){
			console.log("审核通过,url:"+url+",id:"+id);
			LoadAjaxContent('index_info');
			CloseModalBox();
		});
	}
	
}

function user_info(id){
		var url = "getUserById";
		var data = {id:id};
		$.get(url,data,function(data){
			var form = '<div class="wid-3"><img src="'+data.pic+'" alt="头像" style="max-width:100%" /></div>'
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
			+'	<select id="group">'
			+'		<option>设置用户分组</option>'
			+'		<option value="2">重要客户</option>'
			+'		<option value="1" selected>普通客户（默认分组）</option>'
			+'	</select>'
			+'</div>'
			+'<br/>'
			+'<div>'
			+'	<select id="state">'
			+'		<option>设置用户状态</option>'
			+'		<option value="1" selected>激活</option>'
			+'		<option value="2">冻结</option>'
			+'	</select>'
			+'</div>'
			+'</div>';
			OpenModalBox("用户id:"+id,form);
			
			$("#group").change(function(){
				  $.post("user_update",{id:id,groupId:$(this).val()},function(data){
					  console.log("更新群组操作成功");
				  });
				});
			$("#state").change(function(){
			    $.post("user_update",{id:id,state:$(this).val()},function(data){
					  console.log("更新状态操作成功");
				  });
			});
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

	function select(data) {
		selected = data;
		console.log("目前选的是："+selected);
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
		console.log("待审核选的是："+selected);
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
		console.log("已审核选的是："+selected);
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