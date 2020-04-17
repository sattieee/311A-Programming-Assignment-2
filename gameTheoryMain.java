import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class gameTheoryMain {
	
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many players?");
		int numberOfPlayers = input.nextInt();
		ArrayList<Players> listOfPlayers = new ArrayList<Players>();
		System.out.println("How many games?");
		int numberOfGames = input.nextInt();
		System.out.println("What percentage of players would you like to remove?");
		double percentage = (input.nextInt())/100.0;
		double percentPlayers = percentage * numberOfPlayers;
		System.out.println("How many generations?");
		int gen = input.nextInt();
		
		for (int n = 0; n < numberOfPlayers; n++) {
			if (n%4 == 0) {
				listOfPlayers.add(new Players("t4t"));
			}
			else if (n%4 == 1) {
				listOfPlayers.add(new Players("g"));
			}
			else if (n%4 == 2) {
				listOfPlayers.add(new Players("ac"));
			}
			else {
				listOfPlayers.add(new Players("ad"));	
			}
		}
		
	
	for (int h = 1; h<gen+1; h++) {
		int numberOfT4T = 0;
		int numberOfG = 0;
		int numberOfAC = 0;
		int numberOfAD = 0;
		
		int payoffT4T = 0;
		int payoffG = 0;
		int payoffAC = 0;
		int payoffAD = 0;
		
		

		for (int i = 0; i < numberOfPlayers - 1; i++) {
			for (int k = i+1; k< numberOfPlayers; k++) {
				Players playerOne = listOfPlayers.get(i);
				Players playerTwo = listOfPlayers.get(k);
				playerOne.playerMove = null;
				playerTwo.playerMove = null;
				
				for(int m = 0; m<numberOfGames; m++) {
					if (playerOne.playerType == "t4t") {
						if (playerOne.playerMove == null) {
							playerOne.playerMove = "cooperate";
						}
						else {
							playerOne.playerMove = playerTwo.playerMove;
						}
					}
					
					if (playerOne.playerType == "g") {
						if (playerTwo.playerMove == null | playerTwo.playerMove == "cooperate") {
							playerOne.playerMove = "cooperate";
						}
						else {
							playerOne.playerMove = "defect";
						}
					}
					
					if (playerOne.playerType == "ac") {
						playerOne.playerMove = "cooperate";
					}
					
					if (playerOne.playerType == "ad") {
						playerOne.playerMove = "defect";
					}
					
					if (playerTwo.playerType == "t4t") {
						if (playerTwo.playerMove == null) {
							playerTwo.playerMove = "cooperate";
						}
						else {
							playerTwo.playerMove = playerOne.playerMove;
						}
					}
					
					if (playerTwo.playerType == "g") {
						if (playerOne.playerMove == null | playerOne.playerMove == "cooperate") {
							playerTwo.playerMove = "cooperate";
						}
						else {
							playerTwo.playerMove = "defect";
						}
					}
					
					if (playerTwo.playerType == "ac") {
						playerTwo.playerMove = "cooperate";
					}
					
					if (playerTwo.playerType == "ad") {
						playerTwo.playerMove = "defect";
					}
					
					if(playerOne.playerMove == "cooperate" && playerTwo.playerMove == "cooperate") {
						playerOne.playerPayoff = playerOne.playerPayoff + 3;
						listOfPlayers.get(i).playerPayoff = listOfPlayers.get(i).playerPayoff + 3;
						playerTwo.playerPayoff = playerTwo.playerPayoff + 3;
						listOfPlayers.get(k).playerPayoff = listOfPlayers.get(k).playerPayoff + 3;
					}
					
					if(playerOne.playerMove == "cooperate" && playerTwo.playerMove == "defect") {
						playerTwo.playerPayoff = playerTwo.playerPayoff + 5;
						listOfPlayers.get(k).playerPayoff = listOfPlayers.get(k).playerPayoff + 5;
						playerOne.playerPayoff = playerOne.playerPayoff + 0;
					}
					
					if(playerOne.playerMove == "defect" && playerTwo.playerMove == "cooperate") {
						playerOne.playerPayoff = playerOne.playerPayoff + 5;
						listOfPlayers.get(i).playerPayoff = listOfPlayers.get(i).playerPayoff + 5;
						playerTwo.playerPayoff = playerTwo.playerPayoff + 0;
					}
					
					if(playerOne.playerMove == "defect" && playerTwo.playerMove == "defect") {
						playerOne.playerPayoff = playerOne.playerPayoff + 1;
						playerTwo.playerPayoff = playerTwo.playerPayoff + 1;
						listOfPlayers.get(k).playerPayoff = listOfPlayers.get(k).playerPayoff + 1;
						listOfPlayers.get(i).playerPayoff = listOfPlayers.get(i).playerPayoff + 1;

					}		
				}
			}
		}
		
		for (int l = 0; l < listOfPlayers.size(); l++) {
			if (listOfPlayers.get(l).playerType == "t4t") {
				numberOfT4T = numberOfT4T + 1;
				payoffT4T = payoffT4T + listOfPlayers.get(l).playerPayoff;
			}
			if (listOfPlayers.get(l).playerType == "g") {
				numberOfG = numberOfG + 1;
				payoffG = payoffG + listOfPlayers.get(l).playerPayoff;
			}
			if (listOfPlayers.get(l).playerType == "ac") {
				numberOfAC = numberOfAC + 1;
				payoffAC = payoffAC + listOfPlayers.get(l).playerPayoff;
			}
			if (listOfPlayers.get(l).playerType == "ad") {
				numberOfAD = numberOfAD + 1;
				payoffAD = payoffAD + listOfPlayers.get(l).playerPayoff;
			}
		}
		
		double percentageT4T = (numberOfT4T/listOfPlayers.size())*100.0;
		double percentageG = (numberOfG/listOfPlayers.size())*100.0;
		double percentageAC = (numberOfAC/listOfPlayers.size())*100.0;
		double percentageAD = (numberOfAD/listOfPlayers.size())*100.0;
		

		System.out.println("Gen " + h  + ": T4T: " + percentageT4T + " G: "  + percentageG + " AC: " + percentageAC  + " AD: " + percentageAD);
		System.out.println("Gen " + h  + ": T4T: " + payoffT4T + " G: "  + payoffG + " AC: " + payoffAC  + " AD: " + payoffAD);
		System.out.println("Gen " + h  + ": T4T: " + payoffT4T/numberOfT4T + " G: "  + payoffG/numberOfG + " AC: " + payoffAC/numberOfAC  + " AD: " + payoffAD/numberOfAD);
	
		
		//sort players 
		// Collections.sort(listOfPlayers);
		
		//for (int i = 0; i< listOfPlayers.size()-1; i++) {
		//	for (int k = 0; k < listOfPlayers.size(); i++) { 
		//		if (listOfPlayers.get(i).playerPayoff > listOfPlayers.get(k).playerPayoff) {
		//			listOfPlayers.sort(playerPayoff);
		//		}
		//	}
		//}
		
		//Remove bottom players
		for (int i = 0; i < percentPlayers; i++) 
			listOfPlayers.remove(listOfPlayers.size()-1-i);
		}
		
		//Add new players from top
		for (int k = 0; k < percentPlayers; k++) {
			listOfPlayers.add(new Players(listOfPlayers.get(k).playerType));
		}
		
		//reset player payoffs
		for (int j = 0; j < numberOfPlayers; j++) {
			listOfPlayers.get(j).playerPayoff = 0;
		}	
	}
}
