package com.x.threads;

public class ThreadLocalDemo {

	static ThreadLocal<LocalTestModel> local = new ThreadLocal<LocalTestModel>();
	
	public static void main(String[] args) {
		new Thread(){
			public void run() {
//				LocalTestModel model = new LocalTestModel();
//				model.setCode("test code");
//				model.setId(123);
//				local.set(model);
				
				LocalTestModel.getThreadInstance().setId(123);
				LocalTestModel.getThreadInstance().setCode("code1");
				
				A a = new A();
				System.out.println("curr thread....:" + LocalTestModel.getThreadInstance().getId());
				System.out.println("curr thread....:" + a.get().getCode());
				a.run();
				a.start();
				
				System.out.println("----------------分割线--------------");
				B b = new B();
				LocalTestModel.getThreadInstance().setCode("test code 2");
				System.out.println("curr thread....:" + b.get().getCode());
				
			};
		}.start();

	}
	
	static class A extends Thread{

		@Override
		public void run() {
			if (LocalTestModel.getThreadInstance() == null) {
				System.out.println(Thread.currentThread().getId() + " ,子线程中没有localthread对象");
			} else {
				System.out.println(Thread.currentThread().getId() + " ,子线程内尝试打印父类的localhost:" + LocalTestModel.getThreadInstance().getCode());
			}
		}
		
		public LocalTestModel get(){
//			return local.get();
			return LocalTestModel.getThreadInstance();
		}
	} 
	
	static class B extends A{

	} 
	
	static class LocalTestModel {
		
		private LocalTestModel(){}
		
		public static LocalTestModel getThreadInstance(){
			LocalTestModel instance = map.get();
			if (instance == null) {
				instance = new LocalTestModel();
				map.set(instance);
			}
			return instance;
		}
		
		private static ThreadLocal<LocalTestModel> map = new ThreadLocal<LocalTestModel>();
		
		private int id;
		private String code;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		
	}
}
