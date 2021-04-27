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
           //相应数据标记为禁用
           alert('do something');
        });
    }else if(method==='modify'){
        $('[name="userid"]').attr('readonly',true);
        $('[name="userid"]').css('background-color','#f7f7f7');
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
            if(user.CSRQ===''){
                $('#datetimepicker').val('无');
            }else{
                //birth=YYYY-mm-dd,和datapicker统一
                var birth=user.CSRQ.substring(0,4)+'-'+user.CSRQ.substring(4,6)+'-'+user.CSRQ.substring(6);
                $('#datetimepicker').val(birth);
            }
        }
    });
}