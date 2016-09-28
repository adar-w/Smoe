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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
		
		return fieldType.cast(entity.getClass().getDeclaredMethod(methodName).invoke(entity));
	}
	
	public static <E, T> T fieldVal(E entity, String fieldName, Class<T> fieldType) throws Exception {
		Assert.notNull(entity);
		Assert.notNull(fieldName);
		
		return fieldType.cast(entity.getClass().getField(fieldName).get(entity));
	}

	public static <E, T> T fieldVal(Class<E> clazz, String fieldName, Class<T> fieldType) throws Exception {
		Assert.notNull(clazz);
		Assert.notNull(fieldName);
		
		return fieldType.cast(clazz.getField(fieldName).get(null));
	}
	
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
