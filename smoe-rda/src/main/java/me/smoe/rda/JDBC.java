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
package me.smoe.rda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.smoe.rda.exception.RdaException;
import me.smoe.rda.handler.sqlbuilder.SQLData;
import me.smoe.rda.mapping.Mapping;

public final class JDBC {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBC.class); 
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private static String url;

	private static String un;

	private static String pw;
	
	private static DataSource ds;
	
	private static boolean useDS;
	
	static void init(DataSource ds) {
		loadDriver();
		
		JDBC.ds = ds;
		
		useDS = true;
	}

	static void init(String url, String un, String pw) {
		loadDriver();

		JDBC.url = url;
		JDBC.un = un;
		JDBC.pw = pw;
	}

	private static void loadDriver() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("No driver: %s.", DRIVER));
		}
	}

	public static Connection connection() throws Exception {
		return useDS ? ds.getConnection() : DriverManager.getConnection(url, un, pw); 
	}
	
	public static int execute(SQLData data) {
		try (Connection connection = connection()) {
			return statement(connection, data.getSql(), data.getParams()).executeUpdate();
		} catch (Exception e) {
			throw new RdaException(e);
		}
	}
	
	public static <T> T query(SQLData data, Class<T> clazz) {
		try (Connection connection = connection()) {
			return Mapping.to(statement(connection, data.getSql(), data.getParams()).executeQuery(), clazz);
		} catch (Exception e) {
			throw new RdaException(e);
		}
	}

	public static <T> List<T> querys(SQLData data, Class<T> clazz) {
		try (Connection connection = connection()) {
			return Mapping.tos(statement(connection, data.getSql(), data.getParams()).executeQuery(), clazz);
		} catch (Exception e) {
			throw new RdaException(e);
		}
	}
	
	public static <T> T get(SQLData data, Class<T> clazz) {
		try (Connection connection = connection()) {
			return Mapping.get(statement(connection, data.getSql(), data.getParams()).executeQuery(), clazz);
		} catch (Exception e) {
			throw new RdaException(e);
		}
	}
	
	private static PreparedStatement statement(Connection connection, String sql, Collection<Object> params) throws Exception {
		LOGGER.info(String.format("[Rda] SQL: %s Params: %s", sql, params));
		
		PreparedStatement prepareStatement = connection.prepareStatement(sql);

		int index = 1;
		for (Object param : params) {
			prepareStatement.setObject(index, param);
			index++;
		}
		
		return prepareStatement;
	}
}
