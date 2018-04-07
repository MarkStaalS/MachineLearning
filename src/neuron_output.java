package src;

import java.util.HashMap;

import org.omg.CORBA.PUBLIC_MEMBER;

public class neuron_output {
	double g;
	int index;
	double bias;
	double z;
	double delta;
	
	//Array of connections
	HashMap<Integer, connection> connections = new HashMap<Integer, connection>();
	
	public neuron_output(double _bias) {
		bias = _bias;
		z = 0;
	}
	
	private double activationFunc(double _z) {
		//activation function
		if (_z >= 0) {
			g = _z;
		} else {
			g = -1;		
		}	
		return g;
	}
	
	public double calcOut() {
		z = 0;
		//sum of the inputs and gives z
		for (int i = 0; i < connections.size(); i++) {
			z += connections.get(i).getOutput();			
		}
		return this.activationFunc(z) + bias;
	}
	
	public void addConnection(connection c) {
		//add a connection at index
		connections.put(connections.size(), c);
	}
	
	public void update_w(double target, double y) {
		//loops through connections
		for (int i = 0; i < connections.size(); i++) {
			delta = (target - y) * connections.get(i).x;
			connections.get(i).setDelta(delta);
			connections.get(i).update_w();
		}
	}
}

