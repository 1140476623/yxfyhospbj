package sjjd.doc.line.service.impl;
/**
 * @author by xyw
 * @date 2021/3/24.
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sjjd.doc.line.mapper.HospitalInfoMapper;
import sjjd.doc.line.pojo.HospitalInfo;
import sjjd.doc.line.service.IHospitalInfoService;

@Service
public class HospitalInfoServiceImpl extends ServiceImpl<HospitalInfoMapper, HospitalInfo> implements IHospitalInfoService {
}
