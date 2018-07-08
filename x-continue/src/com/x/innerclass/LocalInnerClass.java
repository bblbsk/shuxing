package com.x.innerclass;

/**
  * @ClassName: LocalInnerClass
  * @Description: �ֲ��ڲ���
  * @author-csx
  * @date 2017��11��16�� ����1:36:36
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
