package com.automatic.AutomaticControl.jurisdiction.mapper;

import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionRoleJurisdiction;
import com.automatic.AutomaticControl.jurisdiction.entity.JurisdictionTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author zgy
 * @since 2022-07-01
 */
public interface JurisdictionRoleJurisdictionMapper extends BaseMapper<JurisdictionRoleJurisdiction> {

    List<JurisdictionTable> selectRoleJurisdiction(@Param("roleId") Integer roleId);
}
