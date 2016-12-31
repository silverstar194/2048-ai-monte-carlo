import java.io.PrintWriter;

/**
 * @author Sean Maloney
 *
 */
public class MaxScoreMonteCarlo {

	private Board myGame;
	private int count;
	private PrintWriter writer;

	public MaxScoreMonteCarlo(Board myGame, int count, PrintWriter writer){
		this.myGame = new Board(myGame);
		this.count = count;
		this.writer = writer;
	}

	public int nextMove(){

		double moveZero = 0;
		double moveZeroCount = 0;

		double moveOne = 0;
		double moveOneCount = 0;

		double moveTwo = 0;
		double moveTwoCount = 0;

		double moveThree = 0;
		double moveThreeCount = 0;

		int input = -1;
		for(int i=0; i<Config.RANDOM_RUNS; i++){

			Board boardCopy = new Board(myGame);
			int count = 0;
			int firstMove = -1;
			int gameMoves = 0;
			
			while(!boardCopy.gameOver()){
				gameMoves++;
				//generate random next move
				input =  (int)(Math.random() * 4);
				
				//make the random move
				if(input == 0){
					boardCopy.moveUp(true);
				}else if(input == 1){
					boardCopy.moveDown(true);
				}else if(input == 2){
					boardCopy.moveRight(true);
				}else if(input == 3){
					boardCopy.moveLeft(true);
				}

				//record what the first random move count
				if(input == 0 && count == 0){
					moveZeroCount++;
				}else if(input == 1 && count == 0){
					moveOneCount++;
				}else if(input == 2 && count == 0){
					moveTwoCount++;
				}else if(input == 3 && count == 0){
					moveThreeCount++;
				}
				
				//record what the first random move was
				if(count == 0){
					firstMove = input;
				}

				count++;
			}

			//sum random game scores
			if(firstMove == 0){
				moveZero += boardCopy.getScore();
			}else if(firstMove == 1){
				moveOne += boardCopy.getScore();
			}else if(firstMove == 2){
				moveTwo += boardCopy.getScore();
			}else if(firstMove == 3){
				moveThree += boardCopy.getScore();
			}
			
			writer.println("t,"+this.count+","+boardCopy.getScore()+","+boardCopy.maxTile()+","+gameMoves);

		}

		//calculate random game averages
		moveZero = moveZero/moveZeroCount;
		moveOne = moveOne/moveOneCount;
		moveTwo = moveTwo/moveTwoCount;
		moveThree = moveThree/moveThreeCount;

		//get move with max average score
		double maxOfAll = Math.max(moveZero, Math.max(moveOne, Math.max(moveTwo,moveThree)));

		//return best move
		if(maxOfAll == moveZero){
			return 0;
		}else if(maxOfAll == moveOne){
			return 1;
		}else if(maxOfAll == moveTwo){
			return 2;
		}else if(maxOfAll == moveThree){
			return 3;
		}
		
		
		
		return -1;
	}

}
