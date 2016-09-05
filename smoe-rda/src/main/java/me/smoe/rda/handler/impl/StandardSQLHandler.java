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

import me.smoe.rda.exception.RdaException;
import me.smoe.rda.handler.SQLHandler;

public class StandardSQLHandler implements SQLHandler {

	@Override
	public <T> void save(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void save(Iterable<T> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T findOne(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Iterable<T> find(Class<T> clazz) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Iterable<T> find(Class<T> clazz, T entity) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Iterable<T> find(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Iterable<T> findAll(Class<T> clazz) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void delete(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void delete(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void deleteAll(Class<T> clazz) throws RdaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> long count(Class<T> clazz, T entity) throws RdaException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> boolean exists(Class<T> clazz, Serializable id) throws RdaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> Long build(String sql) throws RdaException {
		// TODO Auto-generated method stub
		return null;
	}

}
