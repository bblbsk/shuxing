package com.x.innerclass;


/**
  * @ClassName: InternalClass
  * @Description: ��Ա�ڲ���
  * @author-csx
  * @date 2017��11��16�� ����1:16:44
  *
  */
public class InternalClass {

	private String name = "Outter";
	public int age = 40;
	
	private String getName(){
		return name;
	}
	
	class Child{
		
		private String name = "Inner";
		public int age = 20;
		
		
		public String getName(){
			return name;
		}
		
		public String getOutterName(){
			return InternalClass.this.getName();
		}
	}
	
	public static void main(String[] args) {
		Child c = new InternalClass().new Child();
		System.out.println(c.getName());
		System.out.println(c.getOutterName());
	}
}
