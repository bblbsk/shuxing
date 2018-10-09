package com.x.puzzle.nativetype;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:原生类型问题
 * @Author：daojia
 * @CreateTime：2018年10月9日下午4:13:42
 * @version v1.0
 */
public class NativeTypeDemo<T> {

	private final T first;
	private final T second;

	public NativeTypeDemo(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public T first() {
		return first;
	}

	public T second() {
		return second;
	}

	public List<String> stringList() {
		return Arrays.asList(String.valueOf(first), String.valueOf(second));
	}

	public static void main(String[] args) {
		NativeTypeDemo<?> p = new NativeTypeDemo<Object>(23, "skidoo");
		System.out.println(p.first() + " " + p.second());
		for (String s : p.stringList()){
			System.out.print(s + " ");
		}
	}
}
