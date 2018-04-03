package src;

import java.util.HashMap;

public class neuron {
	double g;
	double z;
	double bias;
	
	//Array of connections
	HashMap<Integer, connection> inputConnections = new HashMap<Integer, connection>();
	HashMap<Integer, connection> outputConnections = new HashMap<Integer, connection>();
	
	
	public neuron(double _bias) {
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
	
	public void calcOut() {
		z = 0;
		//sum of the inputs and gives z
		for (int i = 0; i < inputConnections.size(); i++)
			z += inputConnections.get(i).getOutput();
		//loops through outputs and sets their input as calcOut
		double out = this.activationFunc(z) + bias;
		for (int i = 0; i < outputConnections.size(); i++)
			outputConnections.get(i).setInput(out);
	}
	
	public void addInputConnection(connection c) {
		//add a connection at index
		inputConnections.put(inputConnections.size(), c);
	}
	
	public void addOutputConnection(connection c) {
		//add a connection at index
		outputConnections.put(outputConnections.size(), c);
	}
	
	public void update_w() {
		//loops through connections
		for (int i = 0; i < inputConnections.size(); i++) {
			double delta = inputConnections.get(i).w * outputConnections.get(0).delta * inputConnections.get(i).x;
			inputConnections.get(i).setDelta(delta);
			inputConnections.get(i).update_w();
		}
	}
}

