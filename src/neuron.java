package src;

import java.util.HashMap;

public class neuron {
	double g;
	double z;
	double bias;
	double delta;
	/*
	 * source: https://www.codeproject.com/Articles/14342/Designing-And-Implementing-A-Neural-Network-Librar 
	 */
	
	/*
	 * Array of connections
	 */
	HashMap<Integer, connection> inputConnections = new HashMap<Integer, connection>();
	HashMap<Integer, connection> outputConnections = new HashMap<Integer, connection>();
	
	
	public neuron(double _bias) {
		bias = _bias;
		z = 0;
	}
	
	private double activationFunc(double _z) {
		if (_z >= 0) {
			g = _z;
		} else {
			g = -1;		
		}	
		return g;
	}
	/*
	 * summs inputs and gives z
	 */
	public double calcOut() {
		z = 0;
		for (int i = 0; i < inputConnections.size(); i++)
			z += inputConnections.get(i).getOutput();
		z = this.activationFunc(z) + bias;
		return z;
	}
	/*
	 * hidden layer neuron
	 */
	public void calcOutHidden() {
		z = calcOut();
		/*
		 * loops through outputs and sets their input as the result of the activation function
		 */
		double out = z; 
		for (int i = 0; i < outputConnections.size(); i++)
			outputConnections.get(i).setInput(out);
	}
	/*
	 * calc error factor for a fully conected network
	 */
	public void calcErrorFactorHidden() {
		double errorFactor = 0;
		for (int i = 0; i < outputConnections.size(); i++)
			errorFactor = errorFactor + (outputConnections.get(i).delta + outputConnections.get(i).w);
		double output = calcOut();
		delta = output * (1 - output) * errorFactor;
	}
	
	/*
	 * output layer neuron
	 * when creating output layer output connections should be omitted
	 * calc error for output layer
	 */
	public void calcErrorOut(double target) {
		double output = calcOut();
		delta = output * (1 - output) * errorFactorOut(target, output);
		for (int i = 0; i < inputConnections.size(); i++) {
			inputConnections.get(i).setDelta(delta);
		}
	}
	
	private double errorFactorOut(double target, double output) {
		double errorFactor = target - output;
		return errorFactor;
	}
	
	/*
	 * input neuron
	 */
	public double getOutput() {
		return z;
	}
	public void setInput(double _input) {
		z = _input;
	}

	public void updateFreeParameters(double n) {
		/*
		 * update bias
		 */
		bias += n * 1 * delta;
		/*
		 * update weight
		 */
		double w = 0;
		for (int i = 0; i < inputConnections.size(); i++) {
			w = 0;
			w = inputConnections.get(i).w + n * 1 * calcOut() * delta;
			inputConnections.get(i).set_w(w);
		}
	}
	
	/*
	 * Infrastructure
	 */
	public void addInputConnection(connection c) {
		inputConnections.put(inputConnections.size(), c);
	}
	
	public void addOutputConnection(connection c) {
		outputConnections.put(outputConnections.size(), c);
	}
	
	
	/*
	public void update_w() {
		//TODO loops through connections 
		for (int i = 0; i < inputConnections.size(); i++) {
			double sum = 0;
			for (int j = 0; j < outputConnections.size(); j++)
				sum += outputConnections.get(j).w + outputConnections.get(j).delta;
			double delta = sum * this.activationFunc(z) + bias;
			inputConnections.get(i).setDelta(delta);
			inputConnections.get(i).update_w();
		}
	}
	*/
}

