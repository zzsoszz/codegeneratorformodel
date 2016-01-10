package com.bxtel.sms.dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
/*
 * 一步步学习 Spring Data 系列
 * http://my.oschina.net/ztlyde/blog/68513
 * http://z276356445t.iteye.com/blog/1602258
 * 
 * 以找到针对JpaRepository和JpaSpecificationExecutor有一个实现类，SimpleJpaRepository.class，这个类实现了刚才所提的两个接口
 */

import com.bxtel.sms.model.Sms;

//@Repository

//@NoRepositoryBean

@Repository
public interface SmsRepository extends PagingAndSortingRepository<Sms, Long>,JpaSpecificationExecutor<Sms> {
	
}