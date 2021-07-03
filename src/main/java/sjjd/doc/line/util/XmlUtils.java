package sjjd.doc.line.util;

import lombok.extern.slf4j.Slf4j;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by xyw
 * @date 2021/4/22.
 */

@Slf4j
public class XmlUtils {

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        String output = null;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.newDocument();
            org.w3c.dom.Element root = document.createElement("Request");
            document.appendChild(root);
            for (String key : data.keySet()) {
                String value = data.get(key);
                if (value == null) {
                    value = "";
                }
                value = value.trim();
                org.w3c.dom.Element filed = document.createElement(key);
                filed.appendChild(document.createTextNode(value));
                root.appendChild(filed);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
            log.info("请求参数:\n{}", output);
            writer.close();
        } catch (Exception ex) {
            log.info("Map转换为XML格式失败" + ex.toString());
        }
        return output;
    }


    /**
     * @description: bean对象转换为 map
     * @param: [obj]
     * @author: xyw
     * @date: 2021/4/26
     */
    public static Map<String, String> beanToMap(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<>();
        for (Field field : fields) {
            // 对于每个属性，获取属性名
            String key = field.getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = field.isAccessible();
                // 修改访问控制权限
                field.setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                String value = field.get(obj) + "";
                map.put(key, value);
                // 恢复访问控制权限
                field.setAccessible(accessFlag);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(map);
        return map;
    }
}
