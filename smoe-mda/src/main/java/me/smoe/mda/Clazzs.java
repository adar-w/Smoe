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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Clazzs {
	
	public static <T, A extends Annotation> boolean hasAnnotation(Class<T> clazz, Class<A> annotation) {
		Assert.notNull(clazz);
		Assert.notNull(annotation);
		
		return clazz.getAnnotation(annotation) != null;
	}
	
	public static <T, A extends Annotation> String annotationVal(Class<T> clazz, Class<A> annotation) throws Exception {
		Assert.notNull(clazz);
		Assert.notNull(annotation);
		
		return annotationVal(clazz.getAnnotation(annotation), "value");
	}

	public static <A extends Annotation> String annotationVal(A annotation) throws Exception {
		Assert.notNull(annotation);

		return annotationVal(annotation, "value");
	}

	public static <A extends Annotation> String annotationVal(A annotation, String fieldName) throws Exception {
		Assert.notNull(annotation);
		Assert.notNull(fieldName);
		
		return methodInvoke(annotation, fieldName, String.class);
	}
	
	public static <E, T> T methodInvoke(E entity, String methodName, Class<T> fieldType) throws Exception {
		Assert.notNull(entity);
		Assert.notNull(methodName);
		Assert.notNull(fieldType);
		
		Method method = entity.getClass().getDeclaredMethod(methodName);
		method.setAccessible(true);
		
		return fieldType.cast(method.invoke(entity));
	}
	
	public static <T> boolean hasField(Class<T> clazz, String fieldName) throws Exception {
		Assert.notNull(clazz);
		
		return fields(clazz).contains(fieldName);
	}
	
	public static <E, T> T fieldVal(E entity, String fieldName, Class<T> fieldType) throws Exception {
		Assert.notNull(entity);
		Assert.notNull(fieldName);
		
		Field field = entity.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		
		return fieldType.cast(field.get(entity));
	}

	public static <E, T> T fieldVal(Class<E> clazz, String fieldName, Class<T> fieldType) throws Exception {
		Assert.notNull(clazz);
		Assert.notNull(fieldName);
		
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		
		return fieldType.cast(field.get(null));
	}
	
	public static <T> List<String> fields(Class<T> clazz) {
		Assert.notNull(clazz);
		
		List<String> fields = new ArrayList<>();
		Stream.of(clazz.getDeclaredFields()).forEach((e) -> {
			fields.add(e.getName());
		});
		
		return fields;
	}
	
	public static <T> Map<String, Object> staticFields(Class<T> clazz) {
		Assert.notNull(clazz);
		
		Map<String, Object> fields = new LinkedHashMap<>();
		Stream.of(clazz.getDeclaredFields()).forEach((e) -> {
			try {
				e.setAccessible(true);
				
				Object value = e.get(clazz);
				
				fields.put(e.getName(), value);
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		});
		
		return fields;
	}
	
	public static <T> Map<String, Object> fields(T entity) {
		return fields(entity, true);
	}
	
	public static <T> Map<String, Object> fields(T entity, boolean allowNull, String... exclude) {
		Assert.notNull(entity);
		Assert.notNull(entity);
		
		Set<String> excludes = exclude == null ? Collections.emptySet() : new HashSet<>(Arrays.asList(exclude));
		
		Map<String, Object> fields = new LinkedHashMap<>();
		Stream.of(entity.getClass().getDeclaredFields()).forEach((e) -> {
			try {
				if (excludes.contains(e.getName())) {
					return;
				}
				
				e.setAccessible(true);
				
				Object value = e.get(entity);
				if (value == null && !allowNull) {
					return;
				}
				
				fields.put(e.getName(), value);
			} catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		});
		
		return fields;
	}
}
