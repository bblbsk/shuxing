package com.x.innerclass;

/**
  * @ClassName: LocalInnerClass
  * @Description: 局部内部类
  * @author-csx
  * @date 2017年11月16日 下午1:36:36
  *
  */
public class LocalInnerClass {
	
	private String name = "class";

	public String getName(){
		
		final String name = "method";
		
		class LocalInner{
			
			public String getName(){
				return name;
			}
		}
		
		LocalInner inner = new LocalInner();
		
		return inner.getName();
	}
	
	public static void main(String[] args) {
		System.out.println(new LocalInnerClass().getName());
	}
}
