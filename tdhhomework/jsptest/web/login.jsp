<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/test.css" />
    <link rel="stylesheet" href="css/reset.css" />
    <script type="text/javascript" src="js/jquery.min.js" ></script>
    <script type="text/javascript" src="js/jquery.cookie-1.4.1.min.js" ></script>
    <script type="text/javascript" src="js/test3.js"></script>
    <title>登陆</title>
</head>
<body>
<form id="userinfo">
    <table class="login">
        <tr>
            <td colspan="3" class="login_title" height="30">用户登录</td>
        </tr>
        <tr>
            <td class="login_head">用户id：</td>
            <td><input class="test_input" type="text" name="userid"/></td>
            <td><input type="checkbox" value="1" name="saveid" />保存账号</td>
        </tr>
        <tr>
            <td class="login_head">用户口令：</td>
            <td><input class="test_input" type="password" name="pwd"/></td>
            <td><input type="checkbox" value="1" name="savepwd" />保存密码</td>
        </tr>
        <tr>
            <td colspan="3" align="center" height="50"><input type="button" value="登录" id="login" class="btn"/></td>
        </tr>
    </table>
</form>
</body>
</html>

