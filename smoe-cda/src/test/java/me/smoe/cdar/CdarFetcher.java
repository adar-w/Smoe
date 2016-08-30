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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CdarFetcher {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 10080);
		
		
		Thread.sleep(10000);
		
		OutputStream outputStream = socket.getOutputStream();
		
		outputStream.write("GET / HTTP".getBytes());
		
		outputStream.flush();
		
		
		outputStream.write("/1.1\r\nHost: baidu.com\r\n\r\n".getBytes());
		
		InputStream inputStream = socket.getInputStream();
		
		byte[] buf = new byte[10240];
		inputStream.read(buf, 0, 10240);
		
		socket.close();

		System.out.println(new String(buf));
	}
}