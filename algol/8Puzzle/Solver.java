

public class Solver {

	private MinPQ<SearchNode> minPq = new MinPQ<SearchNode>();
	private SearchNode goal;
	private Board initialBoard;
	private Solver twinSolver;

	public Solver(Board initial) {
		// find a solution to the initial board (using the A* algorithm)
		initialBoard = initial;
		SearchNode first = new SearchNode(initial, 0, null);
		minPq.insert(first);

	}

	private void step() {
		if(minPq.isEmpty()) {
			return;
		}
		
		SearchNode minSearchNode = minPq.delMin();
		// System.out.println("deq'd" + minSearchNode.board);
	//System.out.println("deq'd" + minSearchNode.manhattan + " P: "
	//			+ minSearchNode.priority());
		if (minSearchNode.board.isGoal()) {
			goal = minSearchNode;
			return;
		}
		//System.out.println("not goal");
		for (Board b : minSearchNode.board.neighbors()) {
			// System.out.println("neigh" + b);

			SearchNode newNode = new SearchNode(b, minSearchNode.moves + 1,
					minSearchNode);
			if (!minSearchNode.equalsParents(newNode))
				minPq.insert(newNode);

		}
	}

	public boolean isSolvable() {
		// is the initial board solvable?
		if (goal != null)
			return true;

		twinSolver = new Solver(initialBoard.twin());

		while (goal == null && twinSolver.goal == null && (!minPq.isEmpty() && !twinSolver.minPq.isEmpty())) {
			step();
			twinSolver.step();
		}
		return goal != null;
	}

	public int moves() {
		// min number of moves to solve initial board; -1 if no solution

		if (goal == null || twinSolver == null) {
			if (isSolvable())
				return goal.moves;
			else
				return -1;
		}
		
		if(twinSolver.goal != null) {
			return -1;
		} else {
			return goal.moves;
		}
	}

	public Iterable<Board> solution() {
		// sequence of boards in a shortest solution; null if no solution
		Queue<Board> solutionQueue = new Queue<Board>();
		SearchNode node = goal;
		solutionQueue.enqueue(node.board);
		while (node.previous != null) {
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

	private class SearchNode implements Comparable<SearchNode> {

		private Board board;
		private int manhattan;
		private int moves;
		private SearchNode previous;

		public SearchNode(Board b, int m, SearchNode p) {
			board = b;
			manhattan = b.manhattan();
			moves = m;
			previous = p;
		}

		public int priority() {
			return manhattan + moves;
		}

		public int compareTo(SearchNode obj) {
			// TODO Auto-generated method stub
			int o1Priority = priority();
			int o2Priority = obj.priority();
			if (o1Priority < o2Priority)
				return -1;
			else if (o1Priority > o2Priority)
				return 1;
			else
				return 0;

		}

		public boolean equalsParents(SearchNode y) {
			// does this board equal y?

			if (previous != null) {
				if (previous.equalsParents(y)) {
					return true;
				}
			}

			return this.board.equals(y.board);

		}

	}

}