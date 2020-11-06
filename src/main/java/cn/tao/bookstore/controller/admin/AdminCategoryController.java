package cn.tao.bookstore.controller.admin;

import cn.tao.bookstore.dao.ICategoryDao;
import cn.tao.bookstore.domain.Category;
import cn.tao.bookstore.exception.CategoryException;
import cn.tao.bookstore.service.CategoryService;
import cn.tao.bookstore.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/AdminCategory")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request) {
        request.setAttribute("categoryList", categoryService.findAll());

        return "forward:/adminjsps/admin/category/list.jsp";
    }

    @RequestMapping("add")
    public String add(String cname, HttpServletRequest request) {
        try {
            categoryService.add(new Category(CommonUtils.uuid(), cname));

            return findAll(request);
        } catch (CategoryException e) {
            request.setAttribute("msg", e.getMessage());

            return "forward:/adminjsps/msg.jsp";
        }
    }

    @RequestMapping("/deleteByCid")
    public String deleteByCid(String cid, HttpServletRequest request) {
        try {
            categoryService.deleteByCid(cid);

            return findAll(request);
        } catch (CategoryException e) {
            request.setAttribute("msg", e.getMessage());

            return "forward:/adminjsps/msg.jsp";
        }
    }

    @RequestMapping("/editPre")
    public String editPre(String cid, HttpServletRequest request) {
        Category category = categoryService.findByCid(cid);
        request.setAttribute("category", category);

        return "forward:/adminjsps/admin/category/mod.jsp";
    }

    @RequestMapping("edit")
    public String edit(Category category, HttpServletRequest request) {
        categoryService.editCnameByCid(category);

        return findAll(request);
    }
}
