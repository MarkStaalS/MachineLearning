package src;

import java.util.HashMap;
import java.util.Vector;

public class neuron {
	double g;
	double z;
	double bias;
	double delta;
	Vector<Double> batchWeights = new Vector<>();
	/*
	 * source: https://www.codeproject.com/Articles/14342/Designing-And-Implementing-A-Neural-Network-Librar 
	 */

	HashMap<Integer, connection> inputConnections = new HashMap<Integer, connection>();
	HashMap<Integer, connection> outputConnections = new HashMap<Integer, connection>();
	
	
	public neuron(int InputConnectionSize) {
		bias = 0;
		z = 0;
		batchWeights.setSize(InputConnectionSize);
		for (int i = 0; i < batchWeights.size(); i++) 
			batchWeights.set(i, 0.0);
	}
	
	private double activationFunc(double _z) {
		/*
		 * sigmoid 
		 */
		return (1 / (1 + Math.exp(- z)));
	}
	/*
	 * summation unit
	 */
	public double calcOut() {
		z = 0;
		for (int i = 0; i < inputConnections.size(); i++)
			z += inputConnections.get(i).getOutput();
		z = this.activationFunc(z) + bias;
		return z;
	}

	public void feedForward() {
		z = calcOut();
		/*
		 * feedforward
		 */
		double out = z; 
		for (int i = 0; i < outputConnections.size(); i++)
			outputConnections.get(i).setInput(out);
	}
	
	/*
	 * calc error factor of a neuron in a hidden layer
	 */
	public void calcErrorFactorHidden() {
		delta = 0;
		double errorFactor = 0;
		for (int i = 0; i < outputConnections.size(); i++)
			errorFactor = errorFactor + (outputConnections.get(i).delta + outputConnections.get(i).w);
		double output = calcOut();
		delta = output * (1 - output) * errorFactor;
	}
	
	/*
	 * calc error for output layer neuron
	 */
	public void calcErrorOut(double target) {
		delta = 0;
		double output = calcOut();
		delta = output * (1 - output) * errorFactorOut(target, output);
		/*
		 * back propagation of delta to input connections
		 */
		for (int i = 0; i < inputConnections.size(); i++) {
			inputConnections.get(i).setDelta(delta);
		}
	}
	
	private double errorFactorOut(double target, double output) {
		return target - output;
	}

	public void updateFreeParameters(double n) {
		/*
		 * update bias
		 */
		//bias += n * 1 * delta;
		/*
		 * update weight
		 */
		for (int i = 0; i < inputConnections.size(); i++) {
			double w = n * 1 * calcOut() * delta;
			batchWeights.set(i, batchWeights.get(i) + w);
		}
	}
	
	public void updateW(int epochs) {
		for (int i = 0; i < inputConnections.size(); i++) {
			inputConnections.get(i).set_w(batchWeights.get(i));
			batchWeights.set(i, 0.0);
		}
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

	/*
	 * Infrastructure
	 */
	public void addInputConnection(connection c) {
		inputConnections.put(inputConnections.size(), c);
	}
	
	public void addOutputConnection(connection c) {
		outputConnections.put(outputConnections.size(), c);
	}
	
	public void setBias(double _bias) {
		bias = _bias;
	}
}

