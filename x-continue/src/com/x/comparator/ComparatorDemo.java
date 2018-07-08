package com.x.comparator;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {
	
	
	
	public static void main(String[] args) {
		List<St> l1 = new ArrayList<St>();
		List<St> l2 = new ArrayList<St>();
		for (int i = 0; i < 10; i++) {
			St s = new St(i);
			l1.add(s);
		}
		for (int i = 0; i < 15; i++) {
			St s = new St(i);
			l2.add(s);
		}
		
		List<St> l3 = new ArrayList<St>();
		l3.addAll(l1);
		l3.addAll(l2);
		
		final List<Integer> keys = new ArrayList<Integer>();
		Collections.sort(l3, new Comparator(){

			@Override
			public int compare(Object o1, Object o2) {
				St s1 = (St) o1;
				St s2 = (St) o2;
				if (s1.getId() == s2.getId() ) {
					keys.add(s1.getId());
				}
				
				return 0;
			}
			
		});
		
		System.out.println(keys);
	}
	
}

class St{
	int id;

	public St(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
