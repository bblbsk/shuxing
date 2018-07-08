package com.x.others;
public class MainActivity {  
  
    private A a2 = new A();  
  
    public static void main(String[] args) {
    	MainActivity ma = new MainActivity();
        ma.test1();  
        ma.test2(ma.a2);  
	}
    
    
    private void test1() {  
        final A a = new A();  
        new Thread() {  
  
            @Override  
            public void run() {  
                a.a ++;  
            }  
  
        }.start();  
    }  
  
    private void test2(final A a1) {  
  
        t2 = new Thread() {  
            @Override  
            public void run() {  
                a1.a ++;  
            }  
        };  
        t2.start();  
    }  
  
    private static Thread t2;  
  
    private class A {  
        public int a =0;  
  
    }  
}