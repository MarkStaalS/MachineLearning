package src;

public class neuron_input {

	double z;
	
	public neuron_input() {
		z = 0;
	}
	
	public double getOutput() {
		return z;
	}
	
	public void setInput(double input) {
		z = input;
	}
}

