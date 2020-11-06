package cn.tao.bookstore.book;

import cn.tao.bookstore.dao.IBookDao;
import cn.tao.bookstore.domain.Book;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class spring_mybatis_Test {
    @Autowired
    private SqlSession sqlSession;
    @Autowired
    private IBookDao bookDao;

    @Test
    public void findAll() {

        List<Book> bookList = bookDao.findAll();

        for(Book book : bookList) {
            System.out.println(book);
        }

    }
}
