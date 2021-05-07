<%--
 * @Author huzhenyu
 * @Description 用户信息编辑页面
 * @Project Name:jsptest
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/test.css" />
    <link rel="stylesheet" href="css/reset.css" />
    <script type="text/javascript" src="js/jquery.min.js" ></script>
    <script type="text/javascript" src="js/test1.js"></script>
    <script type="text/javascript" src="js/test2.js"></script>
    <link rel="stylesheet" type="text/css" href="calendar/jquery.datetimepicker.css"/>
    <script src="js/jquery.js"></script>
    <script src="calendar/jquery.datetimepicker.full.min.js"></script>
    <title>edit&view</title>
</head>
<body>
<div class="test_title">用户信息</div>
<form id="userinfo">
    <table class="test1" border="1" cellspacing="0">
        <tr>
            <td class="table_head"><label>*</label>用户姓名</td>
            <td><input class="test_input" type="text" name="username"/></td>
            <td class="table_head"><label>*</label>用户id</td>
            <td><input class="test_input" type="text" name="userid"/></td>
        </tr>
        <tr>
            <td class="table_head"><label>*</label>用户口令</td>
            <td><input class="test_input" type="password" name="pwd"/></td>
            <td class="table_head">重复口令</td>
            <td><input class="test_input" type="password" name="pwd2"/></td>
        </tr>
        <tr>
            <td class="table_head">用户性别</td>
            <td>
                男<input type="radio" value="09_00003-1" name="gender" />
                女<input type="radio" value="09_00003-2" name="gender" />
            </td>
            <td class="table_head">出生日期</td>
            <td>
                <input class="test_input" id="datetimepicker" type="text" readonly >
            </td>
        </tr>
        <tr>
            <td class="table_head"><label>*</label>部门</td>
            <td>
                <select class="test_input" id="depart">
                    <option style='display: none' value="">请选择部门</option>
                    <%--部门--%>
                </select>
            </td>
            <td class="table_head">排序号</td>
            <td><input class="test_input" type="text" name="number"/></td>
        </tr>
        <tr>
            <td class="table_head">禁用</td>
            <td colspan="3">
                <input type="checkbox" value="1" name="isBan" style="margin-left: 10px">
            </td>

        </tr>
    </table>
    <div class="test_bottom">
        <input type="button" value="保存" id="save" class="btn"/>
        <input type="button" value="关闭" id="close" class="btn"/>
    </div>

</form>
</body>
</html>

