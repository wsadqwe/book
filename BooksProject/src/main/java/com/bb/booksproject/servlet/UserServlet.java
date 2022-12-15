package com.bb.booksproject.servlet;

import com.bb.booksproject.pojo.User;
import com.bb.booksproject.service.UserService;
import com.bb.booksproject.service.impl.UserServiceImpl;
import com.bb.booksproject.util.Constant;
import com.bb.booksproject.util.RequestParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 用户的servlet
 * 作用：接收请求，通过service处理请求，在相应请求
 */
@WebServlet(name = "userServlet",urlPatterns = "/userServlet")

public class UserServlet extends HttpServlet {
    //声明UserService对象
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 统一处理浏览器提交的http://localhost:8080/userServlet的请求
     * @param req 封装请求相关信息的对象
     * @param resp 封装响应相关信息的对象
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*//设置POST请求中数据的解码方式
        req.setCharacterEncoding("UTF-8");*/

        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(type != null && !"".equals(type)){

            if(Constant.SERVLET_TYPE_SAVE.equals(type)){
                //添加用户信息
                try {
                    saveOrUpdateUser(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (Constant.SERVLET_TYPE_UPDATE.equals(type)) {
                //更新用户信息

            } else if (Constant.SERVLET_TYPE_DELETE.equals(type)) {
                //删除用户信息
                //获取需要删除的用户编号
                String id = req.getParameter("id");
                //通过service来处理删除操作
                Integer count = userService.deleteById(Integer.parseInt(id));
                //做一个重定向查询用户信息的操作
                resp.sendRedirect("/userServlet");

            } else if (Constant.SERVLET_TYPE_QUERY.equals(type)) {
                //查询用户
                queryUser(req, resp);
            } else if (Constant.SERVLET_TYPE_QUERYBYID.equals(type)) {
                //查询单条 记录
                String id = req.getParameter("id");
                User user = userService. queryById(Integer.parseInt(id));
                //跳转到更新的页面，同时保存数据到request作用域中
                req.setAttribute("user",user);
                req.getRequestDispatcher("/user/userUpdate.jsp").forward(req,resp);
            } else if (Constant.SERVLET_TYPE_CHECK.equals(type)) {
                //验证账号是否存在
                String username = req.getParameter("username");
                String s = userService.checkUserName(username);
                resp.getWriter().println(s);
                resp.flushBuffer();
            }
        }else{
            //查询用户信息
            queryUser(req, resp);
        }


    }

    /**
     * 添加用户的方法
     * @param req
     * @param resp
     * @throws IOException
     */
    private void saveOrUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取用户表单提交的信息
//        User user = new User();
//        user.setUsername(req.getParameter("username"));
//        user.setPassword(req.getParameter("password"));
//        user.setPhonenum(req.getParameter("phonenum"));
//        user.setEmail(req.getParameter("email"));
        User user = RequestParameterUtils.getRequestParameterForReflect(req, User.class);
        Integer count = -1;
        if(user.getId() != null && user.getId() > 0){
            //表示是更新
            count = userService.updateUser(user);
        }else{
            //count是影响的行数
            count = userService.addUser(user);
        }
        if(count>0){
            //表示插入成功，再做依次查询操作
            resp.sendRedirect("/userServlet");
        }else{
            //表示插入失败
            System.out.println("插入失败...");
            //To Do 跳转到失败页面
        }
    }


    /**
     * 查询用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void queryUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过service查询所有的用户信息
        List<User> list = userService.getUser(null);
        //把查询的信息绑定在request作用域中
        req.setAttribute("list",list);
        //页面跳转到user.jsp中
        req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
    }
}
