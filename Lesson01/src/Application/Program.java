package Application;

import java.util.Scanner;

import Entities.Emoticons;

public class Program {

	public static void main(String[] args) {
		System.out.println("|------------------------------|");
		System.out.println("|                              |");
		System.out.println("|          NordesTeam          |");
		System.out.println("|                              |");
		System.out.println("|------------------------------|");
		Scanner sc = new Scanner(System.in);
		System.out.println();
		Emoticons emoticons = new Emoticons();
		String ans = sc.nextLine();
		emoticons.checkEmoticons(ans);
		
		System.out.println(emoticons.checkEmoticons(ans));
		
		
		sc.close();
	}

}
