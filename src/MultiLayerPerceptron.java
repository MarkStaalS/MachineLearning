package src;

import java.util.Arrays;

public class MultiLayerPerceptron {
	public static void main(String[] args) {
		
		connection c0 = new connection();
		connection c1 = new connection();
		connection c2 = new connection();
		connection c3 = new connection();
		
		c0.set_w(0.2);
		c1.set_w(0.2);
		c2.set_w(0.2);
		c3.set_w(0.2);
		
		c0.setInput(1);
		c1.setInput(1);
		c2.setInput(1);
		c3.setInput(1);
		
		c0.set_n(0.2);
		c1.set_n(0.2);
		c2.set_n(0.2);
		c3.set_n(0.2);
		
		c0.target(1);
		c1.target(1);
		c2.target(1);
		c3.target(1);

		neuron n1 = new neuron(0);		
		
		n1.addConnection(c0);
		n1.addConnection(c1);
		n1.addConnection(c2);
		n1.addConnection(c3);
		
		n1.calcOut();
		System.out.println("z " + n1.calcOut());
		
		c0.update_w();
		c1.update_w();
		c2.update_w();
		c3.update_w();	
		
		n1.activationFunc();
		System.out.println(n1.activationFunc());
		
	}

}
