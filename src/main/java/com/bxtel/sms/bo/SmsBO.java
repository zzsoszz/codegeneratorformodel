package com.bxtel.sms.bo;
import com.bxtel.sms.model.*;
import com.bxtel.sms.dao.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.annotation.Resource;
import javax.persistence.criteria.*;

@Service("SmsBO")
public class SmsBO 
{
	@Resource
	public SmsRepository dao;
	
	private static final Log logger = LogFactory.getLog(SmsBO.class);
	
	public Sms add(Sms model){
			return dao.save(model);
	}
	
	public void delete(Sms model){
			dao.delete(model);
	}
	
	public Page<Sms> findAll(String key)
	{
		return dao.findAll(new Specification<Sms>(){
            @Override
            public Predicate toPredicate(Root<Sms> root, CriteriaQuery<?> query, CriteriaBuilder cb) {  
                root = query.from(Sms.class);  
                Path<String> nameExp = root.get("content");  
                return cb.like(nameExp, "%"+key+"%");  
            }
        }, new PageRequest(1, 5, new Sort(Direction.DESC, new String[] { "status" })));
	}
	
}

//
//
///**
// * 查询某个栏目下的所有文章，并且分页。
// * 
// * 如该栏目下有子栏目，则需要一起显示
// * 
// * @param channelId 栏目id
// * @param searchParams
// * @param pageNumber
// * @param pageSize
// * @return
// */
//public Page<Content> getContentListPaged(final Integer channelId,final Collection<SearchFilter> filters,int pageNumber, int pageSize){
//	
//	return contentDao.findAll(new Specification<Content>(){
//		
//		@Override
//		public Predicate toPredicate(Root<Content> root,
//				CriteriaQuery<?> query, CriteriaBuilder builder) {
//
//			//path转化
//			List<Predicate> orPredicates = Lists.newArrayList();
//			
//			Path<String> idPath = root.get("channel").get("id");
//			Path<String> parentIdsPath = root.get("channel").get("parentIds");
//			
//			Predicate p1 = builder.equal(root.get("channel").get("id"), channelId);
//			orPredicates.add(builder.or(p1));
//			Predicate p2 = builder.like((Path)root.get("channel").get("parentIds"), "%," + channelId + ",%");
//			orPredicates.add(builder.or(p2));
//			
//			//以下是springside3提供的方法
//			Predicate o = DynamicSpecifications.bySearchFilter(filters, Content.class).toPredicate(root, query, builder);
//			
//		    Predicate p = builder.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
//		    query.where(p,o);
//			
//			return null;
//			
//		}
//		
//	}, new PageRequest(pageNumber - 1, pageSize));
//}
//
