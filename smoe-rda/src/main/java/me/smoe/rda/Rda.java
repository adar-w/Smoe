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
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rda {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private static boolean init;

	private static String url;

	private static String un;

	private static String pw;

	public static void to(String url, String un, String pw) {
		loadDriver();

		Rda.url = url;
		Rda.un = un;
		Rda.pw = pw;
		
		init = true;
	}

	private static void loadDriver() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("No driver: %s.", DRIVER));
		}
	}

	private static Connection connection() throws Exception {
		return DriverManager.getConnection(url, un, pw);
	}
	
	public static <T> RdaBuilder<T> at(Class<T> clazz) {
		return new RdaBuilder<>(clazz);
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

		public void findOne(Object id) throws SQLException, Exception {
			PreparedStatement prepareStatement = connection().prepareStatement(String.format("SELECT * FROM %s WHERE id = ?", clazz.getSimpleName().toLowerCase()));
			prepareStatement.setObject(1, id);
			ResultSet rs = prepareStatement.executeQuery();
			
			int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= col; i++) {
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8)) {
						System.out.print("\t");
					}
				}
			}
		}
	}
}
