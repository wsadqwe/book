package com.bb.booksproject.dao.impl;

import com.bb.booksproject.dao.BookDao;
import com.bb.booksproject.pojo.Book;
import com.bb.booksproject.util.DelFlagE;
import com.bb.booksproject.util.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BookDao的实现
 */
public class BookDaoImpl implements BookDao {

    String sql;
    QueryRunner queryRunner;

    /**
     * 查询书籍
     * @param book
     * @return
     */
    @Override
    public List<Book> list(Book book) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from book where isDeleted = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Book>>() {
                @Override
                public List<Book> handle(ResultSet rs) throws SQLException {
                    List<Book> list = new ArrayList<>();
                    while(rs.next()){
                        Book b = new Book();
                        b.setId(rs.getInt("id"));
                        b.setBookname(rs.getString("bookname"));
                        b.setAuthor(rs.getString("author"));
                        String bookintroduction = rs.getString("bookintroduction");
                        if (bookintroduction != null && !"".equals(bookintroduction) && bookintroduction.length() > 20){
                            bookintroduction = rs.getString("bookintroduction").substring(0,20);
                        }
                        b.setBookintroduction(bookintroduction+"...");
                        String bookcontent = rs.getString("bookcontent");
                        if (bookcontent != null && !"".equals(bookcontent) && bookcontent.length() > 20){
                            bookcontent = rs.getString("bookcontent").substring(0,20);
                        }
                        b.setBookcontent(bookcontent+"...");
                        b.setStatus(rs.getInt("status"));
                        b.setIsDeleted(rs.getInt("isDeleted"));
                        list.add(b);
                    }
                    return list;
                }
            }, DelFlagE.NO.code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 添加书籍信息
     * @param book
     * @return
     */
     @Override
    public Integer saveBook(Book book) {
        //获取QueryRunner的对象
         queryRunner = MyDBUtils.getQueryRunner();
         sql = "insert into book(bookname,author,bookintroduction,bookcontent)values (?,?,?,?)";
        try {
            return queryRunner.update(sql,book.getBookname()
                    ,book.getAuthor()
                    ,book.getBookintroduction()
                    ,book.getBookcontent());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    /**
     * 测试添加和查询的main方法
     * @param args
     */
    public static void main(String[] args) {
        BookDaoImpl dao = new BookDaoImpl();
        Book book = new Book();
        book.setId(13);
        book.setBookname("时间简史");
        book.setAuthor("斯蒂芬威廉霍金");
        book.setBookintroduction("一本神奇的书66666");
        book.setBookcontent("关于宇宙本性的最前沿知识");
        //dao.saveBook(book);
        dao.updateBook(book);
        List<Book> list = dao.list(book);
        System.out.println(list);
    }

    /**
     * 更新书籍书库信息
     * @param book
     * @return
     */
    @Override
    public Integer updateBook(Book book) {
        queryRunner = MyDBUtils.getQueryRunner();
        if(book.getIsDeleted() == null ||"".equals(book.getIsDeleted())){
            book.setIsDeleted(DelFlagE.NO.code);
        }
        if (book.getStatus() == null || "".equals(book.getStatus())){
            book.setStatus(0);
        }
        sql = "update book set bookname = ?,author = ?,bookintroduction = ?,bookcontent = ?,status = ?,isDeleted = ? where id = ?";
        try {
            return queryRunner.update(sql,book.getBookname(),
                    book.getAuthor(),
                    book.getBookintroduction(),
                    book.getBookcontent(),
                    book.getStatus(),
                    book.getIsDeleted(),
                    book.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    /**
     * 通过id删除书籍信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "update book set isDeleted = ? where id = ?";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Book queryById(Integer id) {
        queryRunner = MyDBUtils.getQueryRunner();
        sql = "select * from book where isDeleted = ? and id = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Book>() {
                @Override
                public Book handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        Book b = new Book();
                        b.setId(rs.getInt("id"));
                        b.setBookname(rs.getString("bookname"));
                        b.setAuthor(rs.getString("author"));
                        String bookintroduction = rs.getString("bookintroduction");
                        b.setBookintroduction(bookintroduction);
                        String bookcontent = rs.getString("bookcontent");
                        b.setBookcontent(bookcontent);
                        b.setStatus(rs.getInt("status"));
                        b.setIsDeleted(rs.getInt("isDeleted"));
                        return b;
                    }
                    return null;
                }
            }, DelFlagE.NO.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
