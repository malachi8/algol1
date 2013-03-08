public class PointSET {

	private SET<Point2D> pointSet = new SET<Point2D>();

	public PointSET() {
		// construct an empty set of points
	}

	public boolean isEmpty() {
		// is the set empty?
		return pointSet.isEmpty();
	}

	public int size() {
		// number of points in the set
		return pointSet.size();
	}

	public void insert(Point2D p) {
		// add the point p to the set (if it is not already in the set)
		pointSet.add(p);
	}

	public boolean contains(Point2D p) {
		// does the set contain the point p?
		return pointSet.contains(p);
	}

	public void draw() {
		// draw all of the points to standard draw
		for (Point2D p : pointSet) {
			p.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points in the set that are inside the rectangle
		Queue<Point2D> solutionQueue = new Queue<Point2D>();
		for (Point2D p : pointSet) {
			if (rect.contains(p)) {
				solutionQueue.enqueue(p);
			}

		}
		return solutionQueue;

	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to p; null if set is empty
		Point2D nearest = null;
		double mindistance = 0;
		for (Point2D point : pointSet) {
			if (nearest == null) {
				nearest = point;
				mindistance = point.distanceTo(p);
			} else {
				double distance = point.distanceTo(p);
				if (distance < mindistance) {
					nearest = point;
					mindistance = distance;
				}
			}
		}
		return nearest;
	}
}
