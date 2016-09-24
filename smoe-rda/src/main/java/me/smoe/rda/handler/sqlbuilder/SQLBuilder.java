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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import me.smoe.mda.Assert;
import me.smoe.rda.exception.RdaException;

public interface SQLBuilder {
	
	static <T> String tableName(T entity) {
		Assert.notNull(entity);
		
		return entity.getClass().getSimpleName();
	} 
	
	static <T> Map<String, Object> fields(T entity) {
		Assert.notNull(entity);
		
		Map<String, Object> fields = new LinkedHashMap<>();
		Stream.of(entity.getClass().getDeclaredFields()).forEach((e) -> {
			try {
				e.setAccessible(true);
				
				fields.put(e.getName(), e.get(entity));
			} catch (Exception exception) {
				throw new RdaException(exception);
			}
		});
		
		return fields;
	}
	
	static String buildPlaceholders(int count) {
		StringBuilder buf = new StringBuilder();
		
		Stream.generate(() -> "?, ").limit(count).forEach(buf::append);
		
		return buf.toString().substring(0, buf.length() - 2);
	}
	
	<T> SQLData save(T entity);

	<T> SQLData modify(T entity);
	
	<T> SQLData findOne(Class<T> clazz, Serializable id) throws RdaException;
		
	<T> SQLData find(Class<T> clazz) throws RdaException;

	<T> SQLData find(Class<T> clazz, T entity) throws RdaException;
	
	<T> SQLData find(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException;

	<T> SQLData findAll(Class<T> clazz) throws RdaException;

	<T> SQLData delete(Class<T> clazz, Serializable id) throws RdaException;

	<T> SQLData delete(Class<T> clazz, Iterable<? extends Serializable> ids) throws RdaException;

	<T> SQLData deleteAll(Class<T> clazz) throws RdaException;

	<T> SQLData count(Class<T> clazz, T entity) throws RdaException;

	<T> SQLData exists(Class<T> clazz, Serializable id) throws RdaException;

}
