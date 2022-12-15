package com.bb.booksproject.pojo;

/**
 * 书籍实体类
 */
public class Book {
    private Integer id;//书籍id
    private String bookname;//书名
    private String author;//作者名
    private String bookintroduction;//书籍介绍
    private String bookcontent;//书籍内容
    private Integer status;//书籍状态 0书库 0书架
    private Integer isDeleted;//是否被删除 0删除 1未删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookintroduction() {
        return bookintroduction;
    }

    public void setBookintroduction(String bookintroduction) {
        this.bookintroduction = bookintroduction;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getBookcontent() {
        return bookcontent;
    }

    public void setBookcontent(String bookcontent) {
        this.bookcontent = bookcontent;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                ", bookintroduction='" + bookintroduction + '\'' +
                ", bookcontent='" + bookcontent + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
