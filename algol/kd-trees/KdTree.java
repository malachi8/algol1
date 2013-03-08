
public class KdTree {

	private Node root; // root of BST

	private static class Node {
		private Point2D p; // the point
		private RectHV rect; // the axis-aligned rectangle corresponding to this
								// node
		private Node lb; // the left/bottom subtree
		private Node rt; // the right/top subtree

		public Node(Point2D p, RectHV rect) {
			this.p = p;
			this.rect = rect;

		}
	}

	public void insert(Point2D p) {

		root = insert(root, p, new RectHV(0.0, 0.0, 1.0, 1.0), 0);

	}

	private Node insert(Node parent, Point2D p, RectHV rect, int level) {
		if (parent == null)
			return new Node(p, rect);

		int nextLevel = level + 1;
		if (level % 2 == 0) {
			if (p.x() < parent.p.x()) {				
				parent.lb = insert(parent.lb, p, new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax()), nextLevel);
			} else {
				parent.rt = insert(parent.rt, p, new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax()), nextLevel);
			}
		} else {
			if (p.y() < parent.p.y()) {
				parent.lb = insert(parent.lb, p, new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y()), nextLevel);
			} else {
				parent.rt = insert(parent.rt, p, new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax()), nextLevel);
			}
		}

		return parent;
	}
	
	   // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

	
	  // return number of key-value pairs in BST
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        int sum = 0;
        if(x.lb != null){
        	sum = sum + size(x.lb);
        }
        if(x.rt != null){
        	sum = sum + size(x.rt);
        }
        return ++sum;  
    }
    
	public Iterable<Point2D> range(RectHV rect) {
		// all points in the set that are inside the rectangle
		Queue<Point2D> solutionQueue = new Queue<Point2D>();
		return range(root, rect, solutionQueue);

	}
	
	private Iterable<Point2D> range(Node node, RectHV rect, Queue<Point2D> solutionQueue) {
		if(node == null)
			return solutionQueue;
		if(node.rect.intersects(rect)){
		//if(node.rect.contains(new Point2D(rect.xmin(), rect.ymin())) && node.rect.contains(new Point2D(rect.xmax(), rect.ymax()))){
			if(node.lb != null){
				range(node.lb, rect, solutionQueue);
			}
			if(node.rt != null){
				range(node.rt, rect, solutionQueue);
			}
			if(rect.contains(node.p)){
				solutionQueue.enqueue(node.p);
			}
		}
		return solutionQueue;
	}
	
	public void draw() {
		// all points in the set that are inside the rectangle
		
		draw(root, 0);

	}
	
	private void draw(Node node, int level){
		if(node == null)
			return;
		draw(node.lb, level + 1);
		draw(node.rt, level + 1);
		
		if(level % 2 == 0){
			node.rect.draw();
		} else {
			node.rect.draw();
		}
	}
	
	
	public Point2D nearest(Point2D point) {
		// all points in the set that are inside the rectangle
		
		return nearest(root, point, null);

	}
	
	private Point2D nearest(Node node, Point2D point, Point2D nearest) {

		if (node == null)
			return nearest;

		if (node.rect.contains(point)) {
			// if(node.rect.contains(new Point2D(rect.xmin(), rect.ymin())) &&
			// node.rect.contains(new Point2D(rect.xmax(), rect.ymax()))){
			if (node.lb != null) {
				nearest = nearest(node.lb, point, nearest);
			}
			if (node.rt != null) {
				nearest = nearest(node.rt, point, nearest);
			}
		}
		if (nearest == null) {
			nearest = node.p;
		} else if (node.p.distanceTo(point) < nearest.distanceTo(point)) {
			nearest = node.p;
		}

		return nearest;
	}
   

}
