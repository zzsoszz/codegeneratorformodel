package com.bxtel.sms.bo;
import com.bxtel.sms.model.*;


import com.bxtel.sms.dao.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.*;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SortParse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.List;
import java.util.Map;

@Service("SmsBO")
public class SmsBO 
{
	
	@Autowired
	public SmsRepository smsRepository;
	
	private static final Log logger = LogFactory.getLog(SmsBO.class);
	
	public Sms add(Sms model){
			return smsRepository.save(model);
	}
	
	public void delete(Sms model){
			smsRepository.delete(model);
	}
	
	public List<Sms> search(Map<String, Object> searchParams,Map<String, Object> sortParams)
	{
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Sms> spec = DynamicSpecifications.bySearchFilter(filters.values(), Sms.class);
		Sort sort=SortParse.parse(sortParams);
		List<Sms> list = smsRepository.findAll(spec,sort);
		return list;
	}
	
	
	public Page<Sms> search(Map<String, Object> searchParams,Map<String, Object> sortParams,Integer page,Integer pagesize)
	{
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Sms> spec = DynamicSpecifications.bySearchFilter(filters.values(), Sms.class);
		Sort sort=SortParse.parse(sortParams);
		PageRequest pageRequest=null;
		if(sort!=null)
		{
			pageRequest=new PageRequest(page,pagesize,sort);
		}else{
			pageRequest=new PageRequest(page,pagesize);
		}
		Page<Sms> p = smsRepository.findAll(spec,pageRequest);
		return p;
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
