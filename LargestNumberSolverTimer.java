package assign04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LargestNumberSolverTimer extends TimerTemplate {
	private Random random;
	private List<Integer[]> list;
	public LargestNumberSolverTimer(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
		random = new Random();
	}

	@Override
	protected void setup(int n) {
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Integer[] add = new Integer[10];
			for (int j = 0; j < 10; j++)
				add[j] = random.nextInt(100);
			list.add(add);
		}
	}

	@Override
	protected void timingIteration(int n) {
		LargestNumberSolver.findKthLargest2(list, 0);
	}

	@Override
	protected void compensationIteration(int n) {
		
		
	}
	
	public static void main(String[] args) {
		int[] problemSizes = new int[20];
		for (int i = 0; i < 20; i ++)
			problemSizes[i] = i * 100 + 100;
		LargestNumberSolverTimer timer = new LargestNumberSolverTimer(problemSizes, 10);
		for (Result r: timer.run()) 
			System.out.println("" + r.n() + ", " + r.avgNanoSecs());
			
	}

}
