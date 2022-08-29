package com.automatic.AutomaticControl.administration.service;

import com.automatic.AutomaticControl.administration.entity.AdministrationDictionaryDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典详情表 服务类
 * </p>
 *
 * @author zgy
 * @since 2022-06-29
 */
public interface IAdministrationDictionaryDetailsService extends IService<AdministrationDictionaryDetails> {

    String queryDictTextByKey(String code, Integer key);

}
