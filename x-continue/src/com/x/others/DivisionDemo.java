package com.x.others;

import java.util.ArrayList;
import java.util.List;

public class DivisionDemo {

	public static void main(String[] args) {
		List<Double> list = new ArrayList<Double>(); 
		double t = 0.0;
		for (int i = 0; i < 20; i++) {
			list.add(t += 0.1);
			System.out.println("curr double:" + t + " , division :" + t / 3);
		}
		
		System.out.println((double)(200-110)/100);
		
	}
}
