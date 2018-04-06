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
		

		/*connect input to hidden layer 1
		//n0_1
		connection c0_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c1_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c2_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c3_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n1_1
		connection c4_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c5_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c6_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c7_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n2_1
		connection c8_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c9_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c10_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c11_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n3_1
		connection c12_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c13_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c14_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c15_1 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		
		connection[] c_1 = {c0_1,c1_1,c2_1,c3_1,c4_1,c5_1,c6_1,c7_1,c8_1,c9_1,c10_1,c11_1,c12_1,c13_1,c14_1,c15_1};		
		connection hiddenlayer 1 to 2, output connections
		//n0_1
		 * 
		 */
		
		/*
		connection c0_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c1_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c2_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c3_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n1_1
		connection c4_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c5_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c6_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c7_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n2_1
		connection c8_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c9_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c10_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c11_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n3_1
		connection c12_2= new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c13_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c14_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c15_2 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection[] c_2 = {c0_2,c1_2,c2_2,c3_2,c4_2,c5_2,c6_2,c7_2,c8_2,c9_2,c10_2,c11_2,c12_2,c13_2,c14_2,c15_2};
		*/

		//add hiddenalyer 1 connections
		/*n0_1 input
		hl1[0].addInputConnection(c0_1);
		hl1[0].addInputConnection(c1_1);
		hl1[0].addInputConnection(c2_1);
		hl1[0].addInputConnection(c3_1);
		//n0_1 output
		hl1[0].addOutputConnection(c0_2);
		hl1[0].addOutputConnection(c1_2);
		hl1[0].addOutputConnection(c2_2);
		hl1[0].addOutputConnection(c3_2);
			
		//n1_1 input
		hl1[1].addInputConnection(c4_1);
		hl1[1].addInputConnection(c5_1);
		hl1[1].addInputConnection(c6_1);
		hl1[1].addInputConnection(c7_1);
		//n1_1 output
		hl1[1].addOutputConnection(c4_2);
		hl1[1].addOutputConnection(c5_2);
		hl1[1].addOutputConnection(c6_2);
		hl1[1].addOutputConnection(c7_2);
			
		//n2_1 input
		hl1[2].addInputConnection(c8_1);
		hl1[2].addInputConnection(c9_1);
		hl1[2].addInputConnection(c10_1);
		hl1[2].addInputConnection(c11_1);
		//n2_1 output
		hl1[2].addOutputConnection(c8_2);
		hl1[2].addOutputConnection(c9_2);
		hl1[2].addOutputConnection(c10_2);
		hl1[2].addOutputConnection(c11_2);
			
		//n3_1 input
		hl1[3].addInputConnection(c12_1);
		hl1[3].addInputConnection(c13_1);
		hl1[3].addInputConnection(c14_1);
		hl1[3].addInputConnection(c15_1);
		//n3_1 output
		hl1[3].addOutputConnection(c12_2);
		hl1[3].addOutputConnection(c13_2);
		hl1[3].addOutputConnection(c14_2);
		hl1[3].addOutputConnection(c15_2);
		end of 1st layer
		*/				
		/*
		//connection hiddenlayer 2 to output layer
		//n0_2 
		connection c0_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c1_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c2_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n1_2
		connection c4_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c5_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c6_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n2_2
		connection c8_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c9_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c10_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		//n3_2
		connection c12_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c13_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		connection c14_3 = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
		*/
		/*
		connection[] c_3 = {c0_3 ,c1_3 ,c2_3 ,c4_3 ,c5_3 ,c6_3 ,c8_3 ,c9_3 ,c10_3 ,c12_3 ,c13_3 ,c14_3};
		//add hiddenalyer 2 connections
		//n0_2 input
		hl2[0].addInputConnection(c0_2);
		hl2[0].addInputConnection(c4_2);
		hl2[0].addInputConnection(c8_2);
		hl2[0].addInputConnection(c12_2);
		//n0_2 output
		hl2[0].addOutputConnection(c0_3);
		hl2[0].addOutputConnection(c1_3);
		hl2[0].addOutputConnection(c2_3);
			
		//n1_2 input
		hl2[1].addInputConnection(c1_2);
		hl2[1].addInputConnection(c5_2);
		hl2[1].addInputConnection(c9_2);
		hl2[1].addInputConnection(c13_2);
		//n1_2 output
		hl2[1].addOutputConnection(c4_3);
		hl2[1].addOutputConnection(c5_3);
		hl2[1].addOutputConnection(c6_3);
		
		//n2_2 input
		hl2[2].addInputConnection(c2_2);
		hl2[2].addInputConnection(c6_2);
		hl2[2].addInputConnection(c10_2);
		hl2[2].addInputConnection(c14_2);
		//n2_2 output
		hl2[2].addOutputConnection(c8_3);
		hl2[2].addOutputConnection(c9_3);
		hl2[2].addOutputConnection(c10_3);
		
		//n3_2 input
		hl2[3].addInputConnection(c3_2);
		hl2[3].addInputConnection(c7_2);
		hl2[3].addInputConnection(c11_2);
		hl2[3].addInputConnection(c15_2);
		//n3_2 output
		hl2[3].addOutputConnection(c12_3);
		hl2[3].addOutputConnection(c13_3);
		hl2[3].addOutputConnection(c14_3);
		*/
		/*
		//on_0
		outputs[0].addConnection(c0_3);
		outputs[0].addConnection(c4_3);
		outputs[0].addConnection(c8_3);
		outputs[0].addConnection(c12_3);
		//on_1
		outputs[1].addConnection(c1_3);
		outputs[1].addConnection(c5_3);
		outputs[1].addConnection(c9_3);
		outputs[1].addConnection(c13_3);
		//on_2
		outputs[2].addConnection(c2_3);
		outputs[2].addConnection(c6_3);
		outputs[2].addConnection(c10_3);
		outputs[2].addConnection(c14_3);
		//Here we have our full network
		*/
		
		/*
		connection[] c_1 = connectionsInputToFirstHiddenLayer(r, inputs, hl1, rangeMin, rangeMax, n);
		connection[] c_2 = connectionsHiddenLayer(r, hl1, hl2, rangeMin, rangeMax, n);
		connection[] c_3 = connectionsHiddenLayerToOutput(r, hl2, outputs, rangeMin, rangeMax, n);
		/*
		 * set output connections for hl1
		 
		cnt = 0;
		for ( int i = 0; i < hl1.length; i++) {
			for ( int j = 0; j < hl2.length; j++) {
				/*
				 * add output conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 
				hl1[i].addOutputConnection(c_2[cnt]);
			}
			cnt ++;
		}
		/*
		 * set output connections for hl2
		 
		cnt = 0;
		for ( int i = 0; i < hl2.length; i++) {
			for ( int j = 0; j < outputs.length; j++) {
				/*
				 * add output conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 
				hl2[i].addOutputConnection(c_3[cnt]);
			}
			cnt ++;
		}
		*/
	}
}
