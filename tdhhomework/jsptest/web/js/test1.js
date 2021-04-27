jQuery(function(){

	jQuery.ajax({
		type:'post',
		url:'DepartTreeServlet',
		dataType: 'json',
		success:function (data){
			for(var i in data){
				jQuery("#departTree").append("<p class='pointer' code='"+i+"'>"+data[i]+"</p>");
			}
		}
	});

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
				addResult(data);
			}
		});
	});

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
					addResult(data);
				}
			}
		});
	});
	
	jQuery("#add").bind('click',function(){
		window.open("./edit.jsp","新增用户","height=360,width=800,left=400,top=150");
	});
	
	jQuery("#save").bind('click',function(){
		var username=jQuery('[name="username"]').val();
		var userid=jQuery('[name="userid"]').val();
		var pwd=jQuery('[name="pwd"]').val();
		var pwd2=jQuery('[name="pwd2"]').val();
		var gender=jQuery('[name="gender"]:checked').val();
		var birth=jQuery('#datetimepicker').val().replace(/-/g,'');
		var pxh=jQuery('[name="number"]:checked').val();
		var depart=jQuery("#depart option:selected").val();
		var ban=jQuery('[name="isBan"]:checked').val();
		var msg=[];
		var params='';
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
		}
		else{
			$.ajax({
				type:'post',
				url:'CreateUser',
				data:{'userid':userid,'username':username,'pwd':pwd,'gender':gender,'depart':depart,'birth':birth,'pxh':pxh,'ban':ban},
				dataType:'text',
				success:function (data){
					alert(data);
					window.close();
				}
			});
		}
	});
	
	jQuery("#close").bind('click',function(){
		window.close();
	});
});

function encodeStr(val){
	return encodeURIComponent(encodeURIComponent(val));;
}

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

function check(value){
	if(!value||trim(value)==""){
		return true;
	} else{
		return false;
	}
}

function addResult(data){
	var user;
	for(var i=0;i<data.length;i++){
		user=data[i];
		jQuery("#data").append("<tr>\n" +
			"                    <td align=\"center\">"+user.PXH+"</td>\n" +
			"                    <td align=\"center\" class='pointer' id='"+user.YHID+"'>查看</td>\n" +
			"                    <td align=\"center\" class='pointer' id='"+user.YHID+"'>删除</td>\n" +
			"                    <td align=\"center\" class='pointer' id='"+user.YHID+"'>修改</td>\n" +
			"                    <td align=\"center\">"+user.YHXM+"</td>\n" +
			"                    <td align=\"center\">"+user.YHID+"</td>\n" +
			"                    <td align=\"center\">"+user.YHBM+"</td>\n" +
			"                    <td align=\"center\">"+user.YHXB+"</td>\n" +
			"                    <td></td>\n" +
			"                </tr>");
	}
	$("#data").children("tr").each(function (){
		$(this).children("td").eq(1).on('click',function (){
			window.open("./edit.jsp?method=view&id="+$(this).attr('id'),"","height=360,width=800,left=400,top=150");
		});
		$(this).children("td").eq(2).on('click',function (){
			window.open("./edit.jsp?method=delete&id="+$(this).attr('id'),"","height=360,width=800,left=400,top=150");
		});
		$(this).children("td").eq(3).on('click',function (){
			window.open("./edit.jsp?method=modify&id="+$(this).attr('id'),"","height=360,width=800,left=400,top=150");
		});
	});
}
