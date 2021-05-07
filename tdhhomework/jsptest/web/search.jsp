<%--
 * @Author huzhenyu
 * @Description 用户搜索页面
 * @Project Name:jsptest
 * @修改记录：
 *      2021.4.30   实现了注销功能。
 *                  修改、新增或删除记录后，原页面会按之前的搜索条件刷新页面(test1.js的reloadResult方法)。
 *                  显示序号从1开始显示，记录还是按排序号排序。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/test.css" />
    <link rel="stylesheet" href="css/reset.css" />
    <script type="text/javascript" src="js/jquery.min.js" ></script>
    <script type="text/javascript" src="js/test1.js"></script>
    <title>search</title>
</head>
<body>
<table class="test1">
    <tbody>
    <colgroup>
        <col width="300"/>
        <col/>
    </colgroup>
    <tr>
        <td height="80" colspan="2" class="test_layout head">
            <form>
                <div class="test_head">
                    用户姓名/账号：
                    <input type="text" name="username"/>
                    <input id="search" type="button" value="查询" class="btn"/>
                    <input id="add" type="button" value="新增" class="btn"/>
                    <input id="logout" type="button" value="注销" class="btn"/>
                </div>

            </form>
        </td>
    </tr>
    <tr>
        <td class="test_layout test_side" id="departTree">
        <%--  部门树  --%>
        </td>
        <td valign="top" class="test_layout">
            <p id="tips"></p>
            <table border="1" class="user_table">
                <colgroup>
                    <col/>
                    <col width="50px"/>
                    <col width="50px"/>
                    <col width="50px"/>
                    <col/>
                    <col/>
                    <col/>
                    <col/>
                    <col width="40%"/>
                </colgroup>
                <thead>
                <tr>
                    <th class="table_head">序号</th>
                    <th class="table_head"></th>
                    <th class="table_head"></th>
                    <th class="table_head"></th>
                    <th class="table_head">用户姓名</th>
                    <th class="table_head">用户id</th>
                    <th class="table_head">用户部门</th>
                    <th class="table_head">性别</th>
                    <th class="table_head">备注</th>
                </tr>
                </thead>
                <tbody id="data">
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>

