package com.automatic.AutomaticControl.jurisdiction.service;

import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionRoleJurisdiction;
import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author zgy
 * @since 2022-07-01
 */
public interface IJurisdictionRoleJurisdictionService extends IService<JurisdictionRoleJurisdiction> {
    List<JurisdictionTable> selectRoleJurisdiction(Integer roleId);
}
