package src;

public class cost {
	/*
	 * cost = total error
	 */
	public cost() {
		
	}
	/*
	 * squared error function
	 */
	public static double getCost(neuron_output[] outputs, double[] target) {
		double cost = 0;
		for (int i = 0; i < outputs.length; i++) {
			cost += (Math.pow((outputs[i].calcOut()-target[i]),2));
		}
		return cost;
	}

}
