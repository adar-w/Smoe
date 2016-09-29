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
import java.util.Map;

import me.smoe.mda.Assert;
import me.smoe.mda.Clazzs;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> SQLData findOne(Class<T> clazz, Serializable id) throws RdaException {
		Assert.notNull(clazz);
		Assert.notNull(id);
		
		return SQLData.be(String.format(SQLConstant.SM_FINDONE, String.join(SQLConstant.COMMA, Clazzs.fields(clazz)), SQLBuilder.tableName(clazz), SQLBuilder.placeholders(1)), id);
	}

	@Override
	public <T> SQLData find(Class<T> clazz) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> SQLData find(Class<T> clazz, T entity) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> SQLData find(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> SQLData findAll(Class<T> clazz) throws RdaException {
		Assert.notNull(clazz);
		
		return SQLData.be(String.format(SQLConstant.SM_FINDALL, SQLBuilder.tableName(clazz)));
	}

	@Override
	public <T> SQLData delete(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> SQLData delete(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> SQLData deleteAll(Class<T> clazz) throws RdaException {
		Assert.notNull(clazz);
		
		return SQLData.be(String.format(SQLConstant.SM_DELETEALL, SQLBuilder.tableName(clazz)));
	}

	@Override
	public <T> SQLData count(Class<T> clazz, T entity) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> SQLData exists(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}
}
