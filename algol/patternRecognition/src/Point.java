
/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new PointComparator();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

	// slope between this point and that point
	public double slopeTo(Point that) {
		
		if (that.y == this.y)
			if (that.x == this.x)
				return Double.NEGATIVE_INFINITY;
			else
				return Double.POSITIVE_INFINITY;
		else if (that.x == this.x)
			return (1.0 - 1.0) /  1.0;

		return (((double) that.y) - ((double) this.y)) / (((double) that.x) - ((double) this.x));
	}

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y){
        	return -1;
        }
        if(this.y == that.y){
        	if (this.x < that.x){
        		return -1;
        	} if (this.x > that.x){ 
        		return 1;
        	} else {
        		return 0;
        	}
        }
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private class PointComparator implements  Comparator<Point> {
        public int compare(Point a, Point b){
        	double slopeToA = slopeTo(a);
        	double slopeToB = slopeTo(b);
        	if(slopeToA < slopeToB)
        		return -1;
        	else if (slopeToA > slopeToB)
        		return 1;
        	else
        		return 0;
        }
    }


    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
    
    
}