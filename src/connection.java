package src;

public class connection {
	double w;
	double x;
	double z_c;
	double n;
	double delta_w;
	double target;
	//Constructs connection
	public connection() {
	}
	//set weight
	public void set_w(double weight) {
		w = weight;
	}
	//Set input
	public void setInput(double input) {
		x = input;
	}
	//set n, learning rate
	public void set_n(double set_n) {
		n = set_n;
	}
	// target value
	public void target(double set_target) {
		target = set_target;
	}
	//calculates output
	public double getOutput() {
		z_c = w*x;
		return z_c;
	}
	//updates the weight
	public double update_w() {
		double delta_w = n*(target-z_c)*x;
		w = w - delta_w;
		return w;
	}
}
