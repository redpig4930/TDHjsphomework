jQuery(function(){
//获取部门树
	jQuery.ajax({
		type:'post',
		url:'DepartTreeServlet',
		dataType: 'json',
		success:function (data){
			for(var i in data){
				jQuery("#departTree").append("<p class='pointer' code='"+i+"'>"+data[i]+"</p>");
			}
		},
		error:function (){
			alert("发生错误")
		}
	});
//鼠标放置或点击在部门树上，相应部门变色
	jQuery("#departTree").on('mouseenter','p',function (){
			$(this).css("color", "#02a9f0");
	});

	jQuery("#departTree").on('mouseleave','p',function (){
		if(!$(this).hasClass("selected")){
			$(this).css("color","#466c1b");
		}
	});

	jQuery("#departTree").on('click','p',function (){
		$("p").css("color","#466c1b");
		$("p").removeClass("selected");
		$(this).addClass("selected");
		$(this).css("color","#02a9f0");
		$('[name="username"]').val("");
		$.ajax({
			type:'post',
			url:'GetUserByDepart',
			data:{'depart':$(".selected").attr("code")},
			dataType: 'json',
			success:function (data){
				jQuery("#data").empty();
				addResult(data,$(".selected").text());
			},
			error:function (){
				alert("发生错误")
			}
		});
	});
//搜索按键
	jQuery("#search").bind('click',function(){
		var username=trim(jQuery('[name="username"]').val());
		$("p").css("color","#466c1b");
		$("p").removeClass("selected");
		jQuery.ajax({
			type:'post',
			url:'SearchServlet',
			data:{'id':username},
			dataType:'json',
			success:function (data){
				jQuery("#data").empty();
				if(!data.length){
					alert("没找到用户姓名或账号为\""+username+"\"的记录")
				}else {
					alert("找到"+data.length+"条记录")
					addResult(data,username);
				}
			},
			error:function (){
				alert("发生错误")
			}
		});
	});
	//新增按键
	jQuery("#add").bind('click',function(){
		window.open("./edit.jsp","新增用户","height=250,width=800,left=400,top=150");
	});
	//编辑页面的保存按键
	jQuery("#save").bind('click',function(){
		var username=jQuery('[name="username"]').val();
		var userid=jQuery('[name="userid"]').val();
		var pwd=jQuery('[name="pwd"]').val();
		var pwd2=jQuery('[name="pwd2"]').val();
		var gender=jQuery('[name="gender"]:checked').val();
		var birth=jQuery('#datetimepicker').val().replace(/-/g,'');
		var pxh=jQuery('[name="number"]').val();
		var depart=jQuery("#depart option:selected").val();
		var ban=jQuery('[name="isBan"]:checked').val();
		var msg=[];
		var rep = /^(\w*)admin(\w*)$/i
		if(check(username)){
			msg.push('用户姓名');
		}
		if(check(userid)){
			msg.push('用户id');
		}
		if(check(pwd)){
			msg.push('用户口令');
		}
		if(check(depart)){
			msg.push('部门');
		}
		if(msg.length){
			alert('请检查必填项：'+msg.join('，'));
		}
		else if(!(pwd==pwd2)){
			alert('两次口令不一致');
		}else if(rep.test(userid)){
			alert("用户id不能包含admin");
		} else{
			$.ajax({
				type:'post',
				url:'CreateUser',
				data:{'userid':userid,'username':username,'pwd':pwd,'gender':gender,'depart':depart,'birth':birth,'pxh':pxh,'ban':ban},
				dataType:'text',
				success:function (data){
					alert(data);
					if (window.opener && !window.opener.closed) {
						window.opener.reloadResult();
					}
					window.close();
				},
				error:function (){
					alert("发生错误")
				}
			});
		}
	});
	//编辑页面的关闭按键
	jQuery("#close").bind('click',function(){
		// if (window.opener && !window.opener.closed) {
		// 	window.opener.location.reload();
		// }
		window.close();

	});

	//注销按键
	$("#logout").bind('click',function (){
		$.ajax({
			type:'post',
			url:'LogoutServlet',
			dataType:'text',
			success:function (data){
				alert(data);
				window.location.replace("login.jsp");
			},
			error:function (){
				alert("发生错误")
			}
		});
	});
});

//去除空格
function trim(value) {
    if (value){
		value = value.replace(/^\s*|\s*$/g, "");
	}
    if (!value){
		return "";
	}
    else{
		return value;
	}   
}
//检测输入是否为空
function check(value){
	if(!value||trim(value)==""){
		return true;
	} else{
		return false;
	}
}
//填充搜索结果区
function addResult(data,info){
	var user;
	if(info===''){
		info='[全部]'
	}
	$("#tips").text('查询“'+info+'”的结果如下:')
	for(var i=0;i<data.length;i++){
		user=data[i];
		var gender = (user.YHXB===null?'无':user.YHXB);
		jQuery("#data").append("<tr>\n" +
			"                    <td align=\"center\">"+(i+1)+"</td>\n" +
			"                    <td align=\"center\" class='pointer' id='"+user.YHID+"'>查看</td>\n" +
			"                    <td align=\"center\" class='pointer' id='"+user.YHID+"'>删除</td>\n" +
			"                    <td align=\"center\" class='pointer' id='"+user.YHID+"'>修改</td>\n" +
			"                    <td align=\"center\">"+user.YHXM+"</td>\n" +
			"                    <td align=\"center\">"+user.YHID+"</td>\n" +
			"                    <td align=\"center\">"+user.YHBM+"</td>\n" +
			"                    <td align=\"center\">"+gender+"</td>\n" +
			"                    <td></td>\n" +
			"                </tr>");
	}
	$("#data").children("tr").each(function (){
		$(this).children("td").eq(1).on('click',function (){
			window.open("./edit.jsp?method=view&id="+$(this).attr('id'),"","height=250,width=800,left=400,top=150");
		});
		$(this).children("td").eq(2).on('click',function (){
			window.open("./edit.jsp?method=delete&id="+$(this).attr('id'),"","height=250,width=800,left=400,top=150");
		});
		$(this).children("td").eq(3).on('click',function (){
			window.open("./edit.jsp?method=modify&id="+$(this).attr('id'),"","height=250,width=800,left=400,top=150");
		});
	});
}
//新增、修改或删除记录后刷新原窗口
function reloadResult(){
	var depart=$(".selected").text();
	if(depart!==''){
		$.ajax({
			type:'post',
			url:'GetUserByDepart',
			data:{'depart':$(".selected").attr("code")},
			dataType: 'json',
			success:function (data){
				jQuery("#data").empty();
				addResult(data,depart);
			},
			error:function (){
				alert("发生错误")
			}
		});
	}else {
		var username=$('[name="username"]').val();
		$.ajax({
			type:'post',
			url:'SearchServlet',
			data:{'id':username},
			dataType:'json',
			success:function (data){
				jQuery("#data").empty();
				if(!data.length){
					//alert("没找到用户姓名或账号为\""+username+"\"的记录")
				}else {
					//alert("找到"+data.length+"条记录")
					addResult(data,username);
				}
			},
			error:function (){
				alert("发生错误")
			}
		});
	}
}
