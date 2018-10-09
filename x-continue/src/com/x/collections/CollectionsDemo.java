package com.x.collections;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class CollectionsDemo {

	public static void main(String[] args) {

		// checking inclusion
		List<String> list1 = Arrays.asList("A", "A", "A", "C", "B", "B");
		List<String> list2 = Arrays.asList("A", "A", "B", "B");

		// 是否子集
		System.out.println("List 1: " + list1);
		System.out.println("List 2: " + list2);
		System.out.println("Is List 2 contained in List 1: "
				+ CollectionUtils.isSubCollection(list2, list1));

		// 相交
		System.out.println("Commons Objects of List 1 and List 2: "
				+ CollectionUtils.intersection(list1, list2));

		// 差集
		System.out.println("List 1 - List 2: "
				+ CollectionUtils.subtract(list1, list2));

		// 合集
		System.out.println("Union of List 1 and List 2: "
				+ CollectionUtils.union(list1, list2));

	}
}
