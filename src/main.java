package src;


public class main {
	public static void main(String[] args) {

		//input layer
		neuron_input in0 = new neuron_input();
		neuron_input in1 = new neuron_input();
		neuron_input in2 = new neuron_input();
		neuron_input in3 = new neuron_input();
		neuron_input[] inputs = {in0, in1, in2, in3};
		inputs[0].setInput(1);
		inputs[1].setInput(1);
		inputs[2].setInput(1);
		inputs[3].setInput(1);
		
		//1st hiddenlayer
		neuron n1 = new neuron(0);	
		neuron n2 = new neuron(0);
		neuron[] hl1 = {n1,n2};
		
		//output layer
		neuron_output on = new neuron_output(0);
		neuron_output[] outputs = {on};
		
		connection c0 = new connection();
		connection c1 = new connection();
		connection c2 = new connection();
		connection c3 = new connection();
		
		c0.set_w(0.2);
		c1.set_w(0.2);
		c2.set_w(0.2);
		c3.set_w(0.2);
		
		
		//target kan gøres smartere
		c0.set_n(0.2);
		c1.set_n(0.2);
		c2.set_n(0.2);
		c3.set_n(0.2);
		
		
		
		connection c21 = new connection();
		c21.set_w(0.2);
		c21.set_n(0.2);
		
		n1.addConnection(c0);
		n1.addConnection(c1);
		n1.addConnection(c2);
		n1.addConnection(c3);
		
		n2.addConnection(c21);
		
		//for loop, kør det igennem
		for(int i=0; i<10;i ++) {
			n1.calcOut();
			c21.setInput(n1.calcOut());
			double output = n2.calcOut();
		}
		//update
		
	}

}
