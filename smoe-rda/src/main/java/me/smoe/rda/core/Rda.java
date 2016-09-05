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
package me.smoe.rda.core;

import java.io.Serializable;

import javax.sql.DataSource;

import me.smoe.rda.exception.RdaException;
import me.smoe.rda.handler.SQLHandler;
import me.smoe.rda.handler.impl.StandardSQLHandler;

public class Rda<T> {
	
	private static final SQLHandler sqlHandler;
	static {
		sqlHandler = new StandardSQLHandler();
	}

	private static boolean init;

	public static void to(DataSource ds) {
		JDBC.init(ds);
		
		init = true;
	}

	public static void to(String url, String un, String pw) {
		JDBC.init(url, un, pw);
		
		init = true;
	}

	public static <T> RdaBuilder<T> at(Class<T> clazz) throws NoSuchFieldException, SecurityException {
		return new RdaBuilder<T>(clazz);
	}
	
	public static class RdaBuilder<T> {
		
		private Class<T> clazz;
		
		RdaBuilder(Class<T> clazz) {
			if (init) {
				this.clazz = clazz;
			} else {
				throw new RuntimeException("Need init.");
			}
		}

		public void save(T entity) {
			sqlHandler.save(entity);
		}

		public void save(Iterable<T> entities) {
			sqlHandler.save(entities);
		}

		public T findOne(Serializable id) throws RdaException {
			return sqlHandler.findOne(clazz, id);
		}

		public Iterable<T> find() throws RdaException {
			return sqlHandler.find(clazz);
		}

		public Iterable<T> find(T entity) throws RdaException {
			return sqlHandler.find(clazz, entity);
		}

		public Iterable<T> find(Iterable<? extends Serializable> ids) throws RdaException {
			return sqlHandler.find(clazz, ids);
		}

		public Iterable<T> findAll() throws RdaException {
			return sqlHandler.findAll(clazz);
		}

		public void delete(Serializable id) throws RdaException {
			sqlHandler.delete(clazz, id);
		}

		public void delete(Iterable<? extends Serializable> ids) throws RdaException {
			sqlHandler.delete(clazz, ids);
		}

		public void deleteAll() throws RdaException {
			sqlHandler.deleteAll(clazz);
		}

		public long count() throws RdaException {
			return sqlHandler.count(clazz, null);
		}

		public long count(T entity) throws RdaException {
			return sqlHandler.count(clazz, entity);
		}

		public boolean exists(Class<T> clazz, Serializable id) throws RdaException {
			return sqlHandler.exists(clazz, id);
		}

		public Long build(String sql) throws RdaException {
			// TODO
			return null;
		}
	}
}
