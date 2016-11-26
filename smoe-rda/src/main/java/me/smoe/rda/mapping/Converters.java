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
package me.smoe.rda.mapping;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Converters {
	
	private static final Map<Integer, Converter> CONVERTERS = new HashMap<>();
	static {
		register(Types.TIMESTAMP, (obj) -> {
			return new Date(Timestamp.class.cast(obj).getTime());
		});
	}
	
	public static void register(int type, Converter converter) {
		CONVERTERS.put(type, converter);
	}
	
	public static Object Converter(int type, Object dbObj) {
		return CONVERTERS.containsKey(type) ? CONVERTERS.get(type).to(dbObj) : dbObj;
	}
}
