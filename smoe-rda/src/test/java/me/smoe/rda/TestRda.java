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

import java.util.stream.IntStream;

import org.junit.Test;

import me.smoe.mda.Assert;

public class TestRda {
	
	private static final String URL = "jdbc:mysql://localhost:3306/adar";
	
	static {
		Rda.to(URL, "root", "adar");
	}
	
	/**
	 * Init
	 */
	@Test
	public void init() {
		IntStream.range(1, 10).forEach(i -> {
			Rdo rdo = new Rdo();
			rdo.setId(new Long(i));
			rdo.setName("rda-" + i);
			
			Rda.at(Rdo.class).save(rdo);
		});
	}
	
	@Test
	public void sql() {
		System.out.println(Rda.build("select count(*) from rdo").getLong());
		
		System.out.println(Rda.build("select name from rdo where id = ?", 1).getString());

		System.out.println(Rda.build("select * from rdo where id = ?", 2).mapping(Rdo.class));

		System.out.println(Rda.build("select * from rdo").mappings(Rdo.class));
	}
	
	@Test
	public void save() {
		Rdo rdo = new Rdo();
		rdo.setId(17L);
		rdo.setName("hah");
		
		Rda.at(Rdo.class).save(rdo);
	}

	@Test
	public void modify() {
		Rdo rdo = Rda.at(Rdo.class).findOne(2L);
		rdo.setName("modify");
		
		Rda.at(Rdo.class).modify(rdo);
	}

	@Test
	public void findOne() {
		Long id = 630005L;
		
		Rdo rdo = Rda.at(Rdo.class).findOne(id);
		
		Assert.isTrue(id.equals(rdo.getId()));
	}
	
	@Test
	public void findAll() {
		for (Rdo rdo : Rda.at(Rdo.class).findAll()) {
			System.out.println(rdo.getId() + " + " + rdo.getName());
		}
	}
	
	@Test
	public void findMatch() {
		System.out.println(Rda.at(Rdo.class).limit(2).find(new Rdo("hah")));
	}

	@Test
	public void delete() {
		Rda.at(Rdo.class).delete(1L);
	}
	
	@Test
	public void deleteAll() {
		Rda.at(Rdo.class).deleteAll();
	}
	
	@Test
	public void count() {
		System.out.println(Rda.at(Rdo.class).count());
	}
	
	@Test
	public void countMatch() {
		System.out.println(Rda.at(Rdo.class).count(new Rdo("hehe")));
	}
	
	@Test
	public void exists() {
		System.out.println(Rda.at(Rdo.class).exists(1L));
	}
}
