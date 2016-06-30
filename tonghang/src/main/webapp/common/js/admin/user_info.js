function hisCircle(id) {
	var url = "get_user_circle?id=" + id;
	$
			.get(
					url,
					function(data) {
						var str = "<thead>"
								+ "<tr>"
								+ "<th><label><input type=\"text\" name=\"search_rate\" value=\"图片\" class=\"search_init\" /></label></th>"
								+ "<th><label><input type=\"text\" name=\"search_rate\" value=\"说明\" class=\"search_init\" /></label></th>"
								+ "<th><label><input type=\"text\" name=\"search_rate\" value=\"操作\" class=\"search_init\" /></label></th>"
								+ "</tr>" + "</thead>" + "<tbody>";
						for (var i = 0; i < data.length; i++) {
							console.log(data[i]);
							str += "<tr><td><img src='"
									+ data[i].pic
									+ "' /></td><td>"
									+ data[i].content
									+ "</td><td>"
									+ new Date(data[i].datetime)
											.toLocaleString() + "<br/>";
							if (data[i].checked == 1) {
								str += "审核通过";
							} else {
								str += "<button type=\"button\" class=\"btn btn-default\" onclick=\"checkCircle("
										+ data[i].id + ")\">审核</button>";
							}
							str += "</td></tr>";
						}
						str += "</tbody>" + "<tfoot>" + "<tr>" + "<th>图片</th>"
								+ "<th>说明</th>" + "<th>操作</th>" + "</tr>"
								+ "</tfoot>";
						$("#user_behavior_table").html(str);
					});
}

function hisComment(id) {
	var url = "get_user_comment?id=" + id;
	$
			.get(
					url,
					function(data) {
						console.log(data);
						var str = " <thead>"
								+ "<tr>"
								+ "<th><label><input type=\"text\" name=\"search_rate\" value=\"内容\" class=\"search_init\" /></label></th>"
								+ "<th><label><input type=\"text\" name=\"search_rate\" value=\"操作\" class=\"search_init\" /></label></th>"
								+ "</tr>" + "</thead>" + "<tbody>";
						for (var i = 0; i < data.length; i++) {
							console.log(data[i]);
							str += "<tr><td>"
									+ data[i].content
									+ "</td><td>"
									+ new Date(data[i].datetime)
											.toLocaleString() + "<br/>";
							if (data[i].checked == 1) {
								str += "审核通过";
							} else {
								str += "<button type=\"button\" class=\"btn btn-default\" onclick=\"checkCircle("
										+ data[i].id + ")\">审核</button>";
							}
							str += "</td></tr>";
						}
						str += "   </tbody>" + "<tfoot>" + "<tr>"
								+ "<th>内容</th>" + "<th>操作</th>" + "</tr>"
								+ "</tfoot>";
						$("#user_behavior_table").html(str);
					});

}

function checkService(id) {
	var form = '<div>' + '	<select  id="checked">' + '		<option>审核状态</option>'
			+ '		<option value="1">通过</option>'
			+ '		<option value="2">不通过</option>' + '	</select>' + '</div>'
			+ '<br/>' + '<div>' + '	<select>' + '		<option>不通过原因</option>'
			+ '		<option value="1">敏感信息</option>'
			+ '		<option value="2">色情信息</option>'
			+ '		<option value="3">恶意言论</option>'
			+ '		<option value="4">不适合发布的信息</option>' + '	</select>' + '</div>';
	var buttons = '<div><button type="button" class="btn btn-default" onclick="submitCheckService('
			+ id + ')">确定</button></div>';
	OpenModalBox("审核", form, buttons);
}

function submitCheckService(id) {
	var url = "checkService";
	if ($("#checked").val() == 1) {// 说明是通过
		var data = {
			id : id
		};
		$.post(url, data, function(result) {
			console.log("审核通过,url:" + url + ",id:" + id);
			LoadAjaxContent('users_info');
			CloseModalBox();
		});
	} else {
		console.log("审核不通过");
		CloseModalBox();
	}
}