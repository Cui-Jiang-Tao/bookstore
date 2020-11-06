package cn.tao.bookstore.service;

import java.util.List;

import cn.tao.bookstore.dao.IBookDao;
import cn.tao.bookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableTransactionManagement
@Transactional
public class BookService {
    @Autowired
    private IBookDao bookDao;

    @Transactional(readOnly = true)
    public List<Book> findAllByConditional(boolean deleted) {
        return bookDao.findAllByConditional(deleted);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findByCategoryByConditional(String cid, boolean deleted) {
        return bookDao.findByCategoryByConditional(cid, deleted);
    }

    @Transactional(readOnly = true)
    public List<Book> findByCategory(String cid) {
        return bookDao.findByCategory(cid);
    }

    @Transactional(readOnly = true)
    public Book findByBidByConditional(String bid, boolean deleted) {
        return bookDao.findByBidByConditional(bid, deleted);
    }

    @Transactional(readOnly = true)
    public Book findByBid(String bid) {
        return bookDao.findByBid(bid);
    }

    public void add(Book book) {
        bookDao.add(book);
    }

    public void save(Book book) {
        bookDao.save(book);
    }
}

