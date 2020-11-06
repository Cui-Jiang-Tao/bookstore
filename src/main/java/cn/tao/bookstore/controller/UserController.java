package cn.tao.bookstore.controller;

import cn.tao.bookstore.domain.Cart;
import cn.tao.bookstore.exception.UserException;
import cn.tao.bookstore.domain.User;
import cn.tao.bookstore.service.UserService;
import cn.tao.bookstore.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public String regist(HttpServletRequest request, User userForm) throws IOException {
        /**
         * 表单校验
         */
        // * 封装表单数据
        userForm.setUid(CommonUtils.uuid());
        //激活码
        userForm.setCode(CommonUtils.uuid() + CommonUtils.uuid());

        //输入校验
        Map<String, String> errors = new HashMap<String, String>();

        String username = userForm.getUsername();
        if(username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空！");
        }
        else if (username.length() < 3 || username.length() > 10) {
            errors.put("username", "用户名长度必须在3~10之间！");
        }

        String password = userForm.getPassword();
        if(password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空！");
        }
        else if (password.length() < 3 || password.length() > 10) {
            errors.put("password", "密码长度必须在3~10之间！");
        }

        String email = userForm.getEmail();
        if(email == null || email.trim().isEmpty()) {
            errors.put("email", "Email不能为空！");
        }
        else if (!email.matches("\\w+@\\w+\\.\\w+")) {
            errors.put("email", "Email格式错误！");
        }

        //判断是否存在错误信息
        if(errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("form", userForm);

            return "forward:/jsps/user/regist.jsp";
        }

        /**
         * 调用service的regist()方法
         */
        try {
            userService.regist(userForm);
        } catch (UserException e) {
            /**
             * 邮箱或用户名已被注册
             */
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("form", userForm);

            return "forward:/jsps/user/regist.jsp";
        }

        request.setAttribute("msg", "恭喜注册成功！请到邮箱激活！");

        return "forward:/jsps/msg.jsp";
    }

    /**
     * 邮箱激活功能
     */
    @GetMapping("/active")
    public String active(HttpServletRequest request) {
        String code = request.getParameter("code");

        try {
            userService.active(code);
            request.setAttribute("msg", "恭喜，激活成功！！！");
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
        }

        return "forward:/jsps/msg.jsp";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, User userform, HttpServletResponse response) {
        try {
            User user = userService.login(userform);
            HttpSession session = request.getSession();
            session.setAttribute("session_user", user);

            //设置客户端的jsessionid存活时间，和服务端保持一致
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(session.getMaxInactiveInterval());
            response.addCookie(cookie);

            //给用户添加一辆购物车
            request.getSession().setAttribute("cart", new Cart());

            return "redirect:/index.jsp";
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("form", userform);
            return "forward:/jsps/user/login.jsp";
        }
    }

    @RequestMapping("/quit")
    public String quit(HttpServletRequest request) {
        //使session失效
        request.getSession().invalidate();

        return "redirect:/index.jsp";
    }
}
