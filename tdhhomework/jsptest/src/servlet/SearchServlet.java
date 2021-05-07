package servlet;

import Jdbc.CrudTuser;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * @Author huzhenyu
 * @Description 查询用户servlet
 * @Project Name:jsptest
 * @File_Name: SearchServlet
 * @Package_Name:  servlet
 * @修改记录：
 *      2021.4.30   当id为空时，设置id为“_”，因为“_”为通配符，所以这样id为空时就会把所有记录查询出来。
 */
public class SearchServlet extends HttpServlet {
    /**
     * 调用doGET，转到doPost
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常信息
     * @throws IOException 异常信息
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request,response);
    }

    /**
     * 处理搜索记录请求
     * @param request 请求
     * @param response 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        String id=request.getParameter("id");
        if(id.isEmpty()){
            id="_";
        }
        List userList =null;
        try {
            userList= CrudTuser.readByIdName(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json= JSONArray.fromObject(userList).toString();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
    }
}
