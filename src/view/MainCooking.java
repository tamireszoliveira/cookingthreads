package view;

import controller.cooking;

public class MainCooking {
	public static void main(String[]args) {
		System.out.println("Iniciando...");
		for(int i=0; i<= 5; i++) {
			new cooking(i).start();
		}
	}
}
