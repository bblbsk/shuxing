package com.shuxing.serializable.stringutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class StringDemo {

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("1001");
		l.add("1002");

		System.out.println(StringUtils.joinWith(",", l));
		System.out.println(StringUtils.join(l.toArray(new String[0]), ","));
		System.out.println("LR123".substring(2));

		System.out.println("45TS7LqW3xBINY3qEfA6Dgw".length());

		Map<String, String> map = new HashMap<>();
		map.put("1","1");
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			System.out.println(next);
			iterator.remove();
		}
		
		System.out.println("iterator:" + iterator.hasNext());
		System.out.println(map.size());
	}
}
