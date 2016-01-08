package org.springside.modules.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
public class SortParse {
	public static Sort parse(Map<String, Object> sortParams) {
		if(sortParams==null)
		{
			return null;
		}
		List<Order> orders=new ArrayList<Order>();
		for (Entry<String, Object> entry : sortParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}
			// 拆分Direction与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Direction d=names[0].toLowerCase().equals("desc")?Direction.DESC:Direction.ASC;
			Order order=new Order(d,filedName);
			// 创建searchFilter
			orders.add(order);
		}
		if(orders.size()==0)
		{
			return null;
		}
		Sort sort=new Sort(orders);
		return sort;
	}
}
