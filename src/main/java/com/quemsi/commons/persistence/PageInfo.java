package com.quemsi.commons.persistence;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInfo {
	private int page;
	private int pageSize;
	private String orderBy;
	private Boolean asc;
	private boolean unpaged;
	
	public boolean isOrdered() {
		return orderBy != null;
	}
}
