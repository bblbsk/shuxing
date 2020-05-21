package com.x.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy2 implements InvocationHandler{

	private Object proxied;
	
	private void setProxied(Object proxied){
		this.proxied = proxied;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("begin...........transaction");
		
		
		
		System.out.println("commit...........transaction");
		return null;
	}

	
}
