import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//import java.util.Scanner;

/**
 * @author Sean Maloney
 *
 */
public class Main {

	public static void main(String args[]){
		try{
			PrintWriter writer = new PrintWriter(new FileWriter("depth500allmoves.txt", true)); 
			for(int i=0; i<1; i++){
			
				Board myGame = new Board();

				//Scanner in = new Scanner(System.in);
				
				int lastInput = 0;
				int count = 0;
				
				//prevents infinite cycles
				int lastInputCount = 0;

				while(!myGame.gameOver()){
					count++;
					
					MaxScoreMonteCarlo calcNextMove = new MaxScoreMonteCarlo(myGame, count, writer);

					//to play game by hand
					//			String input= in.nextLine();
					//			if(input.equals("w")){
					//				myGame.moveUp(false);
					//			}else if(input.equals("s")){
					//				myGame.moveDown(false);
					//			}else if(input.equals("d")){
					//				myGame.moveRight(false);
					//			}else if(input.equals("a")){
					//				myGame.moveLeft(false);
					//			}

					int input = calcNextMove.nextMove();
					
//					int input = input =  (int)(Math.random() * 4);
					
					if(input == lastInput){
						lastInputCount++;
					}else{
						lastInputCount = 0;
					}
					
					lastInput = input;
					
					if(lastInputCount > 5){
						input = (int)(Math.random() * 4);
						lastInputCount = 0;
					}
					

					System.out.println("Moves: "+count);

					if(input == 0){
						myGame.moveUp(true);
					}else if(input == 1){
						myGame.moveDown(true);
					}else if(input == 2){
						myGame.moveRight(true);
					}else if(input == 3){
						myGame.moveLeft(true);
					}

				}


				writer.println("final,"+myGame.getScore()+","+myGame.maxTile()+","+count);

				System.out.println(myGame.getScore());
				System.out.println(myGame.maxTile());
				System.out.println(count);
			
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("FILE NOT WRITTEN");
		}
		
		System.out.println("Game Over\n");
	}
}

