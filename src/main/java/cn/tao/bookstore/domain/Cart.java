package cn.tao.bookstore.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车存放着多个书籍bid，用户uid
 */

public class Cart {
    private String id;
    private String bid;
    private String uid;
    private Integer count;

    private Book book;

    public Cart() {
    }

    public Cart(String id, String bid, String uid, Integer count) {
        this.id = id;
        this.bid = bid;
        this.uid = uid;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getPrice() {
        return book.getPrice();
    }

    public Double getTotal() {
        return getPrice() * this.count;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", bid='" + bid + '\'' +
                ", uid='" + uid + '\'' +
                ", count=" + count +
                ", book=" + book +
                '}';
    }
}
