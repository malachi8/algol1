public class Percolation {

	private WeightedQuickUnionUF weightedQuickUnionUF = null;
	private int gridSizeN;
	private int totalSizeN;
	private int[][] grid;
	private int openCnt;

	public static void main(String[] args) {
		// int N = StdIn.readInt();
		int N = 20;
		Percolation percolation = new Percolation(N);
		try {

			while (!percolation.percolates()) {
				int ramdomX = StdRandom.uniform(N) + 1;
				int ramdomY = StdRandom.uniform(N) + 1;

				percolation.open(ramdomX, ramdomY);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int test(int N) {
		// int N = StdIn.readInt();

		Percolation percolation = new Percolation(N);
		try {

			while (!percolation.percolates()) {
				int ramdomX = StdRandom.uniform(N) + 1;
				int ramdomY = StdRandom.uniform(N) + 1;

				percolation.open(ramdomX, ramdomY);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return percolation.getOpenCnt();
	}

	public Percolation(int N) {
		// create N-by-N grid, with all sites blocked
		weightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 2);
		gridSizeN = N;
		totalSizeN = N * N;

		grid = new int[N][N];

		// join up virtual nodes
		for (int i = 1; i <= N; i++) {
			weightedQuickUnionUF.union(0, xyTo1DForW(1, i));

		}

		int topNode = (N * N) + 1;
		for (int i = 1; i <= N; i++) {
			weightedQuickUnionUF.union(topNode, xyTo1DForW(N, i));

		}

	}

	private int getOpenCnt() {
		return openCnt;
	}

	public void open(int i, int j) {
		validateIndices(i, j);

		if (isOpen(i, j))
			return;

		// union top
		if (isValidIndices(i - 1, j) && isOpen(i - 1, j)) {
			weightedQuickUnionUF.union(xyTo1DForW(i, j), xyTo1DForW(i - 1, j));
		}

		// union left
		if (isValidIndices(i, j - 1) && isOpen(i, j - 1)) {
			weightedQuickUnionUF.union(xyTo1DForW(i, j), xyTo1DForW(i, j - 1));
		}

		// union right
		if (isValidIndices(i, j + 1) && isOpen(i, j + 1)) {
			weightedQuickUnionUF.union(xyTo1DForW(i, j), xyTo1DForW(i, j + 1));
		}

		// union bottom
		if (isValidIndices(i + 1, j) && isOpen(i + 1, j)) {
			weightedQuickUnionUF.union(xyTo1DForW(i, j), xyTo1DForW(i + 1, j));
		}

		grid[i - 1][j - 1] = 1;
		openCnt++;
	}

	// open site (row i, column j) if it is not already
	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		return grid[i - 1][j - 1] == 1;
	}

	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?

		return isOpen(i, j)
				&& (weightedQuickUnionUF.connected(0, xyTo1DForW(i, j)));

	}

	private int xyTo1D(int x, int y) {
		// come up with a scheme for uniquely mapping 2D coordinates to 1D
		// coordinates
		return (gridSizeN * (x - 1)) + (y - 1);
	}

	private int xyTo1DForW(int x, int y) {
		// come up with a scheme for uniquely mapping 2D coordinates to 1D
		// coordinates
		return xyTo1D(x, y) + 1;
	}

	private void validateIndices(int x, int y) {
		if (!isValidIndices(x, y)) {
			throw new RuntimeException("invalid indexs: " + x + " " + y);
		}
	}

	private boolean isValidIndices(int x, int y) {
		return x >= 1 && x <= gridSizeN && y >= 1 && y <= gridSizeN;

	}

	public boolean percolates() {
		// does the system percolate?
		return weightedQuickUnionUF.connected(0, (totalSizeN + 1));
	}

}
