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
package me.smoe.rda.mapping;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import me.smoe.mda.Assert;
import me.smoe.mda.Clazzs;
import me.smoe.rda.exception.RdaException;

public class Mapping {

	public static <T> T to(ResultSet resultSet, Class<T> clazz) throws Exception {
		Assert.notNull(resultSet);
		Assert.notNull(clazz);

		if (resultSet.next()) {
			T instance = clazz.newInstance();
			Clazzs.fields(clazz).forEach(field -> {
				try {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, clazz);
					Object fieldValue = resultSet.getObject(field, propertyDescriptor.getPropertyType());
					propertyDescriptor.getWriteMethod().invoke(instance, fieldValue);
				} catch (Exception e) {
					throw new RdaException(e);
				}
			});
			
			return instance;
		} else {
			return null;
		}
	}

	public static <T> List<T> tos(ResultSet resultSet, Class<T> clazz) throws Exception {
		Assert.notNull(resultSet);
		Assert.notNull(clazz);
		
		List<T> instances = new ArrayList<>();
		while (resultSet.next()) {
			T instance = clazz.newInstance();
			Clazzs.fields(clazz).forEach(field -> {
				try {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, clazz);
					Object fieldValue = resultSet.getObject(field, propertyDescriptor.getPropertyType());
					propertyDescriptor.getWriteMethod().invoke(instance, fieldValue);
				} catch (Exception e) {
					throw new RdaException(e);
				}
			});
			
			instances.add(instance);
		}
		
		return instances;
	}
}
