package filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/8/23.
 */
public class LoginFilter implements Filter {
    private String passUrl = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        passUrl = filterConfig.getInitParameter("passUrl");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Object userInfo = httpServletRequest.getSession().getAttribute("user_info");
        String uri = httpServletRequest.getRequestURI();
        uri = uri.substring(httpServletRequest.getContextPath().length());

        //用户没有登陆
        if (userInfo == null) {
                if (passUrl != null && passUrl.length() > 0) {
                String[] passUrlArray = passUrl.split(";");

                for (String passUrlSingle : passUrlArray) {
                    if (uri.equals(passUrlSingle)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            }else {
                    httpServletResponse.sendRedirect("/login.jsp");
            }

            return;
        }else {
            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }
    }

    @Override
    public void destroy() {

    }
}
