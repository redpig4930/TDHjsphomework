jQuery(function(){
    if($.cookie('id')){
        $('[name="userid"]').val($.cookie('id'));
        $('[name="saveid"]').attr('checked',true);
    }
    if($.cookie('pwd')){
        $('[name="pwd"]').val($.cookie('pwd'));
        $('[name="savepwd"]').attr('checked',true);
    }

    //登录按键
    $("#login").bind('click',function (){
        var userid=jQuery('[name="userid"]').val();
        var pwd=jQuery('[name="pwd"]').val();
        var saveid=$('[name="saveid"]:checked').val();
        var savepwd=$('[name="savepwd"]:checked').val();
        //请求登陆，登陆成功，则视勾选情况，将id和密码保存在cookie中
        jQuery.ajax({
            type:'post',
            url:'LoginServlet',
            data:{'userid':userid,'pwd':pwd},
            dataType:'json',
            success:function (data){
                if(!data.length){
                    alert("登陆失败")
                }else {
                    if(saveid!=null){
                        $.cookie('id',userid);
                    }else{
                        $.removeCookie('id');
                    }
                    if(savepwd!=null){
                        $.cookie('pwd',pwd);
                    }else{
                        $.removeCookie('pwd');
                    }
                    alert("登陆成功")
                    window.location.replace("search.jsp");
                }
            }
        });
    });


});