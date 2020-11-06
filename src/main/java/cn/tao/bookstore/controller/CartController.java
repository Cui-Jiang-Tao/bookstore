package cn.tao.bookstore.controller;

import cn.tao.bookstore.domain.Cart;
import cn.tao.bookstore.service.CartService;
import cn.tao.bookstore.exception.SessionUserException;
import cn.tao.bookstore.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/findCartsByUid")
    public String findCartsByUid(HttpServletRequest request) throws SessionUserException {
        List<Cart> cartList = cartService.findCartsByUid(CommonUtils.getSessionUid(request));
        request.setAttribute("cartList", cartList);
        request.setAttribute("cartTotal", cartService.getCartTotal(cartList));
        return "forward:/jsps/cart/list.jsp";
    }



    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String add(HttpServletRequest request, String bid, int count) throws SessionUserException {
        Cart cart = new Cart(CommonUtils.uuid(),bid, CommonUtils.getSessionUid(request), count);
        cartService.add(cart);

        return findCartsByUid(request);
    }

    @RequestMapping("/clear")
    public String clear(HttpServletRequest request) throws SessionUserException {
        String uid = CommonUtils.getSessionUid(request);
        cartService.deleteAll(uid);

        return findCartsByUid(request);
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, String bid) throws SessionUserException {
        String uid = CommonUtils.getSessionUid(request);
        cartService.delete(uid, bid);

        return findCartsByUid(request);
    }
}
