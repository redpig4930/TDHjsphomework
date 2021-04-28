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

public class DeleteServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        String userId = request.getParameter("userid");
        try {
            CrudTuser.delete(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("删除成功");
    }
}
