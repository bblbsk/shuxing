package com.x.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.x.proxy.inter.Subject;
import com.x.proxy.inter.impl.RealSubject;

public class JDKProxy implements InvocationHandler{

	private Object realSubject;
	
	public Object getProxyOject(Object realSubject){
		this.realSubject = realSubject;
		return Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		System.out.println("123");
		
		Object invoke = method.invoke(realSubject, args);
		
		System.out.println("321");

		return invoke;
	}

	
	public static void main(String[] args) {
		JDKProxy jdkProxy = new JDKProxy();
		Subject proxyOject = (Subject)jdkProxy.getProxyOject(new RealSubject());
		
		proxyOject.speak();
		
		System.out.println("end............");
	}
}
