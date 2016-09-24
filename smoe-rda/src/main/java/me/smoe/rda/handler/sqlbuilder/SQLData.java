package me.smoe.rda.handler.sqlbuilder;

import java.util.Collection;

public class SQLData {

	private String sql;
	
	private Collection<Object> params;
	
	public static SQLData be(String sql, Collection<Object> params) {
		SQLData data = new SQLData();
		data.setSql(sql);
		data.setParams(params);
		
		return data;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Collection<Object> getParams() {
		return params;
	}

	public void setParams(Collection<Object> params) {
		this.params = params;
	}
}
