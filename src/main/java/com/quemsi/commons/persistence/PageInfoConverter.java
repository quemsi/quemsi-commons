package com.quemsi.commons.persistence;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class PageInfoConverter {
	public Pageable toPageable(PageInfo pageInfo) {
		if(pageInfo.isOrdered()) {
			Order order = pageInfo.getAsc()?Order.asc(pageInfo.getOrderBy()): Order.desc(pageInfo.getOrderBy());
			Sort sort = Sort.by(order);
			if(pageInfo.isUnpaged()){
				return Pageable.unpaged(sort);
			}
			return PageRequest.of(pageInfo.getPage(), pageInfo.getPageSize(), sort);
		}
		if(pageInfo.isUnpaged()){
			return Pageable.unpaged();
		}
		return PageRequest.of(pageInfo.getPage(), pageInfo.getPageSize());
	}
}
