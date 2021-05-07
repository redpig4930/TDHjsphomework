package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @Author huzhenyu
 * @Description 登陆过滤，过滤未登陆的直接访问
 * @Project Name:jsptest
 * @File_Name: LoginFilter
 * @Package_Name:  filter
 */
public class LoginFilter implements Filter {

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤请求，未登陆时，所有访问都会被重定向到登录界面
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session =request.getSession();

        String url = request.getRequestURI();
        int idx = url.lastIndexOf("/");
        //endWith=请求的页面
        String endWith = url.substring(idx + 1);
        //如果没登录则重定向到登录界面
        if(session.getAttribute("user")==null){
            if (!endWith.equals("login.jsp")) {
                response.sendRedirect("login.jsp");
            }else {
                chain.doFilter(req, resp);
            }
            //登录了，则不对请求进行过滤
        }else {
            chain.doFilter(req, resp);
        }
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {

    }


}