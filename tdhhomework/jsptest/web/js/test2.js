jQuery(function (){
    jQuery.datetimepicker.setLocale('ch');
    jQuery('#datetimepicker').datetimepicker({
        timepicker: false,
        format:'Y-m-d'
    });
    var afterUrl =window.location.search.substring(1);
    if(!(afterUrl==='')){
        var method = afterUrl.substring(afterUrl.indexOf('method=')+7,afterUrl.indexOf('&'));
        var userId = decodeURI(afterUrl.substring(afterUrl.indexOf('id=')+3));
        fillTable(userId);
        display(method);
    }else {
        $.ajax({
            type:'post',
            url:'DepartTreeServlet',
            dataType: 'json',
            success:function (data){
                for(var i in data){
                    $("#depart").append("<option value="+i+">"+data[i]+"</option>");
                }
            }
        });
    }


});

function display(method){
    if(method==='view'){
        $("#save").hide();
        $("input").attr('disabled',true);
        $('select').attr('disabled', true);
        $(':button').attr('disabled', false);
    }else if(method==='delete'){
        $("#save").val('确认删除');
        $("input").attr('disabled',true);
        $('select').attr('disabled', true);
        $(':button').attr('disabled', false);
        $("#save").unbind('click').bind('click',function (){
           $.ajax({
               type:'post',
               url:'DeleteServlet',
               data:{'userid':$('[name="userid"]').val()},
               dataType: 'text',
               success:function (data){
                    alert(data);
                    window.close();
               }
           });
        });
    }else if(method==='modify'){
        $('[name="userid"]').attr('readonly',true);
        $('[name="userid"]').css('background-color','#f7f7f7');

        $("#save").unbind('click').bind('click',function (){
            var username=jQuery('[name="username"]').val();
            var userid=jQuery('[name="userid"]').val();
            var pwd=jQuery('[name="pwd"]').val();
            var pwd2=jQuery('[name="pwd2"]').val();
            var gender=jQuery('[name="gender"]:checked').val();
            var birth=jQuery('#datetimepicker').val().replace(/-/g,'');
            var pxh=jQuery('[name="number"]').val();
            var depart=jQuery("#depart option:selected").val();
            if(jQuery('[name="isBan"]:checked')){
                var ban=jQuery('[name="isBan"]:checked').val();
            }else{
                var ban='0';
            }
            console.log(ban);
            var msg=[];
            if(check(username)){
                msg.push('用户姓名');
            }
            if(check(pwd)){
                msg.push('用户口令');
            }
            if(check(depart)){
                msg.push('部门');
            }
            if(msg.length){
                alert('请检查必填项：'+msg.join('，'));
            } else if(!(pwd==pwd2)){
                alert('两次口令不一致');
            }else{
                $.ajax({
                    type:'post',
                    url:'UpdateServlet',
                    data:{
                        'userid':userid,'username':username,'pwd':pwd,'gender':gender,'depart':depart,'birth':birth,'pxh':pxh,'ban':ban
                    },
                    dataType: 'text',
                    success:function (data){
                        alert(data);
                        window.close();
                    }
                });
            }
        });
    }
}

function fillTable(userId){
    $.ajax({
        type:'post',
        url:'SearchServlet',
        data:{'id':userId},
        dataType:'json',
        success:function (data){
            var user = data[0];
            $('[name="username"]').val(user.YHXM);
            $('[name="userid"]').val(user.YHID);
            $('[name="pwd"]').val(user.YHKL);
            $('[name="pwd2"]').val(user.YHKL);
            if(user.YHXB==='男'){
                $('[value="09_00003-1"]').prop('checked',true);
            }else if(user.YHXB==='女'){
                $('[value="09_00003-2"]').prop('checked',true);
            }
            $.ajax({
                type:'post',
                url:'DepartTreeServlet',
                dataType: 'json',
                success:function (data){
                    for(var i in data){
                        if(data[i]===user.YHBM){
                            $("#depart").append("<option value="+i+" selected>"+data[i]+"</option>");
                        }else {
                            $("#depart").append("<option value="+i+">"+data[i]+"</option>");
                        }
                    }
                }
            });
            $('[name="number"]').val(user.PXH);
            if(user.CSRQ===''||user.CSRQ===null){
                $('#datetimepicker').val('');
            }else{
                //birth=YYYY-mm-dd,和datapicker统一
                var birth=user.CSRQ.substring(0,4)+'-'+user.CSRQ.substring(4,6)+'-'+user.CSRQ.substring(6);
                $('#datetimepicker').val(birth);
            }
            if(user.SFJY==='1'){
                $('[name="isBan"]').prop('checked',true);
            }
        }
    });
}