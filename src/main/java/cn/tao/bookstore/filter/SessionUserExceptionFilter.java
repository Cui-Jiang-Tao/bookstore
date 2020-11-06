package cn.tao.bookstore.filter;

import cn.tao.bookstore.exception.SessionUserException;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import javax.servlet.*;
import java.io.IOException;

public class SessionUserExceptionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {

        }
    }

    @Override
    public void destroy() {

    }
}
