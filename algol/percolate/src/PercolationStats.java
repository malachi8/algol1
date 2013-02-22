
public class PercolationStats {
	
	private double[] openCntFrac;
	private double t;
	
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		new PercolationStats(N, T);
		
	}

	public PercolationStats(int N, int T){
		// perform T independent computational experiments on an N-by-N grid
		int[] openCnts = new int[T];
		openCntFrac = new double[T];
		int gridSize = N * N;
		t = T;
		for (int i = 0; i < T; i++){
			Percolation percolation  = new Percolation(N);
			
			try {

				while (!percolation.percolates()) {
					int randomX = StdRandom.uniform(N) + 1;
					int randomY = StdRandom.uniform(N) + 1;
					if(!percolation.isOpen(randomX,  randomY)){
						percolation.open(randomX, randomY);
						openCnts[i]++;
					}
				}
				openCntFrac[i] = (double) openCnts[i] / gridSize;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("mean: " + mean());
		System.out.println("std: " + stddev());
		System.out.println("low: " + confidenceLo());
		System.out.println("hi: " + confidenceHi());
	}
	
	 public double mean(){
	   // sample mean of percolation threshold
		 return StdStats.mean(openCntFrac);
	 }
	 
	   public double stddev() {
		   // sample standard deviation of percolation threshold
		   return StdStats.stddev(openCntFrac);
	   }
	   
	   public double confidenceLo() {
	               // returns lower bound of the 95% confidence interval
		   return mean() - (1.96 * stddev()) / Math.sqrt(t);
	    }
	   public double confidenceHi()  {
		   return mean() + (1.96 * stddev()) / Math.sqrt(t);
	   }
	 
}
