package servlet;

import Jdbc.CrudTuser;
import Jdbc.DepartMap;
import bean.TUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DepartTreeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        if(DepartMap.idNameMap.isEmpty()){
            try {
                DepartMap.departMap("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String json= JSONObject.fromObject(DepartMap.idNameMap).toString();
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
    }
}
