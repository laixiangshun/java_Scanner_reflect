package com.java.Scanner;

import java.util.Scanner;

public class scanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start....");
		Scanner scanner=new Scanner(System.in);
		int number=(int)(Math.random()*100);
		System.out.println(number);
		int count=10;
		for(int i=0;i<count;i++){
			System.out.println("only have "+(count-i)+" times");
			int in=scanner.nextInt();
			if(in==number){
				System.out.println("right only use "+(i+1)+" times");
				scanner.close();
				break;
			}
			if(i==count-1){
				scanner.close();
				System.out.println("��һ�ζ�û�жԣ�ֻ������10�Σ���������");
				break;
			}
		}
	}

}
