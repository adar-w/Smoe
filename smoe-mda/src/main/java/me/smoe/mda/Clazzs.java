/**
 * Copyright (c) 2015, adar.w (adar-w@outlook.com) 
 * 
 * http://www.smoe.me
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
package me.smoe.mda;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Clazzs {
	
	public static <T> List<String> fields(Class<T> clazz) {
		Assert.notNull(clazz);
		
		List<String> fields = new ArrayList<>();
		Stream.of(clazz.getDeclaredFields()).forEach((e) -> {
			fields.add(e.getName());
		});
		
		return fields;
	}
	
	public static <T> Map<String, Object> fields(T entity) {
		Assert.notNull(entity);
		
		Map<String, Object> fields = new LinkedHashMap<>();
		Stream.of(entity.getClass().getDeclaredFields()).forEach((e) -> {
			try {
				e.setAccessible(true);
				
				fields.put(e.getName(), e.get(entity));
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		});
		
		return fields;
	}
}
