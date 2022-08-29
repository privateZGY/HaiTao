package com.automatic.AutomaticControl.administration.service.impl;

import com.automatic.AutomaticControl.administration.entity.AdministrationDictionaryDetails;
import com.automatic.AutomaticControl.administration.mapper.AdministrationDictionaryDetailsMapper;
import com.automatic.AutomaticControl.administration.service.IAdministrationDictionaryDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典详情表 服务实现类
 * </p>
 *
 * @author zgy
 * @since 2022-06-29
 */
@Service
public class AdministrationDictionaryDetailsServiceImpl extends ServiceImpl<AdministrationDictionaryDetailsMapper, AdministrationDictionaryDetails> implements IAdministrationDictionaryDetailsService {
    // private AdministrationDictionaryDetailsMapper administrationDictionaryDetailsMapper;
    // 方法重写的注释
    @Override
    public String queryDictTextByKey(String code, Integer key) {
        return this.baseMapper.queryDictTextByKey(code, key);
//        return administrationDictionaryDetailsMapper.queryDictTextByKey(code, key);
    }
}
