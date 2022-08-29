package com.automatic.AutomaticControl.administration.mapper;

import com.automatic.AutomaticControl.administration.entity.AdministrationDictionaryDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典详情表 Mapper 接口
 * </p>
 *
 * @author zgy
 * @since 2022-06-29
 */
public interface AdministrationDictionaryDetailsMapper extends BaseMapper<AdministrationDictionaryDetails> {
    String queryDictTextByKey(@Param("code") String code, @Param("key") Integer key);
}
