import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Fast2 {
	
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
		Fast2 fast = new Fast2();
		fast.test(points);
	}
	
	private int N;
		
	public void test(Point[] points){
		N = points.length;
		for(int i = 0; i < N; i++){
			Point[] otherThanP = new Point[N - 1];
			int k = 0;
			for(int j = 0; j < N; j++){
				if(j != i){
					otherThanP[k++] = points[j];
				}
					
			}
			Arrays.sort(otherThanP, points[0].SLOPE_ORDER);
			List<Point> pts = new ArrayList<Point>();
			
			boolean found3 = false;
			int l = 0;
			double foundSlope = 0;
			while (l < (otherThanP.length)){
				double slopetoP = points[i].slopeTo(otherThanP[l]);
				if(found3){
					if((slopetoP == foundSlope)){
						//System.out.println(" and " + otherThanP[l]);
						pts.add(otherThanP[l++]);
					}
					continue;
				}
				if(i > otherThanP.length - 2)
					break;
				
				double slopetoP1 = points[i].slopeTo(otherThanP[l + 1]);
				double slopetoP2 = points[i].slopeTo(otherThanP[l + 2]);
				
				if((slopetoP == slopetoP1 && slopetoP == slopetoP2)){
					//System.out.println(otherThanP[l] + " " +  otherThanP[l + 1] + " " + otherThanP[l + 2]);
					pts.add(otherThanP[l]);
					pts.add(otherThanP[l + 1]);
					pts.add(otherThanP[l + 2]);
					l = l + 3;
					found3 = true;
					foundSlope = slopetoP;
				} else
					l++;
						
			}
			if(pts.size() > 0){
				System.out.print(points[i] + " -> ");
				for(Point p : pts){
					System.out.print( p + " -> ");
				}
			}
			System.out.println("");
		}
	}

}
