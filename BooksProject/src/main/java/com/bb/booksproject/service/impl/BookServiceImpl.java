package com.bb.booksproject.service.impl;

import com.bb.booksproject.dao.BookDao;
import com.bb.booksproject.dao.impl.BookDaoImpl;
import com.bb.booksproject.pojo.Book;
import com.bb.booksproject.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    /**
     * 获取书籍表单
     * @param book
     * @return
     */
    @Override
    public List<Book> list(Book book) {
        return bookDao.list(book);
    }

    /**
     * 添加书籍信息
     * @param book
     * @return
     */
    @Override
    public Integer saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    /**
     * 更新书籍信息
     * @param book
     * @return
     */
    @Override
    public Integer updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    /**
     * 通过id删除书籍信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Integer id) {
        return bookDao.deleteById(id);
    }

    @Override
    public Book queryById(Integer id) {
        return bookDao.queryById(id);
    }
}
