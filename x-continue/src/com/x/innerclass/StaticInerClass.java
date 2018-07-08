package com.x.innerclass;


/**
  * @ClassName: StaticInerClass
  * @Description: 静态内部类
  * @author-csx
  * @date 2017年11月16日 下午1:16:44
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
