package servlet;

import Jdbc.CrudTuser;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet {

    /**
     * 调用doGET，转到doPost
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常信息
     * @throws IOException 异常信息
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * 处理登陆请求
     * @param request 请求
     * @param response 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        String userId = request.getParameter("userid");
        String pwd = request.getParameter("pwd");
        HttpSession session = request.getSession();
        List userList =null;
        try {
            userList=CrudTuser.readIdPwd(userId,pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json= JSONArray.fromObject(userList).toString();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
        session.setAttribute("user","yes");
    }
}
