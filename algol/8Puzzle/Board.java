import java.util.Arrays;

public class Board {
	
	private int[][] tiles;
	int N = 0;
	int blankX = 0;
	int blankY = 0;
	private Board previousBoard;
	
	public Board(int[][] blocks) {
		// construct a board from an N-by-N array of blocks
		this.tiles = blocks;
		N = blocks[0].length;
		findBlank();
	}
	
	private void findBlank(){
		for(blankX = 0; blankX < N; blankX++){
			for(blankY = 0; blankY < N; blankY++){
				if (tiles[blankX][blankY] == 0)
					return;
			}
		}
	}

	// (where blocks[i][j] = block in row i, column j)
	public int dimension() {
		// board dimension N
		return N;
	}

	public int hamming() {
		// number of blocks out of place
		int hamming = 0;
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				if(tiles[i][j] != 0 && tiles[i][j] != xYToIndex(i, j)){					
					hamming++;
				}
			}
		}
		return hamming;
	}

	public int manhattan() {
		// sum of Manhattan distances between blocks and goal
		int manhattan = 0;
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				if(tiles[i][j] != 0 && tiles[i][j] != xYToIndex(i, j)){				
					manhattan = manhattan + tilesToGoalXy(tiles[i][j]);
				}
			}
		}
		return manhattan;
		
	}
	
	private int xYToIndex(int x, int y){
		return (N * x) + y + 1;
	}
	
	private int tilesToGoalXy(int index){
		int[][] returnPos = new int[1][2];
		returnPos[0][0] = index / N + 1;
		returnPos[0][1] = (index / N) +  (index % N) + 1;
		return returnPos[0][0] + returnPos[0][1];
	}

	public boolean isGoal() {
		// is this board the goal board?		
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				if(tiles[i][j] != xYToIndex(i, j)){					
					return false;
				}
			}
		}
		return true;
	}

	public Board twin() {
		// a board obtained by exchanging two adjacent blocks in the same row
		int [][] twin = cloneArray(tiles);
		for(int i = 0; i < twin.length - 1; i++){
			for(int j = 0; j < twin[i].length - 1; j++){
				if(twin[i][j] != 0 && twin[i][j + 1] != 0){
					int t = twin[i][j];
					twin[i][j] = twin[i][j + 1];
					twin[i][j + 1] = t;
					return new Board(twin);
				}
			}
		}
		return new Board(twin);
		
	}
	
	private int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}

	public boolean equals(Object y) {
		// does this board equal y?
		if(y.getClass() != this.getClass())
			return false;
		Board other = (Board) y;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; i++){
				if(tiles[i][j] != other.tiles[i][j])
					return false;
			}
		}
		return true;
		
	}
	
	private boolean moveLeft(){
		if(blankX >= 0){			
			tiles[blankX][blankY] = tiles[blankX - 1][blankY];
			tiles[blankX - 1][blankY] = 0;
			blankX--;
			return true;
		}
		return false;
	}
	
	private boolean moveRight(){
		if(blankX <= N){			
			tiles[blankX][blankY] = tiles[blankX + 1][blankY];
			tiles[blankX + 1][blankY] = 0;
			blankX++;
			return true;
		}
		return false;
	}
	
	private boolean moveUp(){
		if(blankY >  0){
			tiles[blankX][blankY] = tiles[blankX][blankY + 1];
			tiles[blankX][blankY + 1] = 0;
			blankY++;
			return true;
		}
		return false;
	}
	
	private boolean moveDown(){
		if(blankY <  N - 1){
			tiles[blankX][blankY] = tiles[blankX][blankY - 1];
			tiles[blankX][blankY - 1] = 0;
			blankY--;
			return true;
		}
		return false;
	}

	public Iterable<Board> neighbors() {
		// all neighboring boards
		Queue<Board> q = new Queue<Board>();
		if(blankX <= N){ // can move right
			Board moveRight = new Board(cloneArray(tiles));
			moveRight.previousBoard = this;
			moveRight.moveRight();
			q.enqueue(moveRight);
		}
		if(blankX >= 0){	 // can move left
			Board moveLeft = new Board(cloneArray(tiles));
			moveLeft.previousBoard = this;
			moveLeft.moveLeft();
			q.enqueue(moveLeft);
		}
		if(blankY >  0){	 // can move up
			Board moveUp = new Board(cloneArray(tiles));
			moveUp.previousBoard = this;
			moveUp.moveUp();
			q.enqueue(moveUp);
		}
		
		if(blankY <  N - 1){ // can move down
			Board moveDown = new Board(cloneArray(tiles));
			moveDown.previousBoard = this;
			moveDown.moveDown();
			q.enqueue(moveDown);
		}
		
		return q;	
	}

	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", tiles[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}
}
