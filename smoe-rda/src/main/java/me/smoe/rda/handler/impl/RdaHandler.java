/**
 * Copyright (c) 2016, adar.w (adar-w@outlook.com) 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.smoe.rda.handler.impl;

import java.io.Serializable;
import java.util.List;

import me.smoe.rda.JDBC;
import me.smoe.rda.common.PageAndOrder;
import me.smoe.rda.handler.sqlbuilder.SQLBuilder;
import me.smoe.rda.handler.sqlbuilder.impl.StandardSQLBuilder;

public final class RdaHandler {
	
	private static final SQLBuilder sqlBuilder;
	static {
		sqlBuilder = new StandardSQLBuilder();
	}

	public <T> void save(T entity) {
		JDBC.execute(sqlBuilder.save(entity));
	}

	public <T> int modify(T entity) {
		return JDBC.execute(sqlBuilder.modify(entity));
	}
	
	public <T> T findOne(Class<T> clazz, Serializable id) {
		return JDBC.query(sqlBuilder.findOne(clazz, id), clazz);
	}

	public <T> List<T> find(Class<T> clazz, PageAndOrder pageAndOrder) {
		return JDBC.querys(sqlBuilder.find(clazz, pageAndOrder), clazz);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(T entity, PageAndOrder pageAndOrder) {
		return JDBC.querys(sqlBuilder.find(entity, pageAndOrder), (Class<T>) entity.getClass());
	}

	public <T> List<T> findAll(Class<T> clazz, PageAndOrder pageAndOrder) {
		return JDBC.querys(sqlBuilder.findAll(clazz, pageAndOrder), clazz);
	}

	public <T> int delete(Class<T> clazz, Serializable id) {
		return JDBC.execute(sqlBuilder.delete(clazz, id));
	}

	public <T> int deleteAll(Class<T> clazz) {
		return JDBC.execute(sqlBuilder.deleteAll(clazz));
	}

	public <T> long count(Class<T> clazz, T entity) {
		return JDBC.get(sqlBuilder.count(clazz, entity), Long.class);
	}

	public <T> boolean exists(Class<T> clazz, Serializable id) {
		return findOne(clazz, id) != null;
	}

	public <T> Long build(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
}