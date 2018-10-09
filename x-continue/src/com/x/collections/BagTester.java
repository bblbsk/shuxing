package com.x.collections;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class BagTester {
	public static void main(String[] args) {
		Bag<String> bag = new HashBag<>();

		/**
		 * 1、可以统计放入bag中元素的个数，并增加或减少
		 */
		
		bag.add(null, 2);
		
		// add "a" two times to the bag.
		bag.add("a", 2);

		// add "b" one time to the bag.
		bag.add("b");
		bag.add("b");

		// add "c" one time to the bag.
		bag.add("c");

		// add "d" three times to the bag.
		bag.add("d", 3);

		// get the count of "d" present in bag.
		System.out.println("d is present " + bag.getCount("d") + " times.");
		System.out.println("bag: " + bag);

		// get the set of unique values from the bag
		System.out.println("Unique Set: " + bag.uniqueSet());

		// remove 2 occurrences of "d" from the bag
		bag.remove("d", 2);
		System.out.println("2 occurences of d removed from bag: " + bag);
		System.out.println("d is present " + bag.getCount("d") + " times.");
		System.out.println("bag: " + bag);
		System.out.println("Unique Set: " + bag.uniqueSet());

	}
}
