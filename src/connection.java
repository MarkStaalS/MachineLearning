package src;

public class connection {
	double w;
	double x;
	double z;
	double n;
	double delta_w;
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
	//calculates output
	public double getOutput() {
		z = w * x;
		return z;
	}
	//updates the weight
	public void update_w(double delta) {
		double delta_w = n*delta*z;
		w = w - delta_w;
	}
}
