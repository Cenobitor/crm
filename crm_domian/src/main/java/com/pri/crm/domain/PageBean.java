package com.pri.crm.domain;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 12:31 AM 24/02/2018
 * @Modified By:
 */
public class PageBean<T> {
/*	商品的列表数据 List<Product> list
	当前页         int curPage
	总页数		   int sumPage
	总数量		   int count
	一页显示数量   int curSize:
	*/

	private List<T> list;
	private int currentPage;
	private int totalSize;
	private int pageSize;
	private int totalPage;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"list=" + list +
				", currentPage=" + currentPage +
				", totalSize=" + totalSize +
				", pageSize=" + pageSize +
				", totalPage=" + totalPage +
				'}';
	}
}

