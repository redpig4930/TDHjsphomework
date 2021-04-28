package servlet;

import Jdbc.CrudTuser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        String userId = request.getParameter("userid");
        String userName = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String gender = request.getParameter("gender");
        String depart = request.getParameter("depart");
        String birth = request.getParameter("birth");
        String pxh = request.getParameter("pxh");
        String ban = request.getParameter("ban");
        try {
            CrudTuser.update(userId,userName,pwd,gender,depart,birth,pxh,ban);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("更新成功");
    }
}
