public class Brute {

	public static void main(String[] args) {
		In in = new In(args[0]); // input file
		int total = in.readInt(); // N-by-N percolation system

		int i = 0;
		Point[] points = new Point[total];
		while (!in.isEmpty()) {
			int x = in.readInt();
			int y = in.readInt();
			points[i++] = new Point(x, y);
		}
		Brute brute = new Brute();
		brute.test(points);
	}
	
	private int N;
		
	public void test(Point[] points){
		
		N = points.length;
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					for (int l = k + 1; l < N; l++) {
						if (i != j && i != k && i != l)
							if (j != k && j != l)
								if (k != l) {									
									Point p = points[i];
									Point q = points[j];
									Point r = points[k];
									Point s = points[l];
									double pSlopeToQ = p.slopeTo(q);
									double pSlopeToR = p.slopeTo(r);
									double pSlopeToS = p.slopeTo(s);
									if (pSlopeToQ == pSlopeToR
											&& pSlopeToQ == pSlopeToS) {
										System.out.println(p + " -> " + q
												+ " -> " + r + " -> " + s);
									}
								}
					}
				}
			}
		}

	}
	
	private int getAdjustedIndex(int indx, int offset) {
		int sIndx = indx + offset >= N ? N - (indx + offset) : indx + offset;
		if (sIndx < 0) {
			sIndx = sIndx * -1;
		}
		return sIndx;
	}

}
