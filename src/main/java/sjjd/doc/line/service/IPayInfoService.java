package sjjd.doc.line.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sjjd.doc.line.pojo.PayInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-18
 */
public interface IPayInfoService extends IService<PayInfo> {


    public String wxPayUrl(PayInfo order);

    public boolean wxOrderQuery(String orderNo);
}
