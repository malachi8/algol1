
import java.util.Arrays;

public class Fast {

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
		Fast fast = new Fast();
		fast.test(points);
	}

	private int N;

	private void test(Point[] points) {
		N = points.length;
		Point[][] onSlopes = new Point[N][N];
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		int slopesCnt = 0;
		for (int i = 0; i < N; i++) {
			Point[] otherThanP = new Point[N - 1];
			points[i].draw();
			int k = 0;
			for (int j = 0; j < N; j++) {
				if (j != i) {
					otherThanP[k++] = points[j];
				}

			}
			Arrays.sort(otherThanP, points[i].SLOPE_ORDER);
			Point[] onSlope = new Point[N];
			int pointOnSlopeCnt = 0;

			boolean found3 = false;
			int l = 0;
			double foundSlope = 0;
			while (l < (otherThanP.length)) {
				double slopetoP = points[i].slopeTo(otherThanP[l]);
				if (found3) {
					if ((slopetoP == foundSlope)) {
						// System.out.println(" and " + otherThanP[l]);
						onSlope[pointOnSlopeCnt++] = otherThanP[l++];
					}
					l++;
					continue;
				}
				if (l >= otherThanP.length - 2)
					break;

				double slopetoP1 = points[i].slopeTo(otherThanP[l + 1]);
				double slopetoP2 = points[i].slopeTo(otherThanP[l + 2]);

				if ((slopetoP == slopetoP1 && slopetoP == slopetoP2)) {
					// System.out.println(otherThanP[l] + " " + otherThanP[l +
					// 1] + " " + otherThanP[l + 2]);
					onSlope[pointOnSlopeCnt++] = points[i];
					onSlope[pointOnSlopeCnt++] = otherThanP[l];
					onSlope[pointOnSlopeCnt++] = otherThanP[l + 1];
					onSlope[pointOnSlopeCnt++] = otherThanP[l + 2];

					l = l + 3;
					found3 = true;
					foundSlope = slopetoP;
				} else
					l++;

			}
			if (pointOnSlopeCnt > 0) {
				boolean found = false;
				Arrays.sort(onSlope, 0, pointOnSlopeCnt);
				for (int x = 0; x < slopesCnt; x++) {
					Point[] prevLines = onSlopes[x];
					if (onSlope[0].compareTo(prevLines[0]) == 0) {
						if (onSlope[pointOnSlopeCnt - 1]
								.compareTo(prevLines[prevLines.length - 1]) == 0) {
							found = true;
							break;
						}
					}
				}
				if (!found) {
					
					for (int x = 0; x < pointOnSlopeCnt; x++) {
						if (x > 0){
							System.out.print(" -> ");
							
						}
						System.out.print(onSlope[x]);

					}
					System.out.println("");
					Point[] nonNulls = Arrays.copyOf(onSlope, pointOnSlopeCnt);
					nonNulls[0].drawTo(nonNulls[nonNulls.length - 1]);
					onSlopes[slopesCnt++] = nonNulls;
				}
			}

		}
	}

}
