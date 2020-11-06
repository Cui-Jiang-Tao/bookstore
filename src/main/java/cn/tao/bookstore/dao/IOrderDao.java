package cn.tao.bookstore.dao;

import cn.tao.bookstore.domain.Order;
import cn.tao.bookstore.domain.OrderItem;
import cn.tao.bookstore.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderDao {
    public List<Order> findOrdersByUid(String uid);
    public List<Order> findOrdersByState(@Param("state") String state);

    /* 该方法只有dao使用，用来查询注入 */
    public Order findOrderByOid(String oid);

    /*暂时还没用到*/
    public Order findOrderByOidAndUid(@Param("oid") String oid, @Param("uid") String uid);

    /* 该方法只有dao使用，用来查询注入 */
    public List<OrderItem> findOrderItemsByOid(String oid);

    public void addOrder(Order order);

    public void addOrderItems(@Param("orderItems") List<OrderItem> orderItems);

    public void setOrderStateByOid(@Param("oid") String oid, @Param("state") Integer state);
}
