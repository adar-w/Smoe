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
