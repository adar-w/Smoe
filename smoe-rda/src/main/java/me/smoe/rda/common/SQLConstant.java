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
package me.smoe.rda.common;

public interface SQLConstant {
	
	String EMPTY = "";
	
	String BLANK = " ";
	
	String COMMA = ",";
	
	String PLACEHOLDER = "?";
	
	String BRACKETS_LEF = "(";
	
	String BRACKETS_RIG = ")";

	String INSERT = "INSERT";

	String DELETE = "DELETE";
	
	String UPDATE = "UPDATE";
	
	String SELECT = "SELECT";
	
	String SM_INSERT = "INSERT INTO %s (%s) VALUES (%s)";
	
	String SM_FINDONE = "SELECT %s FROM %s WHERE id = %s";
	
	String SM_FINDALL = "SELECT * FROM %s";
	
	String CB = SQLConstant.COMMA + SQLConstant.BLANK;
	
	String PCB = SQLConstant.PLACEHOLDER + SQLConstant.COMMA + SQLConstant.BLANK;
	
}
