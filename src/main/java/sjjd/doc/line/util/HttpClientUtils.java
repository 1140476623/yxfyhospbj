package sjjd.doc.line.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import sjjd.doc.line.config.HisConfig;
import sjjd.doc.line.pojo.ResponseResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author by xyw
 * @date 2021/4/22.
 */

@Component
@Slf4j
public class HttpClientUtils {


    /**
     * 发送xml请求到server端
     *
     * @param method xml请求方法
     * @param reqXml 发送的xml数据流
     * @return null发送失败，否则返回响应内容
     */
    public static String sendPost(String method, String reqXml) {
        //创建httpclient工具对象
        HttpClient client = new HttpClient();
        //创建post请求方法
        PostMethod post = new PostMethod(HisConfig.hisUrl + method);
        //设置请求超时时间
        client.setConnectionTimeout(2000 * 1000);
        String respStr = null;
        try {
            //设置请求头部类型
            post.setRequestHeader("Content-Type", "application/xml");
            post.setRequestHeader("charset", "UTF-8");
            //设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
            post.setRequestBody(reqXml);
            int statusCode = client.executeMethod(post);
            //只有请求成功200了，才做处理
            if (statusCode == HttpStatus.SC_OK) {
                InputStream inputStream = post.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuffer stringBuffer = new StringBuffer();
                String str;
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }

                respStr = stringBuffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return respStr;
    }

    public static String callWebStr(String wsdUrl, String operationName, String... params) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdUrl);
        try {
            Object[] objects = client.invoke(operationName, params);
            String decrypt = objects[0].toString();
            log.info("His返回数据：{}", decrypt);
            return decrypt;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("远程调用失败：{}", e.toString());
            return null;
        }
    }


    public static String callWebJson(String wsdUrl, String operationName, JSONObject... params) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdUrl);
        try {
            Object[] objects = client.invoke(operationName, params);
            String decrypt = objects[0].toString();
            log.info("His返回数据：{}", decrypt);
            return decrypt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析数据
     *
     * @param data
     * @return
     */
    public static ResponseResult<JSONObject> parseResult(String data) {
        ResponseResult<JSONObject> rr = new ResponseResult<>();
        JSONObject jsonObject = JSONObject.fromObject(data);
        String state = jsonObject.getString("state");
        if ("1".equals(state)) {// 成功
            Object obj = jsonObject.get("data");
            if (obj instanceof JSONObject) {
                JSONObject data1 = jsonObject.getJSONObject("data");
                rr.setState(1);
                rr.setMessage(jsonObject.getString("message"));
                rr.setData(data1);
                return rr;
            } else {
                rr.setState(0);
                rr.setMessage("数据解析错误");
                return rr;
            }
        } else if ("0".equals(state)) {// 失败
            rr.setState(0);
            rr.setMessage(jsonObject.getString("message"));
            return rr;
        } else {
            log.error("调用接口未知错误：" + data);
            rr.setState(0);
            rr.setMessage("未知错误");
            return rr;
        }
    }

    public static ResponseResult<List<JSONObject>> parseResult2(String data) {
        ResponseResult<List<JSONObject>> rr = new ResponseResult<>();
        JSONObject jsonObject = JSONObject.fromObject(data);
        String state = jsonObject.getString("state");
        if ("1".equals(state)) {// 成功
            Object obj = jsonObject.get("data");
            if (obj instanceof JSONArray) {
                List<JSONObject> data1 = jsonObject.getJSONArray("data");
                rr.setState(1);
                rr.setMessage(jsonObject.getString("message"));
                rr.setData(data1);
                return rr;
            } else {
                rr.setState(0);
                rr.setMessage("数据解析错误");
                return rr;
            }
        } else if ("0".equals(state)) {// 失败
            rr.setState(0);
            rr.setMessage(jsonObject.getString("message"));
            return rr;
        } else {
            log.error("调用接口未知错误：" + data);
            rr.setState(0);
            rr.setMessage("未知错误");
            return rr;
        }
    }
}
