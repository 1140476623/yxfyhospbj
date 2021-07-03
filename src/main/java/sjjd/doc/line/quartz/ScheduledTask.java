package sjjd.doc.line.quartz;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sjjd.doc.line.controller.endDocking.payment.entity.Tariff;
import sjjd.doc.line.service.ITariffService;
import sjjd.doc.line.util.HttpClientUtils;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Component
public class ScheduledTask {

    @Value("${hisUrl}")
    private String hisUrl;

    @Value("${hisMethod}")
    private String hisMethod;

    @Resource
    private ITariffService tariffService;

    //当天凌晨两点   同步药价信息
    @Scheduled(cron = " 0  0  2  *  *  ?")
    private void checkTaskMethod() {
        String resJson = HttpClientUtils.callWebStr(hisUrl, hisMethod,
                "GetTariffList", new JSONObject().toString());
        JSONObject jsonObjectss = JSONObject.fromObject(resJson);
        if (!"1".equals(jsonObjectss.getString("state"))) {
            log.info("同步药价信息失败，请求his失败");
            return;
        }
        List<JSONObject> jsonObjects1 = (List<JSONObject>) jsonObjectss.get("data");
        if (CollectionUtils.isEmpty(jsonObjects1)) {
            log.info("同步时,暂无药价信息");
            return;
        }
        for (JSONObject jsonObject : jsonObjects1) {
            Tariff tariff = JSON.parseObject(JSON.toJSONString(jsonObject), Tariff.class);
            QueryWrapper<Tariff> queryWrapper = new QueryWrapper<>();
            tariffService.saveOrUpdate(tariff, queryWrapper.eq("item_code", tariff.getItemCode()));
        }
        log.info("同步药价信息成功");
    }
}
