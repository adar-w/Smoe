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
import me.smoe.rda.common.SQLConstant;
import me.smoe.rda.exception.RdaException;
import me.smoe.rda.handler.sqlbuilder.SQLBuilder;
import me.smoe.rda.handler.sqlbuilder.SQLData;

public class StandardSQLBuilder implements SQLBuilder {

	@Override
	public <T> SQLData save(T entity) {
		Assert.notNull(entity);

		Map<String, Object> fields = SQLBuilder.fields(entity);
		
		String fieldsName = String.join(SQLConstant.COMMA, fields.keySet());
		
		return SQLData.be(String.format(SQLConstant.INSERT_SM, SQLBuilder.tableName(entity), fieldsName, SQLBuilder.buildPlaceholders(fields.size())), fields.values());
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
		
		
		
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
