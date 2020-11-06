package cn.tao.bookstore.service;

import cn.tao.bookstore.dao.IBookDao;
import cn.tao.bookstore.dao.ICartDao;
import cn.tao.bookstore.dao.IOrderDao;
import cn.tao.bookstore.domain.Cart;
import cn.tao.bookstore.domain.Order;
import cn.tao.bookstore.domain.OrderItem;
import cn.tao.bookstore.domain.User;
import cn.tao.bookstore.exception.OrderException;
import cn.tao.bookstore.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@EnableTransactionManagement
@Transactional
public class OrderService {
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IBookDao bookDao;

    @Transactional(readOnly = true)
    public List<Order> findOrdersByUid(String uid) {
        return orderDao.findOrdersByUid(uid);
    }

    @Transactional(readOnly = true)
    public List<Order> findOrdersByState(String state) {
        return orderDao.findOrdersByState(state);
    }

    public Order add(String uid, User user) {
        List<Cart> cartList = cartDao.findCartsByUid(uid);
        List<OrderItem> orderItems = new ArrayList<>(cartList.size());

        Double total = 0.0;
        String oid = CommonUtils.uuid();

        //暂时不设追order的total
        Order order = new Order(oid, new Date(), 0,
                1, user, null);

        for (Cart cart : cartList) {
            total += cart.getTotal();
            orderItems.add(new OrderItem(CommonUtils.uuid(), cart.getCount(),
                    cart.getTotal(), order, bookDao.findByBidByConditional(cart.getBid(), false)));
        }

        order.setTotal(total);
        order.setOrderItemList(orderItems);

        //把购物车清空，把数据添加到订单上
        cartDao.deleteAll(uid);

        orderDao.addOrder(order);
        orderDao.addOrderItems(orderItems);

        return order;
    }

    public Order findOrderByOidAndUid(String oid, String uid) throws OrderException {
        Order order = orderDao.findOrderByOidAndUid(oid, uid);

        if(order == null) {
            throw new OrderException("非法订单！！！");
        }

        return order;
    }

    public void setOrderStateByOid(String oid, Integer state, Integer oldState) throws OrderException {
        //确保订单状态：1 -> 2 -> 3 -> 4
        if((1 <= state && state <= 4) && (state - oldState == 1)) {
            orderDao.setOrderStateByOid(oid, state);
        } else {
            throw new OrderException("非法订单状态！！！");
        }
    }
}
