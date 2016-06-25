function openModal() {
	var form = $('<form id="add_admin_user_form">'
			+ '<div class="form-group has-success has-feedback">'
			+ '<label">账户名</label>'
			+ '<div>'
			+ '<input type="text" id="username" class="form-control" placeholder="账户名" name=username>'
			+ '</div>'
			+ '<label>密码</label>'
			+ '<div>'
			+ '<input type="text" id="password" class="form-control" name=password>'
			+ '</div>' + '<label>选择账户类型</label>' + '<div>'
			+ '<select class="populate placeholder" name="role"' + 'id="type">'
			+ '<option value="0">-- 选择账户类型 --</option>'
			+ '<option value="超级管理员">-- 超级管理员 --</option>'
			+ '<option value="审核员">-- 审核员 --</option>' + '</select>' + '</div>'
			+ '</div>' + '</form>');
	var buttons = $('<button id="event_cancel" type="cancel" class="btn btn-default btn-label-left">'
			+ '<span><i class="fa fa-clock-o txt-danger"></i></span>'
			+ '取消'
			+ '</button>'
			+ '<button type="submit" id="event_submit" class="btn btn-primary btn-label-left pull-right">'
			+ '<span><i class="fa fa-clock-o"></i></span>' + '添加' + '</button>');
	OpenModalBox('添加账号', form, buttons);
	$('#event_cancel').on('click', function() {
		CloseModalBox();
	});
	$('#event_submit').on('click', function() {
		var data = $("#add_admin_user_form").serialize();
		console.log("1:" + data);
		$.ajax({
			type : "post",
			url : "add_admin_user?" + data,
			data : null,
			dataType : "json",
			success : function(data) {
				console.log("2:" + data);
			}
		});
		console.log("3:" + data);
		CloseModalBox();
	});
	$('select').select2();
}
function edit(id) {
	$
			.get(
					"get_admin_user?id=" + id,
					function(data) {
						console.log(data);
						var form = $('<form id="edit_admin_user_form">'
								+ '<input type="hidden" name="id" value="'+data.id+'">'
								+ '<div class="form-group has-success has-feedback">'
								+ '<label">账户名</label>'
								+ '<div>'
								+ '<input type="text" id="username" class="form-control" placeholder="账户名" name="username" value="'+data.username+'">'
								+ '</div>'
								+ '<label>密码</label>'
								+ '<div>'
								+ '<input type="text" id="password" class="form-control" name="password" value="'+data.password+'">'
								+ '</div>'
								+ '<label>选择账户类型</label>'
								+ '<div>'
								+ '<select class="populate placeholder" name="role"'
								+ 'id="type">'
								+ '<option value="0">-- 选择账户类型 --</option>'
								+ '<option value="超级管理员">-- 超级管理员 --</option>'
								+ '<option value="审核员">-- 审核员 --</option>'
								+ '</select>' + '</div>' + '</div>' + '</form>');
						var buttons = $('<button id="event_cancel" type="cancel" class="btn btn-default btn-label-left">'
								+ '<span><i class="fa fa-clock-o txt-danger"></i></span>'
								+ '取消'
								+ '</button>'
								+ '<button type="submit" id="event_submit" class="btn btn-primary btn-label-left pull-right">'
								+ '<span><i class="fa fa-clock-o"></i></span>'
								+ '添加' + '</button>');
						OpenModalBox('编辑账号', form, buttons);
						$('#event_cancel').on('click', function() {
							CloseModalBox();
						});
						$('#event_submit').on('click', function() {
							var data = $("#edit_admin_user_form").serialize();
							$.ajax({
								type : "post",
								url : "edit_admin_user?" + data,
								data : null,
								dataType : "json",
								success : function(data) {
									console.log("success:：" + data);
								}
							});
							CloseModalBox();
						});
						$('select').select2();
					}

			);
}