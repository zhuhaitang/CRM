$(function(){
	$("#login_but").bind({
		click:function(){
			form();
		}
	});
	$("#reset_but").bind({
		click:function(){
			$('form')[0].reset();
		}
	});
});

function form(){
	var form=$('form');
	var url=sy.pn()+'/loginController/validate';
	form.form('submit', {
		url:url,
		onSubmit: function(){
			return form.form('validate');
		},
		success:function(data){
			var message=$.parseJSON(data);
			if(message.success){
				window.location.href = sy.pn()+'/loginController/main';
			}else{
				alert('用户名或密码错误');
			}
			
		}
	});
}
