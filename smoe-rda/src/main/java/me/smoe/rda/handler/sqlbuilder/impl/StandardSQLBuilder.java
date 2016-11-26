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
package me.smoe.rda.handler.sqlbuilder.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.smoe.mda.Assert;
import me.smoe.mda.Clazzs;
import me.smoe.rda.common.PageAndOrder;
import me.smoe.rda.common.SQLConstant;
import me.smoe.rda.exception.RdaException;
import me.smoe.rda.handler.sqlbuilder.SQLBuilder;
import me.smoe.rda.handler.sqlbuilder.SQLData;

public class StandardSQLBuilder implements SQLBuilder {

	@Override
	public <T> SQLData save(T entity) {
		Assert.notNull(entity);

		Map<String, Object> fields = Clazzs.fields(entity);
		
		return SQLData.be(String.format(SQLConstant.SM_INSERT, SQLBuilder.tableName(entity), SQLBuilder.columns(fields.keySet()), SQLBuilder.placeholders(fields.size())), fields.values().toArray());
	}

	@Override
	public <T> SQLData modify(T entity) {
		Assert.notNull(entity);
		
		Serializable id = SQLBuilder.id(entity);
		Assert.notNull(id, "[Rda] Modify: Id can't be null.");
		
		Map<String, Object> fields = Clazzs.fields(entity, false, SQLConstant.ID);

		List<Object> params = new ArrayList<>(fields.size() + 1);
		params.addAll(fields.values());
		params.add(id);

		return SQLData.be(String.format(SQLConstant.SM_UPDATE, SQLBuilder.tableName(entity), SQLBuilder.sets(fields), SQLBuilder.placeholders(1)), params.toArray());
	}

	@Override
	public <T> SQLData findOne(Class<T> clazz, Serializable id) throws RdaException {
		Assert.notNull(clazz);
		Assert.notNull(id);
		
		return SQLData.be(String.format(SQLConstant.SM_FINDONE, String.join(SQLConstant.COMMA + SQLConstant.BLANK, Clazzs.fields(clazz)), SQLBuilder.tableName(clazz), SQLBuilder.placeholders(1)), id);
	}

	@Override
	public <T> SQLData find(T entity, PageAndOrder pageAndOrder) throws RdaException {
		Assert.notNull(entity);
		Assert.notNull(pageAndOrder);
		
		Map<String, Object> fields = Clazzs.fields(entity, false);
		
		return SQLData.be(String.format(SQLConstant.SM_FIND, SQLBuilder.tableName(entity), SQLBuilder.wheres(fields, false)) + SQLConstant.BLANK + SQLBuilder.pageAndOrder(pageAndOrder), fields.values().toArray());
	}

	@Override
	public <T> SQLData findAll(Class<T> clazz, PageAndOrder pageAndOrder) throws RdaException {
		Assert.notNull(clazz);
		
		return SQLData.be(String.format(SQLConstant.SM_FINDALL, SQLBuilder.tableName(clazz)) + SQLConstant.BLANK + SQLBuilder.pageAndOrder(pageAndOrder));
	}

	@Override
	public <T> SQLData delete(Class<T> clazz, Serializable id) throws RdaException {
		Assert.notNull(clazz);
		Assert.notNull(id);
		
		return SQLData.be(String.format(SQLConstant.SM_DELETE, SQLBuilder.tableName(clazz), SQLBuilder.placeholders(1)), id);
	}

	@Override
	public <T> SQLData deleteAll(Class<T> clazz) throws RdaException {
		Assert.notNull(clazz);
		
		return SQLData.be(String.format(SQLConstant.SM_DELETEALL, SQLBuilder.tableName(clazz)));
	}

	@Override
	public <T> SQLData count(Class<T> clazz, T entity) throws RdaException {
		Assert.notNull(clazz);
		
		if (entity == null) {
			return SQLData.be(String.format(SQLConstant.SM_COUNT, SQLBuilder.tableName(clazz)));
		} else {
			Map<String, Object> fields = Clazzs.fields(entity, false);
			return SQLData.be(String.format(SQLConstant.SM_COUNT_MATCH, SQLBuilder.tableName(clazz), SQLBuilder.wheres(fields, false)), fields.values().toArray());
		}
	}
}
