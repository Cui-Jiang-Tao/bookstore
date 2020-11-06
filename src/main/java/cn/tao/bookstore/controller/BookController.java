package cn.tao.bookstore.controller;

import cn.tao.bookstore.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
* forward
* redirect
* */

@Controller
@RequestMapping("/Book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/findAll")
    String findAll(HttpServletRequest request) {
        request.setAttribute("bookList", bookService.findAllByConditional(false));

        return "forward:/jsps/book/list.jsp";
    }

    @RequestMapping("/findByCategory")
    public String findByCategory(@Param("cid") String cid, HttpServletRequest request) {
        request.setAttribute("bookList", bookService.findByCategoryByConditional(cid, false));

        return "forward:/jsps/book/list.jsp";
    }

    @RequestMapping("/findByBid")
    public String findByBid(@Param("bid") String bid, HttpServletRequest request) {
        request.setAttribute("book", bookService.findByBidByConditional(bid, false));

        return "forward:/jsps/book/desc.jsp";
    }
}
