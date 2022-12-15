package com.bb.booksproject.servlet;

import com.bb.booksproject.pojo.Book;
import com.bb.booksproject.service.BookService;
import com.bb.booksproject.service.impl.BookServiceImpl;
import com.bb.booksproject.util.Constant;
import com.bb.booksproject.util.RequestParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookServlet",urlPatterns = "/bookServlet")
public class BookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*//设置POST请求中数据的解码方式
        req.setCharacterEncoding("UTF-8");*/

        //1.获取提交的type数据
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(Constant.SERVLET_TYPE_QUERY.equals(type)){
            queryBookList(req, resp);
        } else if (Constant.SERVLET_TYPE_SAVE.equals(type)) {
            saveOrUpdateBook(req, resp);
        } else if (Constant.SERVLET_TYPE_QUERYBYID.equals(type)) {
            //根据编号查询book信息
            String id = req.getParameter("id");
            Book book = bookService.queryById(Integer.parseInt(id));
            req.setAttribute("book",book);
            req.getRequestDispatcher("/book/bookUpdate.jsp").forward(req,resp);
        }else if (Constant.SERVLET_TYPE_UPDATE.equals(type)) {
            saveOrUpdateBook(req, resp);//添加和更新book信息（已提取方法）
        } else if (Constant.SERVLET_TYPE_DELETE.equals(type)) {
            deleteById(req, resp);//逻辑  删除book信息
        }else {
            queryBookList(req, resp);
        }

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //逻辑删除book信息
        String id = req.getParameter("id");
        bookService.deleteById(Integer.parseInt(id));
        resp.sendRedirect("/bookServlet?type=query");
    }

    /**
     * 添加和更新的实现方法
     * @param req
     * @param resp
     */
    private void saveOrUpdateBook(HttpServletRequest req, HttpServletResponse resp) {
        //表示完成book数据的添加
        try {
            //通过反射帮助我们从request中快速提取表单信息到book对象中，提升开发效率
            Book book = RequestParameterUtils.getRequestParameterForReflect(req, Book.class);
            int count = -1;
            if (book.getId() != null && book.getId() > 0){
                //表示是更新
                count = bookService.updateBook(book);
            }else {
                //表示是添加
                count =  bookService.saveBook(book);
            }
            if (count > 0){
                //表示添加成功
                resp.sendRedirect("/bookServlet?type=query");
            }else{
                //表示添加失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryBookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有的书籍信息
        List<Book> list = bookService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/book/book.jsp").forward(req, resp);
    }
}
