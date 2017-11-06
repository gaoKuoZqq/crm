package com.crm.easyui;

import java.util.List;

public class EasyUIDataGrideResult {
	Integer total;
	List<?> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "EasyUIDataGrideResult [total=" + total + ", rows=" + rows + "]";
	}
	
}
