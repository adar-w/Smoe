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
import me.smoe.rda.handler.impl.RdaHandler;

public final class Rda<T> {
	
	private static final RdaHandler handler;
	static {
		handler = new RdaHandler();
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

	public static <T> RdaBe<T> at(Class<T> clazz) {
		return new RdaBe<T>(clazz);
	}

	public static class RdaBe<T> {
		
		private Class<T> clazz;
		
		RdaBe(Class<T> clazz) {
			if (init) {
				this.clazz = clazz;
			} else {
				throw new RuntimeException("Need init.");
			}
		}

		public void save(T entity) throws Exception {
			handler.save(entity);
		}

		public T findOne(Serializable id) throws RdaException {
			return handler.findOne(clazz, id);
		}

		public Iterable<T> find() throws RdaException {
			return handler.find(clazz);
		}

		public Iterable<T> find(T entity) throws RdaException {
			return handler.find(clazz, entity);
		}

		public Iterable<T> find(Iterable<? extends Serializable> ids) throws RdaException {
			return handler.find(clazz, ids);
		}

		public Iterable<T> findAll() throws RdaException {
			return handler.findAll(clazz);
		}

		public void delete(Serializable id) throws RdaException {
			handler.delete(clazz, id);
		}

		public void delete(Iterable<? extends Serializable> ids) throws RdaException {
			handler.delete(clazz, ids);
		}

		public void deleteAll() throws RdaException {
			handler.deleteAll(clazz);
		}

		public long count() throws RdaException {
			return handler.count(clazz, null);
		}

		public long count(T entity) throws RdaException {
			return handler.count(clazz, entity);
		}

		public boolean exists(Serializable id) throws RdaException {
			return handler.exists(clazz, id);
		}

		public Long build(String sql) throws RdaException {
			// TODO
			return null;
		}
	}
}