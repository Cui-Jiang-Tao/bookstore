package cn.tao.bookstore.controller;

import cn.tao.bookstore.domain.Category;
import cn.tao.bookstore.exception.CategoryException;
import cn.tao.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request) {
        request.setAttribute("categoryList", categoryService.findAll());

        return "forward:/jsps/left.jsp";
    }
}
