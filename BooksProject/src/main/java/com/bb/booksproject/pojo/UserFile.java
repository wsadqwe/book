package com.bb.booksproject.pojo;

public class UserFile {
    private Integer id;

    private String times;

    private String imgsrc;

    private String txtsrc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}