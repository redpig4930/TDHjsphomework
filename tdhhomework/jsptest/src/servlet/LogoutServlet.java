package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author huzhenyu
 * @Description 注销servlet
 * @Project Name:jsptest
 * @File_Name: LogoutServlet
 * @Package_Name:  servlet
 */
public class LogoutServlet extends HttpServlet {
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
     * 处理注销请求
     * @param request 请求
     * @param response 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        HttpSession session = request.getSession();
        //删除session中的"user"属性
        session.removeAttribute("user");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("已注销");
    }
}
