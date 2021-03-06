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
package me.smoe.rda.handler.sqlbuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import me.smoe.mda.Assert;
import me.smoe.mda.Clazzs;
import me.smoe.mda.Strings;
import me.smoe.rda.annotation.Table;
import me.smoe.rda.common.PageAndOrder;
import me.smoe.rda.common.SQLConstant;
import me.smoe.rda.exception.RdaException;

public interface SQLBuilder {
	
	static <T> String tableName(T entity) {
		Assert.notNull(entity);
		
		return tableName(entity.getClass());
	}
	
	static <T> String tableName(Class<T> clazz) {
		Assert.notNull(clazz);
		
		if (Clazzs.hasAnnotation(clazz, Table.class)) {
			try {
				return Clazzs.annotationVal(clazz, Table.class);
			} catch (Exception e) {
				throw new RdaException(e);
			}
		}
		
		return clazz.getSimpleName().toLowerCase();
	}
	
	static <T> Serializable id(T entity) {
		Assert.notNull(entity);
		
		try {
			return Clazzs.fieldVal(entity, SQLConstant.ID, Serializable.class);
		} catch (Exception e) {
			throw new RdaException(e);
		}
	}
	
	static String columns(Collection<String> columns) {
		return String.join(SQLConstant.CB, columns);
	}
	
	static String wheres(Map<String, Object> fields) {
		return wheres(fields, true);
	}
	
	static String wheres(Map<String, Object> fields, boolean allowNull) {
		Assert.notNull(fields);
		
		StringBuilder buf = new StringBuilder(SQLConstant.WHERE);
		fields.forEach((k, v) -> {
			if (v == null && !allowNull) {
				return;
			} else {
				buf.append(SQLConstant.BLANK);
				buf.append(k);
				
				if (v == null) {
					buf.append(SQLConstant.BLANK + SQLConstant.IS_NULL);
				} else {
					buf.append(SQLConstant.BLANK + SQLConstant.EQ + SQLConstant.BLANK);
					buf.append(SQLConstant.PLACEHOLDER);
				}
			}
			
			buf.append(SQLConstant.BLANK + SQLConstant.AND);
		});
		
		return buf.toString().substring(0, buf.length() - 4);
	}
	
	static String sets(Map<String, Object> fields) {
		Assert.notNull(fields);
		
		StringBuilder buf = new StringBuilder();
		fields.forEach((k, v) -> {
			if (v == null) {
				return;
			} else {
				buf.append(k);
				buf.append(SQLConstant.BLANK + SQLConstant.EQ + SQLConstant.BLANK);
				buf.append(SQLConstant.PLACEHOLDER);
			}
			
			buf.append(SQLConstant.CB);
		});
		
		return buf.toString().substring(0, buf.length() - 2);
	}
	
	static String pageAndOrder(PageAndOrder pageAndOrder) {
		Assert.notNull(pageAndOrder);
		
		String limit = Strings.EMPTY;
		if (pageAndOrder.getLimit() != null) {
			StringBuilder buf = new StringBuilder(SQLConstant.LIMIT);
			Arrays.stream(pageAndOrder.getLimit()).forEach(e -> {
				buf.append(SQLConstant.BLANK);
				buf.append(e);
				buf.append(SQLConstant.COMMA);
			});
			
			limit = buf.toString().substring(0, buf.length() - 1);
		}
		
		String orderBy = Strings.EMPTY;
		if (!pageAndOrder.getOrderBy().isEmpty()) {
			StringBuilder buf = new StringBuilder(SQLConstant.ORDER_BY);
			pageAndOrder.getOrderBy().forEach((k, v) -> {
				buf.append(SQLConstant.BLANK);
				buf.append(k);
				buf.append(SQLConstant.BLANK);
				buf.append(v ? SQLConstant.ASC : SQLConstant.DESC);
				buf.append(SQLConstant.COMMA);
			});
			orderBy = buf.toString().substring(0, buf.length() - 1);
		}
		
		return String.join(SQLConstant.BLANK, limit, orderBy).trim();
	}
	
	static String placeholders(int count) {
		StringBuilder buf = new StringBuilder();
		
		Stream.generate(() -> SQLConstant.PCB).limit(count).forEach(buf::append);
		
		return buf.toString().substring(0, buf.length() - 2);
	}
	
	<T> SQLData save(T entity);

	<T> SQLData modify(T entity);
	
	<T> SQLData findOne(Class<T> clazz, Serializable id) throws RdaException;
		
	<T> SQLData find(T entity, PageAndOrder pageAndOrder) throws RdaException;
	
	<T> SQLData findAll(Class<T> clazz, PageAndOrder pageAndOrder) throws RdaException;

	<T> SQLData delete(Class<T> clazz, Serializable id) throws RdaException;

	<T> SQLData deleteAll(Class<T> clazz) throws RdaException;

	<T> SQLData count(Class<T> clazz, T entity) throws RdaException;

}
