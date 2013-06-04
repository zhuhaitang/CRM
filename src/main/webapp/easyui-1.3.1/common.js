
/** 全局对象 **/
var sy = $.extend({}, sy);

/** dialog扩张 **/
sy.dialog = function(options) {
	var opts = $.extend({
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	return $('<div/>').dialog(opts);
};

/** tab关闭时回收内存 **/
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		} else {
			$(this).find('.combo-f').each(function() {
				var panel = $(this).data().combo.panel;
				panel.panel('destroy');
			});
		}
	} catch (e) {
	}
};
/** 使panel和datagrid在加载时提示 **/
$.fn.panel.defaults.loadingMessage = '数据加载中，请稍候....';
$.fn.datagrid.defaults.loadMsg = '数据加载中，请稍候....';

/** 提示框 **/
sy.showMessage = function(text) {
	$.messager.show({msg :text,title : '提示'});
}
sy.messagerConfirm = function(title, msg, fn) {
	return $.messager.confirm(title, msg, fn);
};
/** 警告框 **/
sy.alert = function(title,text){
	$.messager.alert(title,text);  
};

/** 防止panel/window/dialog组件超出浏览器边界 **/
var easyuiPanelOnMove = function(left, top) {/* 防止超出浏览器边界 */
    if (left < 0) {
        $(this).window('move', {
            left : 1
        });
    }
    if (top < 0) {
        $(this).window('move', {
            top : 1
        });
    }
    var width = $(this).panel('options').width;
    var height = $(this).panel('options').height;
    var right = left + width;
    var buttom = top + height;
    var browserWidth = $(document).width();
    var browserHeight = $(document).height();
    
    if (right > browserWidth) {
        $(this).window('move', {
            left : browserWidth - width
        });
    }
    if (buttom > browserHeight) {
        $(this).window('move', {
            top : browserHeight - height
        });
    }
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;

/** 使用方法:sy.pn() **/
sy.pn = function() {
	return window.document.location.pathname.substring(0, window.document.location.pathname.indexOf('\/', 1));
};