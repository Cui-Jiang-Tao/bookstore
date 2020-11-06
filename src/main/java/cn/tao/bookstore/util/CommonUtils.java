package cn.tao.bookstore.util;

import cn.tao.bookstore.domain.User;
import cn.tao.bookstore.exception.SessionUserException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class CommonUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String getSessionUid(HttpServletRequest request) throws SessionUserException {
        User user = (User) request.getSession().getAttribute("session_user");

        if (user == null) {
            throw new SessionUserException("用户会话中断，需要重新登录！！！");
        }

        return  user.getUid();
    }
}
