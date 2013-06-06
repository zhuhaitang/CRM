var datagrid;
var tree;
var parentId="";
$(function(){
	tree=$('#deptTree').tree({   
	    url:sy.pn()+'/deptController/findTree',
	    onClick: function(node){
	    	parentId=node.id;
	    	datagrid.datagrid('load', {likeCode:node.attributes.code});
		}
	});  

	datagrid = $('#tt').datagrid({
		url :sy.pn()+'/deptController/findDept',
		rownumbers:true,
		pagination:true, 
		fit:true,
		toolbar : [ {
			text : '增加',
			iconCls : 'icon-add',
			handler : function() {
				save();
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				deletes();
			}
		}, '-', {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				update();
			}
		}, '-' ],
		columns : [[ 
		    {field : 'id',title :'',width : 30,checkbox:true},
		    {field : 'code',title : '部门编号',width : 200,sortable : true},
		    {field : 'name',title :'部门名称',width : 400}
		]],
		onLoadSuccess: function () {  
            var grid = $(".datagrid-toolbar"); //datagrid   
            var dlg = $("#dlg-toolbar");  
            dlg.attr("style", "float:left;margin-top:3px;");   
            grid.append(dlg);   
        }  
		
	});
});
function save(){
	var p=parent.sy.dialog({
	    title: '新增部门信息',
	    href: sy.pn()+'/deptController/initForm',
	    width: 480,
	    height: 106,
	    closed: false,
	    cache: false,
	    modal: true,
	    toolbar: [{
			text: '保存',
			iconCls: 'icon-save',
			handler: function(data){
				form(p,sy.pn()+"/deptController/save");
			}
		},'-'],
		onLoad : function() {
			var form=p.find('form');
			form.form('load', {
				parentId : parentId
			});
		}
	});
}

function update(){
	var node = datagrid.datagrid('getSelections');
	if(node.length==1){
		var p=parent.sy.dialog({
		    title: '修改部门信息',
		    href: sy.pn()+'/deptController/initForm',
		    width: 480,
		    height: 106,
		    closed: false,
		    cache: false,
		    modal: true,
		    toolbar: [{
				text: '保存',
				iconCls: 'icon-save',
				handler: function(data){
					form(p,sy.pn()+"/deptController/update");
				}
			},'-'],
			onLoad : function() {
				var form=p.find('form');
				form.form('load', node[0]);
			}
		});
	}else if(node.length > 1){
		parent.sy.showMessage('请选择一条数据！');
		return ;
	}else{
		parent.sy.showMessage('请先选择需要修改的信息！');
		return ;
	}
}

function form(p,url){
	var form=p.find('form');
	form.form('submit', {
		url:url,
		onSubmit: function(){
			return form.form('validate');
		},
		success:function(data){
			var message=$.parseJSON(data);
			datagrid.datagrid('reload');
			tree.tree('reload');
			p.dialog('close');
			if(message.success){
				parent.sy.showMessage("操作成功");
			}else{
				parent.sy.showMessage("操作失败");
			}
			
		}
	});
}

function deletes(){
	var node = datagrid.datagrid('getSelections');
	var fields = '';
	for (var i = 0; i < node.length; i++) {
		if (node[i].type == '1') {
			fields = fields + node[i].name + '&nbsp;';
		}
	}
	if (fields != '') {
		parent.sy.alert('提示', '<b>您选中的项目中包含如下系统内置的只读项目</b><br>' + fields
						+ '<font color=red>只读项目不能删除!</font>');
		return;
	}
	if(node.length>0){
		var chkValue = [];
		for(var i=0; i<node.length; i++){
			chkValue.push(node[i].code);
		}
		alert(chkValue.join(","));
		parent.sy.messagerConfirm(
			'请确认', 
			'<span style="color:red"><b>提示:</b></span><br><span style="color:red">将同时删除下属人员和角色以及权限信息.</span><br>继续删除吗?', 
			function(b) {
    			if (b) {
	    			$.ajax({
	    				type: "POST",
	    				url: sy.pn()+'/deptController/delete',
	    				data:'codes='+chkValue.join(","),
	    				dataType: "json",
	    				success: function(data){
	    					datagrid.datagrid('reload');
	    					tree.tree('reload');
	    					if(data.success){
	    						parent.sy.showMessage("操作成功");
	    					}else{
	    						parent.sy.showMessage("操作失败");
	    					}
	    				}
	    			});
				}
			});
	}else{
		parent.sy.showMessage('请先选择需要删除的信息！');
		return ;
	}
}