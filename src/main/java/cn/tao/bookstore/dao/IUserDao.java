package cn.tao.bookstore.dao;

import cn.tao.bookstore.domain.User;
import org.apache.ibatis.annotations.Param;

public interface IUserDao {
    public User findByUsername(String username);

    public User findByUid(String uid);

    public User findByEmail(String email);

    public void add(User user);

    public User findByCode(String code);

    public void updateState(@Param("uid") String uid, @Param("state") boolean state);
}
