package cn.tao.bookstore.controller.admin;

import cn.tao.bookstore.domain.Order;
import cn.tao.bookstore.exception.OrderException;
import cn.tao.bookstore.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/AdminOrder")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/findOrdersByState")
    public String findsOrderByState(@Param("state") String state, HttpServletRequest request) {
        /*传入的state是一个对象*/
        List<Order> orderList = orderService.findOrdersByState(state);
        request.setAttribute("orderList", orderList);

        return "forward:/adminjsps/admin/order/list.jsp";
    }

    @RequestMapping("/consignment")
    public String setOrderState(String uid, String oid, Integer state, HttpServletRequest request) {
        try {
            Order order = orderService.findOrderByOidAndUid(oid, uid);
            orderService.setOrderStateByOid(oid, state, order.getState());

            return  findsOrderByState(String.valueOf(state), request);
        } catch (OrderException e) {
            request.setAttribute("msg", e.getMessage());
            return "forward:/jsps/msg.jsp";
        }
    }
}
