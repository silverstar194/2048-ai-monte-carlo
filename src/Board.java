import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Admin
 *
 */
public class Board {

	private int[][] board;
	private int score;

	public Board(){
		board = new int[4][4];
		score = 0;
		setupBoard();
		printBoard();
	}

	//clones object for random runs
	public Board(Board myGame){
		this.board = Arrays.stream(myGame.board).map(int[]::clone).toArray(int[][]::new);
		this.score = myGame.score;
	}

	public void moveLeft(boolean sim){
		int[][] boardCopy = new int[4][4];

		for(int j=0; j<4; j++){
			int rowCount = 0;
			for(int i=0;i<4; i++){
				if(board[j][i] > 0){
					if(rowCount > 0 && boardCopy[j][rowCount-1] == board[j][i]){
						boardCopy[j][rowCount-1] = boardCopy[j][rowCount-1]*2;
						score += boardCopy[j][rowCount-1];
					}else{
						boardCopy[j][rowCount] =  board[j][i];
						rowCount++;
					}
				}
			}
		}
		
		this.board = boardCopy;
		addRamdomTile();
		
		
		if(sim == false){
			printBoard();
		}
	}

	public void moveRight(boolean sim){
		int[][] boardCopy = new int[4][4];
		
		for(int j=3; j>=0; j--){
			int rowCount = 3;
			for(int i=3;i>=0; i--){
				if(board[j][i] > 0){
					if(rowCount < 3 && boardCopy[j][rowCount+1] == board[j][i]){
						boardCopy[j][rowCount+1] = boardCopy[j][rowCount+1]*2;
						score += boardCopy[j][rowCount+1];
					}else{
						boardCopy[j][rowCount] =  board[j][i];
						rowCount--;
					}
				}
			}
		}
		
		this.board = boardCopy;
		addRamdomTile();
		
		if(sim == false){
			printBoard();
		}
		
	}

	public void moveDown(boolean sim){
		
		int[][] boardCopy = new int[4][4];
		
		for(int i=3; i>=0; i--){
			int rowCount = 3;
			for(int j=3;j>=0; j--){
				if(board[j][i] > 0){
					if(rowCount < 3 && boardCopy[rowCount+1][i] == board[j][i]){
						boardCopy[rowCount+1][i] = boardCopy[rowCount+1][i]*2;
						score += boardCopy[rowCount+1][i];
					}else{
						boardCopy[rowCount][i] =  board[j][i];
						rowCount--;
					}
				}
			}
		}
		
		this.board = boardCopy;
		addRamdomTile();
		
		if(sim == false){
			printBoard();
		}
		
	}

	public void moveUp(boolean sim){
		
		int[][] boardCopy = new int[4][4];
		
		for(int i=0; i<4; i++){
			int rowCount = 0;
			for(int j=0;j<4; j++){
				if(board[j][i] > 0){
					if(rowCount > 0 && boardCopy[rowCount-1][i] == board[j][i]){
						boardCopy[rowCount-1][i] = boardCopy[rowCount-1][i]*2;
						score += boardCopy[rowCount-1][i];
					}else{
						boardCopy[rowCount][i] =  board[j][i];
						rowCount++;
					}
				}
			}
		}
		
		this.board = boardCopy;
		addRamdomTile();
		
		if(sim == false){
			printBoard();
		}

	}

	public void printBoard(){
		
		System.out.println("\nScore: "+score);
		
		for(int i=0; i<board[0].length; i++){

			System.out.print(" ");
			for(int j=0; j<board[i].length; j++){
				System.out.print(board[i][j]+" ");
			}

			System.out.println();
		}
	}

	public boolean gameOver(){
		for(int i=0; i<this.board[0].length; i++){
			for(int j=0; j<this.board[i].length; j++){

				//if tiles are still empty
				if(this.board[i][j] == 0){
					return false;
				}

				//if tiles can still be combine
				if(j-1 >= 0 &&  this.board[i][j-1] == this.board[i][j]){
					return false;
				}

				if(i-1 >= 0 && this.board[i-1][j] == this.board[i][j]){
					return false;
				}

				if(i+1<4 && this.board[i+1][j] == this.board[i][j]){
					return false;
				}
				if(j+1<4 && this.board[i][j+1] == this.board[i][j]){
					return false;
				}

			}
		}
		return true;
	}

	public void addRamdomTile(){
		ArrayList<Tile> openTiles = new ArrayList<>();

		for(int i=0; i<board[0].length; i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j] == 0){
					openTiles.add(new Tile(i,j));
				}
			}
		}	

		//if open tiles are left
		if(openTiles.size() > 0){
			Tile placeHolderTile = openTiles.get((new Random()).nextInt(openTiles.size()));
			board[placeHolderTile.getRow()][placeHolderTile.getCol()] = Math.random() > 0.9 ? 4 : 2;

		}
	}

	public void setupBoard(){
		addRamdomTile();
		addRamdomTile();
	}

	public int getScore(){
		return score;
	}


	public int maxTile(){

		int max = -1;
		for(int i=0; i<board[0].length; i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j] > max){
					max = board[i][j];
				}
			}
		}	
		return max;
	}


}
