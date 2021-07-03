package sjjd.doc.line.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author by xyw
 * @date 2021/4/22.
 */

@Component
public class HisConfig {

    public static String hisUrl;

    @Value("${hisUrl}")
    public void setHisUrl(String hisUrl) {
        this.hisUrl = hisUrl;
    }
}
