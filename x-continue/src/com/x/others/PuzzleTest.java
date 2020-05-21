package com.x.others;

import java.math.BigDecimal;

public class PuzzleTest {

	public static void main(String[] args) {
		PuzzleTest test = new PuzzleTest();
		// 1����
		test.puzzle1(-1);
		test.puzzle1(1);
		// 2����
		test.puzzle2();
		// 3����
		test.puzzle3();
		// 7����
		test.puzzle7();
		// 8����
		test.puzzle8();
		
	}

	/**
	 *
	 * @Title: puzzle1
	 * @Description: ��żУ��
	 * @throws
	 */
	public void puzzle1(int i) {
//		System.out.println(i % 2 == 1);
		// �Ż�
		System.out.println(i % 2 != 0);
	}

	/**
	 *
	 * @Title: puzzle2
	 * @Description: double�ļ���
	 * @throws
	 */
	public void puzzle2() {
		// return 2.0 - 1.10;
		// �Ż�
		System.out.println(new BigDecimal("2.0").subtract(new BigDecimal("1.10")).doubleValue());
	}

	/**
	  *
	  * @Title: puzzle3
	  * @Description: ����������
	  * @throws
	  */
	public void puzzle3() {
		System.out.println((24 * 60 * 60 * 1000 * 1000) / (24 * 60 * 60 * 1000));
		// �Ľ�
		System.out.println((24L * 60 * 60 * 1000 * 1000) / (24L * 60 * 60 * 1000));
	}
	
	/**
	  *
	  * @Title: puzzle4
	  * @Description: �ڶ���long����ʱ��ʹ��L������l�����Ļ��ң���Сдû������
	  * @throws
	  */
	public void puzzle4(){
		System.out.println(12345+5432l); 
		System.out.println(12345+5432L); 
	}
	
	/**
	  *
	  * @Title: puzzle7
	  * @Description: ��������������ֵ
	  * @throws
	  */
	public void puzzle7(){
		//1�����õ���������������ֵ���򵥵ķ�����
		int x = 5, y = 10; // ������������
		int temp = x; // ���������ʱ����temp����ȡxֵ
		x = y; // ��y��ֵ����x
		y = temp; // Ȼ�����ʱ����tempֵ����y
		System.out.println("x=" + x + "y=" + y);
		
		//2�����������������Ȼ������ķ�ʽ�������ݽ���,�׶�������� x �� y ����ֵ����Ļ������� int ��ֵ����ʧ����
		x = x + y ;
		y = x - y;
		x = x - y;
		System.out.println("x=" + x + "y=" + y);
		//3������λ����ķ�ʽ�������ݵĽ��������õ�˼��ԭ���ǣ�һ�������ͬһ�������Σ���������Ǹ��������Ҳ��ᳬ��int��Χ
		x = x^y;
		y = x^y;
		x = x^y;
		System.out.println("x=" + x + "y=" + y);
		
		// λ����
		// ����( << )
		System.out.println(5<<2);//���н����20
		// ����( >> ) 
		System.out.println(5>>2);//���н����1 
		// �޷�������( >>> )
		System.out.println(5>>3);//�����0  
		System.out.println(-5>>3);//�����-1  
		System.out.println(-5>>>3);//�����536870911  
		
//		5����ɶ����ƣ� 0000 0000 0000 0000 0000 0000 0000 0101
//		5����3λ����Ϊ0��0�Ķ�����Ϊ�� 0000 0000 0000 0000 0000 0000 0000 0000        // (��0���в�λ)
//		-5����ɶ����ƣ� 1111 1111 1111 1111 1111 1111 1111 1011
//		-5����3λ����Ϊ-1��-1�Ķ�����Ϊ�� 
//		1111 1111 1111 1111 1111 1111 1111 1111   // (��1���в�λ)
//		-5�޷�������3λ��Ľ�� 536870911 ����ɶ����ƣ� 
//		0001 1111 1111 1111 1111 1111 1111 1111   // (��0���в�λ)
//		ͨ������ת���ɶ����ƺ����ǿ��Է��֣��������ƣ���λ��0�����������ƣ���λ��1����������ʹ���޷�������ʱ����0���в�λ(��Ȼ��Ȼ�ģ����ɸ��������������)
//		ע�⣺����������˵�������ƣ���λ��λ��������������߸������ƣ���λ������0����(���в���)
		
		// λ��( & ) ��һ���������ĵĵ�nλ�ڵڶ����������ĵ�nλ�������1����ô����ĵ�nΪҲΪ1������Ϊ0
		System.out.println(5 & 3);//���Ϊ1  
		// λ��( | ) ��һ���������ĵĵ�nλ�ڵڶ����������ĵ�nλ ֻҪ��һ����1����ô����ĵ�nΪҲΪ1������Ϊ0
		System.out.println(5 | 3);//���Ϊ7  
		// λ���( ^ ) ��һ���������ĵĵ�nλ�ڵڶ����������ĵ�nλ �෴����ô����ĵ�nΪҲΪ1������Ϊ0
		System.out.println(5 ^ 3);//���Ϊ6  
		// λ��( ~ ) λ����һԪ���������������ĵ�nλΪ1����ô����ĵ�nλΪ0����֮��
		System.out.println(~5);//���Ϊ-6  
		
	}
	
	/**
	  * @Title: puzzle8
	  * @Description: �������ʽ����������
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
