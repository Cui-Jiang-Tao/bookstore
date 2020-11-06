package cn.tao.bookstore.controller.admin;

import cn.tao.bookstore.domain.Book;
import cn.tao.bookstore.service.BookService;
import cn.tao.bookstore.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/AdminBook")
public class AdminBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request) {
        request.setAttribute("bookList", bookService.findAll());

        return "forward:/adminjsps/admin/book/list.jsp";
    }

    @RequestMapping("/findByBid")
    public String findByBid(@Param("bid") String bid, HttpServletRequest request) {
        request.setAttribute("book", bookService.findByBid(bid));
        request.setAttribute("categoryList", categoryService.findAll());

        return "forward:/adminjsps/admin/book/desc.jsp";
    }

    @RequestMapping("/save")
    public String save(Book book, String cid, HttpServletRequest request) {
        book.setCategory(categoryService.findByCid(cid));
        bookService.save(book);

        return findAll(request);
    }
}
