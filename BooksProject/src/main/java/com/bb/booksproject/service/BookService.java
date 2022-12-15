package com.bb.booksproject.service;

import com.bb.booksproject.pojo.Book;

import java.util.List;

public interface BookService {
    /**
     * 查询所有的书籍信息
     * @return
     */
    public List<Book> list(Book book);

    /**
     * 添加书籍信息
     * @param book
     * @return
     */
    public Integer saveBook(Book book);

    /**
     * 更新书籍信息
     * @param book
     * @return
     */
    public Integer updateBook(Book book);

    /**
     * 根据id删除书籍信息
     * @param id
     * @return
     */
    public Integer deleteById(Integer id);

    /**
     * 根据id查询book信息
     * @param id
     * @return
     */
    public Book queryById(Integer id);
}
