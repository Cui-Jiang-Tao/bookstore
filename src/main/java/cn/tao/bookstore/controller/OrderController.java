package cn.tao.bookstore.controller;

import cn.tao.bookstore.domain.Order;
import cn.tao.bookstore.domain.User;
import cn.tao.bookstore.exception.OrderException;
import cn.tao.bookstore.service.OrderService;
import cn.tao.bookstore.exception.SessionUserException;
import cn.tao.bookstore.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/Order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/findOrdersByUid")
    public String findOrdersByUid(HttpServletRequest request) throws SessionUserException {
        request.setAttribute("orderList",
                orderService.findOrdersByUid(CommonUtils.getSessionUid(request)));

        return "forward:/jsps/order/list.jsp";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) throws SessionUserException {
        Order order = orderService.add(CommonUtils.getSessionUid(request), (User) request.getSession().getAttribute("session_user"));
        request.setAttribute("order", order);
        return prePay(order.getOid(), request);

    }


    @RequestMapping("/prePay")
    public String prePay(String oid, HttpServletRequest request) throws SessionUserException {
        Order order = (Order) request.getAttribute("order");

        if (order == null) {
            try {
                order = orderService.findOrderByOidAndUid(oid, CommonUtils.getSessionUid(request));
                request.setAttribute("order", order);
            } catch (OrderException e) {
                request.setAttribute("msg", e.getMessage());
                return "forward:/jsps/msg.jsp";
            }
        }

        return "forward:/jsps/order/desc.jsp";
    }

    @RequestMapping(value = {"/pay/{state}", "/confirm/{state}"})
    /*点击直接支付*/
    public String setOrderState(String oid, HttpServletRequest request, @PathVariable Integer state) throws SessionUserException {
        try {
            Order order = orderService.findOrderByOidAndUid(oid, CommonUtils.getSessionUid(request));
            orderService.setOrderStateByOid(oid, state, order.getState());
        } catch (OrderException e) {
            request.setAttribute("msg", e.getMessage());
            return "forward:/jsps/msg.jsp";
        } catch (SessionUserException e) {
            e.printStackTrace();
            return "forward:/jsps/user/login.jsp";
        }

        return findOrdersByUid(request);
    }
}
