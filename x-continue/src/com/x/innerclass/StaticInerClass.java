package com.x.innerclass;


/**
  * @ClassName: StaticInerClass
  * @Description: ��̬�ڲ���
  * @author-csx
  * @date 2017��11��16�� ����1:16:44
  *
  */
public class StaticInerClass {

	static class Child{
		
	}
	
	public static void main(String[] args) {
		Child c0 = new Child();
		StaticInerClass.Child c = new StaticInerClass.Child();
	}
}
