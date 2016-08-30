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