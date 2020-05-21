package com.x.others;

import java.math.BigDecimal;

public class PuzzleTest {

	public static void main(String[] args) {
		PuzzleTest test = new PuzzleTest();
		// 1测试
		test.puzzle1(-1);
		test.puzzle1(1);
		// 2测试
		test.puzzle2();
		// 3测试
		test.puzzle3();
		// 7测试
		test.puzzle7();
		// 8测试
		test.puzzle8();
		
	}

	/**
	 *
	 * @Title: puzzle1
	 * @Description: 奇偶校验
	 * @throws
	 */
	public void puzzle1(int i) {
//		System.out.println(i % 2 == 1);
		// 优化
		System.out.println(i % 2 != 0);
	}

	/**
	 *
	 * @Title: puzzle2
	 * @Description: double的减法
	 * @throws
	 */
	public void puzzle2() {
		// return 2.0 - 1.10;
		// 优化
		System.out.println(new BigDecimal("2.0").subtract(new BigDecimal("1.10")).doubleValue());
	}

	/**
	  *
	  * @Title: puzzle3
	  * @Description: 长整数除法
	  * @throws
	  */
	public void puzzle3() {
		System.out.println((24 * 60 * 60 * 1000 * 1000) / (24 * 60 * 60 * 1000));
		// 改进
		System.out.println((24L * 60 * 60 * 1000 * 1000) / (24L * 60 * 60 * 1000));
	}
	
	/**
	  *
	  * @Title: puzzle4
	  * @Description: 在定义long类型时，使用L，编码l带来的混乱，大小写没有区别
	  * @throws
	  */
	public void puzzle4(){
		System.out.println(12345+5432l); 
		System.out.println(12345+5432L); 
	}
	
	/**
	  *
	  * @Title: puzzle7
	  * @Description: 交换两个变量的值
	  * @throws
	  */
	public void puzzle7(){
		//1、利用第三个变量交换数值，简单的方法。
		int x = 5, y = 10; // 定义两个变量
		int temp = x; // 定义第三临时变量temp并提取x值
		x = y; // 把y的值赋给x
		y = temp; // 然后把临时变量temp值赋给y
		System.out.println("x=" + x + "y=" + y);
		
		//2、可以用两个数求和然后相减的方式进行数据交换,弊端在于如果 x 和 y 的数值过大的话，超出 int 的值会损失精度
		x = x + y ;
		y = x - y;
		x = x - y;
		System.out.println("x=" + x + "y=" + y);
		//3、利用位运算的方式进行数据的交换，利用的思想原理是：一个数异或同一个数两次，结果还是那个数，而且不会超出int范围
		x = x^y;
		y = x^y;
		x = x^y;
		System.out.println("x=" + x + "y=" + y);
		
		// 位运算
		// 左移( << )
		System.out.println(5<<2);//运行结果是20
		// 右移( >> ) 
		System.out.println(5>>2);//运行结果是1 
		// 无符号右移( >>> )
		System.out.println(5>>3);//结果是0  
		System.out.println(-5>>3);//结果是-1  
		System.out.println(-5>>>3);//结果是536870911  
		
//		5换算成二进制： 0000 0000 0000 0000 0000 0000 0000 0101
//		5右移3位后结果为0，0的二进制为： 0000 0000 0000 0000 0000 0000 0000 0000        // (用0进行补位)
//		-5换算成二进制： 1111 1111 1111 1111 1111 1111 1111 1011
//		-5右移3位后结果为-1，-1的二进制为： 
//		1111 1111 1111 1111 1111 1111 1111 1111   // (用1进行补位)
//		-5无符号右移3位后的结果 536870911 换算成二进制： 
//		0001 1111 1111 1111 1111 1111 1111 1111   // (用0进行补位)
//		通过其结果转换成二进制后，我们可以发现，正数右移，高位用0补，负数右移，高位用1补，当负数使用无符号右移时，用0进行部位(自然而然的，就由负数变成了正数了)
//		注意：笔者在这里说的是右移，高位补位的情况。正数或者负数左移，低位都是用0补。(自行测试)
		
		// 位与( & ) 第一个操作数的的第n位于第二个操作数的第n位如果都是1，那么结果的第n为也为1，否则为0
		System.out.println(5 & 3);//结果为1  
		// 位或( | ) 第一个操作数的的第n位于第二个操作数的第n位 只要有一个是1，那么结果的第n为也为1，否则为0
		System.out.println(5 | 3);//结果为7  
		// 位异或( ^ ) 第一个操作数的的第n位于第二个操作数的第n位 相反，那么结果的第n为也为1，否则为0
		System.out.println(5 ^ 3);//结果为6  
		// 位非( ~ ) 位非是一元操作符，操作数的第n位为1，那么结果的第n位为0，反之。
		System.out.println(~5);//结果为-6  
		
	}
	
	/**
	  * @Title: puzzle8
	  * @Description: 条件表达式的类型问题
	  * @throws
	  */
	public void puzzle8(){
		char x = 'X';
		String info = "info";
		int i = 0;
		System.out.println(x);
		System.out.println(false ? 0 : x);
		System.out.println(false ? i : x);
		System.out.println(false ? i : info);
	}

}
