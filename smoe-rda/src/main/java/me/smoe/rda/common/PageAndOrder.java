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

import java.util.LinkedHashMap;
import java.util.Map;

public class PageAndOrder {

	private int[] limit;
	
	private Map<String, Boolean> orderBy = new LinkedHashMap<>();

	public int[] getLimit() {
		return limit;
	}

	public void setLimit(int[] limit) {
		this.limit = limit;
	}

	public Map<String, Boolean> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Map<String, Boolean> orderBy) {
		this.orderBy = orderBy;
	}
}
