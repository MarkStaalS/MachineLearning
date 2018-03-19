package src;

public class connection {
	double w;
	double x;
	double z_c;
	double n;
	double delta_w;
	double target;

	public connection() {
	}
	public void int_w(double weight) {
		w = weight;
	}
	public void setInput(double input) {
		x = input;
	}
	public void set_n(double set_n) {
		n = set_n;
	}
	public void target(double set_target) {
		target = set_target;
	}
	public double getOutput() {
		z_c = w*x;
		return z_c;
	}
	public double update_w() {
		double delta_w = n*(target-z_c)*x;
		w = w - delta_w;
		return w;
	}
}
