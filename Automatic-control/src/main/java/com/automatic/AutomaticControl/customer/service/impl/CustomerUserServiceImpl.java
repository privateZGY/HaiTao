package com.automatic.AutomaticControl.customer.service.impl;

import com.automatic.AutomaticControl.customer.entity.CustomerUser;
import com.automatic.AutomaticControl.customer.mapper.CustomerUserMapper;
import com.automatic.AutomaticControl.customer.service.ICustomerUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zgy
 * @since 2022-06-24
 */
@Service
public class CustomerUserServiceImpl extends ServiceImpl<CustomerUserMapper, CustomerUser> implements ICustomerUserService {

}
