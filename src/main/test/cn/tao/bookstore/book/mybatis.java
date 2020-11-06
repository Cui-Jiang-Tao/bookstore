package cn.tao.bookstore.book;

import cn.tao.bookstore.dao.IBookDao;
import cn.tao.bookstore.domain.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mybatis {
    @Test
    public void findAll() throws IOException {
        String resource = "mapper/SqlSessionFactory.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession =  sqlSessionFactory.openSession(true);

        IBookDao bookDao = sqlSession.getMapper(IBookDao.class);

        List<Book> bookList = bookDao.findAll();

        for(Book book : bookList) {
            System.out.println(book);
        }

    }
}
