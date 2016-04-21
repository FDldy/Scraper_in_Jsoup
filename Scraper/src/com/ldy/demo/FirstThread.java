package com.ldy.demo;

public class FirstThread extends Thread{

	private int i;
	public void run(){
		for (i=0;i<100;i++){
			System.out.println(getName()+" "+i);
		}
	}
	public static void main(String[] args) {
		Thread.currentThread().setName("SX");
		for (int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
			if (i==20){
				FirstThread FT = new FirstThread();
				FT.setName("SB");
				FT.start();
			}	
		}

	}

}
