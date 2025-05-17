package StudyTimer;

import java.util.Scanner;

public class Main {
		public static void main(String [] args) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("🌟 Welcome to FocusTimer - Your Study Companion! 🌟");
			System.out.println("Let's turn your goals into achievements, one cycle at a time!");
			System.out.println("-------------------------------------------------------------");
			System.out.print("👋 What's your name, champ : ");
			String name = scanner.nextLine().toUpperCase();
			System.out.print("📚 How many study cycles would you like to complete today \" " +name+ " \":");
			int numOfCycles = scanner.nextInt();
			System.out.print("⏰ How many minutes should each study cycle last \" "+name+" \" :");
			int durationOfEachCycle = scanner.nextInt();
			System.out.print("😌 How many minutes break would you like after each cycle \" " +name+ " \" :");
			int breakDuration = scanner.nextInt();
			
			while(numOfCycles != 0) {
			    FocusMode focus = new FocusMode(durationOfEachCycle, breakDuration);
			    Thread focusThread = new Thread(focus);
			    focusThread.start();
			    try {
			        focusThread.join(); 
			    } catch (InterruptedException e) {
			        System.out.println("Main thread interrupted.");
			    }
			    numOfCycles--;
			}

		}

}
