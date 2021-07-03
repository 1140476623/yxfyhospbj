package sjjd.doc.line.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sjjd.doc.line.pojo.Node;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AutoInterceptor implements HandlerInterceptor {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    static Logger logger = LoggerFactory.getLogger(AutoInterceptor.class);


    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();


        List<Node> nodes = (List<Node>) session.getAttribute("nodes");
        List<String> nodeLink = new ArrayList<String>();
        String url = request.getServletPath();
        if (nodes != null && !nodes.isEmpty()) {
            for (Node node : nodes) {
                if (node.getNodeLink() != null) {
                    nodeLink.add(node.getNodeLink());

                }

            }

            if (nodeLink.contains(url)) {
                return true;
            }

        }
        //System.out.println("权限拦截器拦截" + url);
        //重置response
        response.reset();
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter pw = response.getWriter();
        Map<String, String> jsonObject = new HashMap<String, String>();
        jsonObject.put("state", "0");
        jsonObject.put("message", "用户没有此权限,请联系管理员添加");
        String mapjson = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
        pw.write(mapjson);
        return false;

    }

}
