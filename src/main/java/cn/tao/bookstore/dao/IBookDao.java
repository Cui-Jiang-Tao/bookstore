package cn.tao.bookstore.dao;

import cn.tao.bookstore.domain.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IBookDao {
    public List<Book> findAllByConditional(boolean deleted);

    public List<Book> findAll();

    public List<Book> findByCategoryByConditional(@Param("cid") String cid, @Param("deleted") boolean deleted);

    public List<Book> findByCategory(String cid);

    public Book findByBid(String bid);

    public Book findByBidByConditional(@Param("bid") String bid, @Param("deleted") boolean deleted);

    /**
     * 查询指定分类下的图书本数
     */
    public int getCountByCid(String cid);


    /**
     * 添加图书
     */
    public void add(Book book);


    public void save(Book book);
}