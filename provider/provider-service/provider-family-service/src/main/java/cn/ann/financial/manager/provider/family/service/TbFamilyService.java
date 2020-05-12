package cn.ann.financial.manager.provider.family.service;

import cn.ann.financial.manager.commons.provider.service.impl.BaseServiceImpl;
import cn.ann.financial.manager.provider.family.api.TbFamilyServiceApi;
import cn.ann.financial.manager.provider.family.domain.TbFamily;
import cn.ann.financial.manager.provider.family.mapper.TbFamilyMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:23:36
 */
@Service(version = "1.0.0")
public class TbFamilyService extends BaseServiceImpl<TbFamily, TbFamilyMapper> implements TbFamilyServiceApi {
    @Override
    protected void insertInit(TbFamily tbFamily) {
        tbFamily.setUserCount(1);
    }

    @Override
    protected void updateInit(TbFamily oldFamily, TbFamily tbFamily) {
        if (StringUtils.isNotBlank(tbFamily.getIntro())) {
            oldFamily.setIntro(tbFamily.getIntro());
        }
        if (StringUtils.isNotBlank(tbFamily.getName())) {
            oldFamily.setName(tbFamily.getName());
        }
        if (tbFamily.getUserCount() != null && !oldFamily.getUserCount().equals(tbFamily.getUserCount())) {
            oldFamily.setUserCount(tbFamily.getUserCount());
        }
    }
}
