package sjjd.doc.line.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sjjd.doc.line.controller.endDocking.payment.entity.Tariff;
import sjjd.doc.line.mapper.TariffMapper;
import sjjd.doc.line.service.ITariffService;

/**
 * @author by xyw
 * @date 2020/12/10.
 */
@Service
public class TariffServiceImpl extends ServiceImpl<TariffMapper, Tariff> implements ITariffService {
}
