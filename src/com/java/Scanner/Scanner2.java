package com.java.Scanner;

import java.util.Scanner;

public class Scanner2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.println("ÇëÊäÈë×Ö·û´®¡£¡£¡£");
		while(true){
			String line=scanner.nextLine();
			if(line.equals("exit"))
				{
					scanner.close();
					break;
				}
			System.out.println(">>>"+line);
		}
		
		Scanner s = new Scanner("123 asdf sd 45 789 sdf asdfl,sdf.sdfl,asdf    ......asdfkl    las"); 
         s.useDelimiter(" |,|\\."); 
         while (s.hasNext()) { 
                 System.out.println(s.next());
         } 
	}

}
