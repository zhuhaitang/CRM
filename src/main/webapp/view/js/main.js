var tabsMenu;
var centerTabs;
$(function(){
	$("#bmgl").bind("click",function(){
		addTab("部门管理",sy.pn()+"/deptController/initDept");
	});
	$("#mkgl").bind("click",function(){
		addTab("模块管理",sy.pn()+"/moduleController/initModule");
	});
	$("#rygl").bind("click",function(){
		addTab("人员管理",sy.pn()+"/userController/initUser");
	});
	$("#czgl").bind("click",function(){
		addTab("操作管理",sy.pn()+"/operateController/initOperate");
	});
	$("#jsgl").bind("click",function(){
		addTab("角色管理",sy.pn()+"/roleController/initRole");
	});
	$("#zdsj").bind("click",function(){
		addTab("字典数据",sy.pn()+"/dictController/initDict");
	});
	tabsMenu = $('#tabsMenu').menu({
		onClick : function(item) {
			var curTabTitle = $(this).data('tabTitle');
			var type = $(item.target).attr('type');

			if (type === 'refresh') {
				refreshTab(curTabTitle);
				return;
			}

			if (type === 'close') {
				var t = centerTabs.tabs('getTab', curTabTitle);
				if (t.panel('options').closable) {
					centerTabs.tabs('close', curTabTitle);
				}
				return;
			}

			var allTabs = centerTabs.tabs('tabs');
			var closeTabsTitle = [];

			$.each(allTabs, function() {
				var opt = $(this).panel('options');
				if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === 'closeAll') {
					closeTabsTitle.push(opt.title);
				}
			});

			for ( var i = 0; i < closeTabsTitle.length; i++) {
				centerTabs.tabs('close', closeTabsTitle[i]);
			}
		}
	});
	
	centerTabs = $('#tabs').tabs({
		fit : true,
		border : false,
		onContextMenu : function(e, title) {
			e.preventDefault();
			tabsMenu.menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data('tabTitle', title);
		}
	});
});

function refreshTab(title) {
	var tab = centerTabs.tabs('getTab', title);
	centerTabs.tabs('update', {
		tab : tab,
		options : tab.panel('options')
	});
}
function addTab(title,url){
	if(!$('#tabs').tabs('exists',title)){
		$('#tabs').tabs('add',{
			title:title,
			closable:true,
			content:'<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
			fit:true
		});
	}else{
		$('#tabs').tabs('select',title);
	}
}

function logout(){
	window.location.href = sy.pn()+'/loginController/logout';
}