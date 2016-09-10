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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import me.smoe.cda.core.Cda;

public class CdarProxyTest {
	
	private static final String PATH_BASE_DIR = "/Users/adar-w/Work/tmp/";
	
	private static final String API_KEY = "bf51839375f82febca6b07e0e2dd3799";
	
	private static final String API_PATH = "http://apis.baidu.com/txapi/mvtp/meinv";

	public static void main(String[] args) throws Exception {
		String content = Cda.connect(API_PATH)
//							 .proxy(ZeroProxy.proxy())
							 .header("apikey", API_KEY)
						 	 .urlParam("num", "10")
						 	 .text();
		
		JSONArray pics = JSON.parseObject(content).getJSONArray("newslist");
		for (int i = 0; i < pics.size(); i++) {
			JSONObject pic = pics.getJSONObject(i);
			
			System.out.println(String.format("Download... %s", pic.getString("title")));
			
			Cda.connect(pic.getString("picUrl")).download(PATH_BASE_DIR + new Date().getTime() + ".jpg");
		}
	}
}
