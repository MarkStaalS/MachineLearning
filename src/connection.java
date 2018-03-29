package src;

public class connection {
	double w;
	double x;
	double z;
	double n;
	double delta;
	
	//Constructs connection
	public connection(double _w, double _n) {
		w = _w;
		n = _n;
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
	
	public void setDelta(double _delta) {
		delta = _delta;
	}
	
	//calculates output
	public double getOutput() {
		z = w * x;
		return z;
	}
	
	//updates the weight
	public void update_w() {
		double delta_w = n * delta * z;
		w = w - delta_w;
	}
}
