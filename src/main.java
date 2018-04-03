package src;

public class main {
	public static void main(String[] args) {
		
		//input layer
		neuron_input in0 = new neuron_input();
		neuron_input in1 = new neuron_input();
		neuron_input in2 = new neuron_input();
		neuron_input in3 = new neuron_input();
		neuron_input[] inputs = {in0, in1, in2, in3};
		
		//1st hiddenlayer
		neuron n0_1 = new neuron(0);	
		neuron[] hl1 = {n0_1};

		//connect input to hidden layer 1
		connection c00_1 = new connection(Math.random(), 0.02);
		connection c10_1 = new connection(Math.random(), 0.02);
		connection c20_1 = new connection(Math.random(), 0.02);
		connection c30_1 = new connection(Math.random(), 0.02);
		
		//connection hiddenlayer 1 to 2
		connection c00_2 = new connection(Math.random(), 0.02);

		//add hiddenalyer 1 connections
		n0_1.addInputConnection(c00_1);
		n0_1.addInputConnection(c10_1);
		n0_1.addInputConnection(c20_1);
		n0_1.addInputConnection(c30_1);
		n0_1.addOutputConnection(c00_2);
		//end of 1st layer
		
		//2nd layer
		neuron n0_2 = new neuron(0);
		neuron[] hl2 = {n0_2};
		
		//connection hiddenlayer 2 to output layer
		connection c00_3 = new connection(Math.random(), 0.02);
		
		//add hiddenalyer 2 connections
		n0_2.addInputConnection(c00_2);
		n0_2.addOutputConnection(c00_3);
		
		//output layer
		neuron_output on = new neuron_output(0);
		neuron_output[] outputs = {on};
		on.addConnection(c00_3);
		//Here we have our full network
		
		//Feedforward
		//input layer
		inputs[0].setInput(1);
		inputs[1].setInput(1);
		inputs[2].setInput(1);
		inputs[3].setInput(1);
		c00_1.setInput(in0.getOutput());
		c10_1.setInput(in1.getOutput());
		c20_1.setInput(in2.getOutput());
		c30_1.setInput(in3.getOutput());
		//hiddenlayers
		hl1[0].calcOut();
		hl2[0].calcOut();
		//output layer
		double output = outputs[0].calcOut();
		System.out.println(output);
		//traning (backpropagation)
		outputs[0].update_w(1, output);
		hl2[0].update_w();
		hl1[0].update_w();
		
		//Feedforward
		//input layer
		inputs[0].setInput(1);
		inputs[1].setInput(1);
		inputs[2].setInput(1);
		inputs[3].setInput(1);
		c00_1.setInput(in0.getOutput());
		c10_1.setInput(in1.getOutput());
		c20_1.setInput(in2.getOutput());
		c30_1.setInput(in3.getOutput());
		//hiddenlayers
		hl1[0].calcOut();
		hl2[0].calcOut();
		//output layer
		output = outputs[0].calcOut();
		System.out.println("out: " + output);
		
		/*Mangler:
		 * træning
		 * save og load af vægte
		 * udtræk af data
		 */
	}
}
