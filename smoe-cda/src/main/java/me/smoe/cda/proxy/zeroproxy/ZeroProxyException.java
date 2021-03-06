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

import me.smoe.cda.proxy.zeroproxy.ZeroProxy.Code;

public class ZeroProxyException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private Code code;
	
	public ZeroProxyException(Code code, String message) {
		super(message);
		
		this.code = code;
	}

	public ZeroProxyException(Code code, String message, Throwable cause) {
		super(message, cause);
		
		this.code = code;
	}

	public Code getCode() {
		return code;
	}
}
