package cn.tao.bookstore.service;

import cn.tao.bookstore.dao.ICartDao;
import cn.tao.bookstore.domain.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private ICartDao cartDao;

    public double getCartTotal(List<Cart> cartList) {
        double total = 0.0;

        for (Cart cart : cartList) {
            total += cart.getTotal();
        }

        return total;
    }

    public List<Cart> findCartsByUid(String uid) {
        return cartDao.findCartsByUid(uid);
    }

    /*public Cart findCartByUidAndBid(String uid, String bid) {
        return cartDao.findCartByUidAndBid(uid, bid);
    }*/

    public void add(Cart cart) {
        Cart cartOld = cartDao.findCartByUidAndBid(cart.getUid(), cart.getBid());

        if (cartOld != null) {
            updateCountByUidAndBid(cart.getUid(), cart.getBid(), cart.getCount() + cartOld.getCount());
        } else {
            cartDao.add(cart);
        }
    }

    private void updateCountByUidAndBid(String uid, String bid, Integer count) {
        cartDao.updateCountByUidAndBid(uid, bid, count);
    }

    public void delete(String uid, String bid) {
        cartDao.delete(uid, bid);
    }

    public void deleteAll(String uid) {
        cartDao.deleteAll(uid);
    }
}
