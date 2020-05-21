package com.x.proxy.inter.impl;

import com.x.proxy.inter.DupSubject;
import com.x.proxy.inter.Subject;

public class RealSubject implements Subject, DupSubject {

	@Override
	public void speakAgain() {
		System.out.println("DupSubject speakAgain");
	}

	@Override
	public void speak() {
		 System.out.println("Subject speak");
	}
}