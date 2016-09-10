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
package me.smoe.cdar;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.smoe.cda.core.Cda;

public class CdarFetchTest {
	
	private static final String PATH_BASE_DIR = "/Users/adar-w/Work/tmp/";

	public static void main(String[] args) throws Exception {
		fetch("http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1466438890020_R&pv=&ic=0&nc=1&z=&se=1&showtab=1&fb=0&width=&height=&face=0&istype=2&itg=0&ie=utf-8&word=王祖贤");
	}

	private static void fetch(String url) throws Exception {
		String fullText = Cda.connect(url).text();
		
		Pattern pattern = Pattern.compile("(http://[^\"]*.jpg)\"");
		Matcher matcher = pattern.matcher(fullText);
		while (matcher.find()) {
			download(matcher.group(1));
		}

		pattern = Pattern.compile("(https://[^\"]*.jpg)\"");
		matcher = pattern.matcher(fullText);
		while (matcher.find()) {
			download(matcher.group(1));
		}
	}
	
	private static void download(String url) {
		System.out.println(String.format("Download... %s", url));
		
		try {
			Cda.connect(url).download(PATH_BASE_DIR + new Date().getTime() + ".jpg");
		} catch (Exception e) {
		}
	}
}
