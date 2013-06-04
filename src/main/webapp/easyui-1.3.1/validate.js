$.extend($.fn.validatebox.defaults.rules, {
	eqPassword : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});