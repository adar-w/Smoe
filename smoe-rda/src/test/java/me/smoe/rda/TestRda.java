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

import me.smoe.rda.core.Rda;

public class TestRda {
	
	private static final String URL = "jdbc:mysql://localhost:3306/adar";
	
	static {
		Rda.to(URL, "root", "adar");
	}

	public static void main(String[] args) throws Exception {
		CAM cam = new CAM();
		cam.setId(1L);
		cam.setName("hah");
		
//		Rda.at(CAM.class).save(cam);
		Rda.save(cam);
	}
}
