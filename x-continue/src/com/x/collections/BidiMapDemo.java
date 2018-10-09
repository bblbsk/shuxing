package com.x.collections;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;


/**
 * @Description:双向map
 * @Author：daojia
 * @CreateTime：2018年9月30日下午2:36:15
 * @version v1.0
 */
public class BidiMapDemo {

	public static void main(String[] args) {
		
		BidiMap<Integer, String> map = new TreeBidiMap<Integer, String>();
		
		/**
		 * 1、key, value 均不可以为 null 或 ""
		 * 2、key, value 均不可重复，否则数据会异常
		 * 3、适合做两类常量之间的转换
		 */
		
		
		map.put(1, "A");
		map.put(1, "B");
		BidiMap<String, Integer> bidiMap = map.inverseBidiMap();
		
		
		System.out.println(map.get(1));
		System.out.println(bidiMap.get("A"));
		
		
	}
}
