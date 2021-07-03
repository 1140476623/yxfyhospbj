package sjjd.doc.line.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sjjd.doc.line.pojo.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class LogInterceptor implements HandlerInterceptor {


    @Value("${web.redirect.path}")
    private String ipUrl;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        String sessionId = session.getId();

        Admin admin = (Admin) session.getAttribute("admin");

        String url = ipUrl + "/admin/showlogin";
        if (admin == null) {
            response.sendRedirect(url);
            return false;
        } else {
            String sessionid = stringRedisTemplate.opsForValue().get("aId" + admin.getId());
            if (!sessionid.equals(sessionId)) {
                System.out.println("登录拦截器拦截");
                String urls = ipUrl + "/admin/loginmessage";
                response.sendRedirect(urls);
                return false;
            }
        }
        return true;

    }

}
