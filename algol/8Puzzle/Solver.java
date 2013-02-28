import java.util.Comparator;

public class Solver {
	
	private MinPQ<SearchNode> minPq = new MinPQ<SearchNode>();
	private SearchNode goal;
	private Board initialBoard;
	
	public Solver(Board initial) {
		// find a solution to the initial board (using the A* algorithm)
		initialBoard = initial;
		
	}
	
	private void insertInitial(Board initial){
		SearchNode first = new SearchNode(initial, 0, null);
		minPq.insert(first);
	}
	
	
	private void step() {
		SearchNode minSearchNode = minPq.delMin();
		if (minSearchNode.board.isGoal()) {
			goal = minSearchNode;
			return;
		}
		for (Board b : minSearchNode.board.neighbors()) {

			if (!b.equals(minSearchNode.board)) {
				SearchNode newNode = new SearchNode(b, minSearchNode.moves + 1,
						minSearchNode);

				minPq.insert(newNode);
			}
		}
	}


	public boolean isSolvable() {
		// is the initial board solvable?
		insertInitial(initialBoard);
		
		Solver twinSolver = new Solver(initialBoard.twin());
		
		while(goal == null && twinSolver.goal == null){
			step();
			twinSolver.step();
		}
		return goal != null;
	}

	public int moves() {
		// min number of moves to solve initial board; -1 if no solution

		SearchNode node = goal;
		int moves = 0;
		while (node.previous != null) {
			node = node.previous;
			moves++;
		}
		return moves;
	}

	public Iterable<Board> solution() {
		// sequence of boards in a shortest solution; null if no solution
		Queue<Board> solutionQueue = new Queue<Board>();		
		SearchNode node = goal;
		solutionQueue.enqueue(node.board);
		while(node.previous != null){
			node = node.previous;
			solutionQueue.enqueue(node.board);
		}
		return solutionQueue;
	}

	public static void main(String[] args) {
	    // create initial board from file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}
	
	private class SearchNode implements Comparator<SearchNode> {
		
		private Board board;
		private int manhattan;
		private int moves;
		private SearchNode previous;
		
		public SearchNode(Board b, int m, SearchNode p){
			board = b;
			manhattan = b.manhattan();
			moves = m;	
			previous = p;
		}
		
		public int priority(){
			return manhattan + moves;
		}

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			// TODO Auto-generated method stub
			int o1Priority = o1.priority();
			int o2Priority = o2.priority();
			if(o1Priority < o2Priority)
				return -1;
			else if(o1Priority > o2Priority)
				return 1;
			else
				return 0;
			
		}
		
		
	}

}