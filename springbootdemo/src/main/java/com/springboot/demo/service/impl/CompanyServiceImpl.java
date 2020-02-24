package com.springboot.demo.service.impl;

import com.springboot.demo.model.Company;
import com.springboot.demo.dao.CompanyMapper;
import com.springboot.demo.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fumj
 * @since 2020-02-21
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
