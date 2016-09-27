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

import org.junit.Test;

import me.smoe.mda.Assert;

public class TestRda {
	
	private static final String URL = "jdbc:mysql://localhost:3306/adar";
	
	static {
		Rda.to(URL, "root", "adar");
	}
	
	@Test
	public void save() {
		CAM cam = new CAM();
		cam.setId(4L);
		cam.setName("hah");
		
		Rda.at(CAM.class).save(cam);
	}

	@Test
	public void findOne() {
		Long id = 630005L;
		
		CAM cam = Rda.at(CAM.class).findOne(id);
		
		Assert.isTrue(id.equals(cam.getId()));
	}
	
	@Test
	public void findAll() {
		for (CAM cam : Rda.at(CAM.class).findAll()) {
			System.out.println(cam.getId() + " + " + cam.getName());
		}
	}
}
