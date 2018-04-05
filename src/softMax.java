package src;

public class softMax {
	
	public softMax() {
		
	}
	
	public static double[] getSoftMax(neuron_output[] outputs) {
		/*
		 * SoftMax layer/ activation function
		 */
		double t[] = new double[outputs.length];
		double t_sum = 0;
		double softMax[] = new double[outputs.length];
		for (int i = 0; i < outputs.length; i++) {
			t[i] = Math.exp(outputs[i].calcOut());
			t_sum += t[i];
		}
		for (int i = 0; i < outputs.length; i++) {
			softMax[i] = t[i] / t_sum;
		}
		return softMax;	
	}
}
