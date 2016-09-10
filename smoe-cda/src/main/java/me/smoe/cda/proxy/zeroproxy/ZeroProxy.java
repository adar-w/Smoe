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
package me.smoe.cda.proxy.zeroproxy;

import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import me.smoe.cda.core.Cda;
import me.smoe.cda.proxy.CdaProxy;

public class ZeroProxy implements CdaProxy {
	
	// ZeroProxy 动态切换IP, 需要请联系adar-w@outlook.com
	private static final String CENTER_NODE = "http://175.55.84.120:16227/api/proxy/node";
	
	private static final String CODE_SUCCESS = "0";
	
	private static final int RETRY_TIME = 3;

	private static final int RETRY_SLEEP_SECOND = 10;
	
	public static void main(String[] args) throws Exception {
		System.out.println(proxy());
	}
	
	/**
	 * 获取代理节点
	 */
	public static ZeroProxy proxy() throws Exception {
		for (int i = 0; i < RETRY_TIME; i++) {
			try {
				return proxy0();
			} catch (ZeroProxyException e) {
				System.out.println(String.format("[ZeroProxy] Warn: %s try again.", e.getCode().getMsg()));

				TimeUnit.SECONDS.sleep(RETRY_SLEEP_SECOND);
				continue;
			} catch (Exception e) {
				throw new ZeroProxyException(Code.NODE_OTHER, e.getMessage(), e);
			}
		}
		
		return proxy0();
	}
	
	private static ZeroProxy proxy0() throws Exception {
		String proxyData = Cda.connect(CENTER_NODE)
	   			   			  .header("dsb_client_key", "Duoshoubang")
	   			   			  .text();

		return parseProxyData(proxyData);
	}
	
	private static ZeroProxy parseProxyData(String proxyData) throws Exception {
		JSONObject proxyJson = JSON.parseObject(proxyData);
		
		String code = proxyJson.getString("status_code");
		if (!CODE_SUCCESS.equals(code)) {
			throw new ZeroProxyException(Code.NODE_FAIL, String.format("[ZeroProxy] 代理节点获取失败, Msg: %s", proxyJson.getString("status_msg")));
		}
		
		if (proxyJson.getJSONArray("nodes").isEmpty()) {
			throw new ZeroProxyException(Code.NODE_NONE, "[ZeroProxy] 无可用代理节点");
		}
		
		JSONObject node = proxyJson.getJSONArray("nodes").getJSONObject(0);
		
		return new ZeroProxy(node.getString("ip"), Integer.valueOf(node.getString("port")), node.getString("username"), node.getString("password"));
	}
	
	public enum Code {

		NODE_NONE(1, "无可用代理节点"),

		NODE_FAIL(0, "代理节点获取失败"),

		NODE_OTHER(0, "代理节点获取失败");

		private int value;

		private String msg;

		private Code(int value, String msg) {
			this.value = value;
			this.msg = msg;
		}

		public static Code getCode(int value) {
			for (Code e : values()) {
				if (e.getValue() == value) {
					return e;
				}
			}
			return null;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
	
	private String ip;
	
	private int port;
	
	private String username;
	
	private String password;

	public ZeroProxy(String ip, int port, String username, String password) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	@Override
	public String proxyIp() {
		return ip;
	}

	@Override
	public int proxyPort() {
		return port;
	}

	@Override
	public String proxyUsername() {
		return username;
	}

	@Override
	public String proxyPassword() {
		return password;
	}
}
