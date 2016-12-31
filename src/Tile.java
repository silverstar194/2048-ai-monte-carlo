/**
 * @author Sean Maloney
 *
 * Used to hold coordinates add a new random title
 */
public class Tile {

	private int row;
	private int col;

	public Tile(int row, int col){
		this.row = row;
		this.col = col;
	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}
	
}
