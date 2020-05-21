package com.x.others;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TryTest {

	public static void main(String[] args) {
//		System.out.println(0.3 == 0.1*3);
//		System.out.println(0.4 == 0.1*4);
		
		
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		
		for (int i = 0; i < l.size(); i++) {
			l.remove(i);
		}
		
//		Iterator<Integer> iterator = l.iterator();
//		while(iterator.hasNext()){
//			int i = 1;
//			if ( iterator.next() == i ) {
//				iterator.remove();
//			}
//			iterator.next();
//			iterator.remove();
//		}
		
		System.out.println(l);
		
		int i = 200;
		int t = 200;
		System.out.println(i == t);
	}
}
