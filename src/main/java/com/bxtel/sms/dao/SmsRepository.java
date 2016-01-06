package com.bxtel.sms.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.bxtel.sms.model.Sms;
/*
 * 一步步学习 Spring Data 系列
 * http://my.oschina.net/ztlyde/blog/68513
 * http://z276356445t.iteye.com/blog/1602258
 * 
 * 以找到针对JpaRepository和JpaSpecificationExecutor有一个实现类，SimpleJpaRepository.class，这个类实现了刚才所提的两个接口
 */
public interface SmsRepository extends PagingAndSortingRepository<Sms, Long>,JpaSpecificationExecutor<Sms> {
	
}