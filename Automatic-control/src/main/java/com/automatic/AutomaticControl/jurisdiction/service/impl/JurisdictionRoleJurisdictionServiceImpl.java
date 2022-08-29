package com.automatic.AutomaticControl.jurisdiction.service.impl;

import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionRoleJurisdiction;
import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionTable;
import com.automatic.AutomaticControl.jurisdiction.mapper.JurisdictionRoleJurisdictionMapper;
import com.automatic.AutomaticControl.jurisdiction.service.IJurisdictionRoleJurisdictionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author zgy
 * @since 2022-07-01
 */
@Service
public class JurisdictionRoleJurisdictionServiceImpl extends ServiceImpl<JurisdictionRoleJurisdictionMapper, JurisdictionRoleJurisdiction> implements IJurisdictionRoleJurisdictionService {

    @Autowired(required = false)
    private JurisdictionRoleJurisdictionMapper jurisdictionRoleJurisdictionMapper;

    @Override
    public List<JurisdictionTable> selectRoleJurisdiction(Integer roleId) {
        List<JurisdictionTable> jurisdictionTable = jurisdictionRoleJurisdictionMapper.selectRoleJurisdiction(roleId);
        return jurisdictionTable;
    }
}
