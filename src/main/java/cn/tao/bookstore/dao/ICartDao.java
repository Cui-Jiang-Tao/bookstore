package cn.tao.bookstore.dao;

import cn.tao.bookstore.domain.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICartDao {
    public List<Cart> findCartByCartUid(String uid);

    public Cart findCartByUidAndBid(@Param("uid") String uid, @Param("bid") String bid);

    public void add(Cart cart);

    public void delete(@Param("uid") String uid, @Param("bid") String bid);

    public void deleteAll(String uid);

    public List<Cart> findCartsByUid(String uid);

    public void updateCountByUidAndBid(@Param("uid") String uid, @Param("bid") String bid, @Param("count") Integer count);
}
