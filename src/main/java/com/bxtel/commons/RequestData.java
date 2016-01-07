package com.bxtel.commons;

import java.util.Map;

public class RequestData {
	Integer page;
	Integer pagesize;
	Map<String, Object> search;
	Map<String, Object> sort;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Map<String, Object> getSearch() {
		return search;
	}
	public void setSearch(Map<String, Object> search) {
		this.search = search;
	}
	public Map<String, Object> getSort() {
		return sort;
	}
	public void setSort(Map<String, Object> sort) {
		this.sort = sort;
	}
}
