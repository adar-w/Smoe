/**
 * Copyright (c) 2016, adar.w (adar.w@outlook.com) 
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
package me.smoe.rda.handler.impl;

import java.util.List;

import me.smoe.rda.JDBC;
import me.smoe.rda.handler.sqlbuilder.SQLData;

public class RdaBuilder {
	
	private SQLData sqlData;
	
	RdaBuilder(String sql, Object... params) {
		this.sqlData = SQLData.be(sql, params);
	}
	
	public void exec() {
		JDBC.execute(sqlData);
	}

	public <T> T get(Class<T> returnClazz) {
		return JDBC.get(sqlData, returnClazz);
	}
	
	public int getInt() {
		return JDBC.get(sqlData, Integer.class);
	}

	public Integer getInteger() {
		return JDBC.get(sqlData, Integer.class);
	}
	
	public Long getLong() {
		return JDBC.get(sqlData, Long.class);
	}

	public String getString() {
		return JDBC.get(sqlData, String.class);
	}

	public <T> T mapping(Class<T> returnClazz) {
		return JDBC.query(sqlData, returnClazz);
	}

	public <T> List<T> mappings(Class<T> returnClazz) {
		return JDBC.querys(sqlData, returnClazz);
	}
}
