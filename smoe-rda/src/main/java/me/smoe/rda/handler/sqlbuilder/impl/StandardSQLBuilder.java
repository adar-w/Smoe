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

import me.smoe.rda.common.Assert;
import me.smoe.rda.common.SQLConstant;
import me.smoe.rda.exception.RdaException;
import me.smoe.rda.handler.sqlbuilder.SQLBuilder;

public class StandardSQLBuilder implements SQLBuilder {

	@Override
	public <T> String save(T entity) {
		Assert.notNull(entity);

		String[] fields = SQLBuilder.fields(entity);
		return String.format(SQLConstant.INSERT_SM, SQLBuilder.tableName(entity), fields[0], fields[1]);
	}

	@Override
	public <T> String save(Iterable<T> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String modify(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String findOne(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String find(Class<T> clazz) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String find(Class<T> clazz, T entity) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String find(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String findAll(Class<T> clazz) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String delete(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String delete(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String deleteAll(Class<T> clazz) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String count(Class<T> clazz, T entity) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> String exists(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}
}
