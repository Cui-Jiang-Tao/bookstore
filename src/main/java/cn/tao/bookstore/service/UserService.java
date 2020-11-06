package cn.tao.bookstore.service;

import cn.tao.bookstore.exception.UserException;
import cn.tao.bookstore.dao.IUserDao;
import cn.tao.bookstore.domain.User;
import cn.tao.bookstore.util.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private IUserDao IUserDao;

    /**
     * 注册功能
     * @param form
     * @throws UserException
     */
    public void regist(User form) throws UserException {
        //校验用户名
        User user = IUserDao.findByUsername(form.getUsername());
        if(user != null) {
            throw new UserException("用户名已被注册！");
        }

        user = IUserDao.findByEmail(form.getEmail());
        if(user != null) {
            throw new UserException("Email已被注册！");
        }

        try {
            SendEmailUtil.send_email(form.getEmail(), form.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        IUserDao.add(form);
    }

    /**
     * 激活
     * @param code
     * @throws UserException
     */
    public void active(String code) throws UserException {
        User user = IUserDao.findByCode(code);

        if(user == null) {
            throw new UserException("激活码无效！");
        }

        if(user.isState()) {
            throw new UserException("您已经激活过了，无需重复激活！");
        }

        IUserDao.updateState(user.getUid(), true);
    }

    /**
     * 登录功能
     * @param form
     * @return
     * @throws UserException
     */
    public User login(User form) throws UserException {
        User user = IUserDao.findByUsername(form.getUsername());

        if(user == null ||
                !user.getPassword().equals(form.getPassword())) {
            throw new UserException("用户名或密码错误！");
        }

        if(!user.isState()) {
            throw new UserException("账户尚未激活！");
        }
        return user;
    }
}
