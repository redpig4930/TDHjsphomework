package servlet;

import Jdbc.CrudTuser;
import bean.TUser;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        String id=request.getParameter("id");
        List userList =null;
        TUser user=new TUser();
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
