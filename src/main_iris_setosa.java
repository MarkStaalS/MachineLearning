package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class main_iris_setosa {
	public static void main(String[] args) throws IOException {
		/*
		 * Different classes:
		 * 0: Iris setosa
		 * 1: Iris versicolor
		 * 2: Iris virginica
		 * 
		 * We have 4 inputs:
		 * SepalLength
		 * SepalWidth
		 * PetalLength
		 * PetalWidth
		 * 
		 * PrintWriter writer = new PrintWriter("C:\\Users\\mark\\Desktop\\w.txt", "UTF-8");
		 * writer.println(c0.w);
		 * writer.println(c1.w);
		 * writer.close();
		 * 
		 * Missing :
		 * Save and loading of weights
		 * batch size and implementation
		 * Division of data
		 * Final control
		 * Posible layers as objects
		*/
		
		/*
		 * learning rate
		 */
		double n = 0.2;
		/*
		 * create layers
		 */
		neuron_input[] inputs = createInputLayer(4);
		neuron[] hl1 = createHiddenLayer(4);
		neuron[] hl2 = createHiddenLayer(4);
		neuron_output[] outputs = createOutputLayer(3);
		/*
		 * establish connections: intput to hl1
		 * create and connect
		 */
		Random r = new Random();
		double rangeMin = -1;
		double rangeMax = 1;
		
		int cnt = 0;
		/*
		 * create input connections for hl1
		 */
		connection[] c_1 = new connection[inputs.length * hl1.length];
		for ( int i = 0; i < inputs.length; i++) {
			for ( int j = 0; j < hl1.length; j++) {
				/*
				 * create connection
				 */
				c_1[cnt] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				/*
				 * add input conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 */
				hl1[i].addInputConnection(c_1[cnt]);
				cnt ++;
			}
		}
		/*
		 * create input connections for hl2
		 */	
		cnt = 0;
		connection[] c_2 = new connection[hl1.length * hl2.length];
		for ( int i = 0; i < hl1.length; i++) {
			for ( int j = 0; j < hl2.length; j++) {
				/*
				 * create connection
				 */
				c_2[cnt] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				/*
				 * add input conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 */
				hl2[i].addInputConnection(c_2[cnt]);
			}
			cnt ++;
		}
		/*
		 * create input connections for output layer
		 */	
		cnt = 0;
		connection[] c_3 = new connection[hl2.length * outputs.length];
		for ( int i = 0; i < outputs.length; i++) {
			for ( int j = 0; j < hl2.length; j++) {
				/*
				 * create connection
				 */
				c_3[cnt] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				/*
				 * add input conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 */
				outputs[i].addConnection(c_3[cnt]);
			}
			cnt ++;
		}
		
		/*
		 * create output connections for hl1
		 */
		cnt = 0;
		for ( int i = 0; i < hl1.length; i++) {
			for ( int j = 0; j < hl2.length; j++) {
				/*
				 * add output conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 */
				hl1[i].addOutputConnection(c_2[cnt]);
			}
			cnt ++;
		}
		/*
		 * create output connections for hl2
		 */
		cnt = 0;
		for ( int i = 0; i < outputs.length; i++) {
			for ( int j = 0; j < hl2.length; j++) {
				/*
				 * add output conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 */
				hl2[i].addOutputConnection(c_3[cnt]);
			}
			cnt ++;
		}
		/*
		 * Here we have our full network
		 */
	
		//Our traning set has 150 lines of data
		int epoch_max = 2;
		//Read data
		//Loads txt file
		File file = new File("C:\\Users\\mark\\Desktop\\iris.data.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList listOfSt = new ArrayList();
		//Transforms file to list of strings
		String st;
		while ((st = br.readLine()) != null) {
			listOfSt.add(st);
		}
		String [] test = new String[3];
		int i = 0;
		String [] y = new String[4];
		int teller_output = 0;
		
		for (int epoch = 0; epoch < epoch_max; epoch ++) {
			/*
			 * Segmentation of data
			 * 0-49   = Setosa
			 * 50-99  = Versicolor
			 * 100-149 = Virginica
			 * Amount of test data
			 * Missing normalizating and perhaps scrambling and augmentation
			 */
			
			/*
			 * get string of data
			 */
			String x; 
			x = (String) listOfSt.get(epoch);
			//split string
			y = x.split(",");
			
			for ( int j = 0; j < 4; j++) {
				/*
				 * convert from string to double
				 */
				double conv_double = Double.parseDouble(y[j]);
				/*
				 * set input from 0 to 3 (0,1,2,3)
				 */
				inputs[j].setInput(conv_double);
			}

			/*
			 * feeding input from input layer to 1st set of connections
			 */
			for (int ctr = 0; ctr < c_1.length; ctr++) {
				for (int ctr2 = 0; ctr2 < inputs.length; ctr2++) {
					c_1[ctr].setInput(inputs[ctr2].getOutput());
				}
			}

			/*
			 * hiddenlayers
			 */
			for (int ctr = 0; ctr < hl1.length; ctr++) {
				hl1[ctr].calcOut();
			}
			for (int ctr = 0; ctr < hl2.length; ctr++) {
				hl2[ctr].calcOut();
			}
			
			/*
			 * set target and update weights
			 */
			int[] tA = setTarget(x, outputs);
			
			//output layer
			//double output = outputs[0].calcOut();
			
			/*batch size 5
			if (epoch % 5 == 0 && epoch ) {
				//traning (backpropagation), every 10th epoch
				outputs[0].update_w(target, output);
				hl2[0].update_w();
				hl1[0].update_w();	
				System.out.print("update " + epoch + " ");
			}
			*/
			/*Print
			 * Target for: on_ 0 , on_1 , on_2
			 * Output for: on_ 0 , on_1 , on_2
			 * We have a seperate target for each output 0 or 1
			 */
			System.out.println("Target: on_0" + "\t on_1" + "\t on_2" );
			System.out.println("\t" + tA[0] + "\t" + tA[1] + "\t" + tA[2]);
			System.out.println("\t on_0" + "\t on_1" + "\t on_2" );
			System.out.println("\t" + outputs[0].calcOut() + "\t" + outputs[1].calcOut() + "\t" + outputs[2].calcOut());

			
			/*Online traning, fejl
			 * Remember! target should be set based on output neuron e.g [0 0 1] 
			outputs[0].update_w(target, output);
			hl2[0].update_w();
			hl1[0].update_w();	
			*/
			
			/*soft max layer
			 * create class, soft max
			 * output neurons as inputs
			 * outputs procentage value for each, sum = 1
			 */
			
		}	
	}
	/*
	 * setting targets
	 */
	public static int[] setTarget(String x, neuron_output[] outputs) {
		/*
		 * only used for print
		 */
		int target_array[] = new int[3];
		if (x.contains("Iris-setosa")) {
			outputs[0].update_w(1, outputs[0].calcOut());
			outputs[1].update_w(0, outputs[1].calcOut());
			outputs[2].update_w(0, outputs[2].calcOut());
			/*
			 * only used for print
			 */
			target_array[0] = 1;
			target_array[1] = 0;
			target_array[2] = 0;
		} 
		else if (x.contains("Iris-versicolor")) {
			outputs[0].update_w(0, outputs[0].calcOut());
			outputs[1].update_w(1, outputs[1].calcOut());
			outputs[2].update_w(0, outputs[2].calcOut());
			/*
			 * only used for print
			 */
			target_array[0] = 0;
			target_array[1] = 1;
			target_array[2] = 0;
		} 
		else if (x.contains("Iris-virginica")) {
			outputs[0].update_w(0, outputs[0].calcOut());
			outputs[1].update_w(0, outputs[1].calcOut());
			outputs[2].update_w(1, outputs[2].calcOut());
			/*
			 * only used for print
			 */
			target_array[0] = 0;
			target_array[1] = 0;
			target_array[2] = 1;
		}
		/*
		 * only used for print
		 */
		return target_array;
		
	}
	
	/*
	 * creating layers
	 */
	public static neuron[] createHiddenLayer(int size) {
		/*
		 * creates hidden layer
		*/ 
		neuron[] hl = new neuron[size];
		for (int i = 0; i < size; i++) {
			hl[i] = new neuron(0);
		}
		return hl;
	}
	public static neuron_input[] createInputLayer(int size) {
		/*
		 *  creates input layer
		 */
		neuron_input[] inputs = new neuron_input[size];
		for (int i = 0; i < size; i++) {
			inputs[i] = new neuron_input();
		}
		return inputs;
	}
	public static neuron_output[] createOutputLayer(int size) {
		/*
		 * creats output layer
		 */
		neuron_output[] outputs = new neuron_output[size];
		for (int i = 0; i < size; i++) {
			outputs[i] = new neuron_output(0);
		}
		return outputs;
	}
	
	/*
	 * creating connections
	 */
	public static void createInputConnections(int size) {
		
	}
}
