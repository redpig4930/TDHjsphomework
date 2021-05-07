package servlet;

import Jdbc.CrudTuser;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CreateUser extends HttpServlet {
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
     * 处理新增记录的请求
     * @param request 请求
     * @param response 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException{
        //sdf日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String userId = request.getParameter("userid");
        String userName = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String gender = request.getParameter("gender");
        String depart = request.getParameter("depart");
        String birth = request.getParameter("birth");
        String pxh = request.getParameter("pxh");
        String ban = request.getParameter("ban");
        String userCode = "320100"+userId;
        int i =0;
        try {
            i=CrudTuser.create(userCode,"320100",userId,userName,pwd,gender,depart,birth,sdf.format(Calendar.getInstance().getTime()),ban,pxh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        if(i==0){
            writer.write("新增失败，id可能重复或者过长(最长14个字符)");
        }else{
            writer.write("新增成功");
        }

    }
}
