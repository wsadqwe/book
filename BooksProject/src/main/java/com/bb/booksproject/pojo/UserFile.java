package com.bb.booksproject.pojo;

public class UserFile {
    private Integer id;

    private String  author;

    private String times;

    private String imgsrc;

    private String txtsrc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc == null ? null : imgsrc.trim();
    }

    public String getTxtsrc() {
        return txtsrc;
    }

    public void setTxtsrc(String txtsrc) {
        this.txtsrc = txtsrc == null ? null : txtsrc.trim();
    }
    @Override
    public String toString() {
        return "UserFile{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", times='" + times + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", txtsrc='" + txtsrc + '\'' +
                '}';
    }
}