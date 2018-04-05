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
		 * We have 4 inputs: SepalLength, SepalWidth, PetalLength, PetalWidth
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
		 * establish connections:
		 */
		Random r = new Random();
		double rangeMin = -1;
		double rangeMax = 1;
		int cnt = 0;

		connection[] c_1 = connectionsInputToFirstHiddenLayer(r, inputs, hl1, rangeMin, rangeMax, n);
		connection[] c_2 = connectionsHiddenLayer(r, hl1, hl2, rangeMin, rangeMax, n);
		connection[] c_3 = connectionsHiddenLayerToOutput(r, hl2, outputs, rangeMin, rangeMax, n);
		/*
		 * set output connections for hl1
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
		 * set output connections for hl2
		 */
		cnt = 0;
		for ( int i = 0; i < hl2.length; i++) {
			for ( int j = 0; j < outputs.length; j++) {
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
	
		/*
		 * Our traning set has 150 lines of data
		 */
		int epoch_max = 0;
		/*
		 * Read data
		 *Loads txt file
		 */
		File file = new File("C:\\Users\\mark\\Desktop\\iris.data.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList listOfSt = new ArrayList();
		/*
		 * Transforms file to list of strings
		 */
		String st;
		while ((st = br.readLine()) != null) {
			listOfSt.add(st);
		}
		String [] y = new String[4];
		
		int batch_size = 2;
		double[][] e_out = new double[batch_size][outputs.length];
		double[] error = new double[outputs.length];
		double e = 0;
		cnt = 0;
		
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
			String x = (String) listOfSt.get(epoch);
			/*
			 * split string
			 */
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
			for (int ctr = 0; ctr < hl1[0].outputConnections.size(); ctr++) {
				hl1[ctr].calcOut();
			}
			for (int ctr = 0; ctr < hl2[0].outputConnections.size(); ctr++) {
				hl2[ctr].calcOut();
			}
			
			/*
			 * set target
			 */
			int[] tA = setTarget(x);
			
			/*
			 * output layer
			//double output = outputs[0].calcOut();
			
			/*
			 * batch size 5
			 
			for (int i = 0; i < outputs.length; i++) {
				e_out[cnt][i] = tA[i] - outputs[i].calcOut();
				error[i] += e_out[cnt][i];
			}
			cnt ++;
			if (cnt == 5) {
				System.out.println("*******************************");
				for(int i = 0; i < error.length; i++) {
					error[i] = error[i] / batch_size;
				}
				cnt = 0;
				/*
				 * update weights???
				 * how do you use this, if the target varies then how to you update the wieghts for 5 epochs if they have different targets ?? 
				 
				for(int i = 0; i < outputs.length; i++) {
					outputs[i].update_w( error[i]);
				}
			}
			/*Print
			 * Target for: on_ 0 , on_1 , on_2
			 * Output for: on_ 0 , on_1 , on_2
			 * We have a seperate target for each output 0 or 1
			 */
			System.out.println("Target: on_0" + "\t on_1" + "\t on_2" );
			System.out.println("\t" + tA[0] + "\t" + tA[1] + "\t" + tA[2]);
			System.out.println("\t on_0" + "\t on_1" + "\t on_2" );
			System.out.println("\t" + outputs[0].calcOut() + "\t" + outputs[1].calcOut() + "\t" + outputs[2].calcOut());
			
			
			for (int i = 0; i < outputs.length; i++) {
				outputs[i].update_w(tA[i], outputs[i].calcOut());
			}
			
			
			for (int i = 0; i < hl1.length; i++) {
				hl1[i].update_w();
			}
			/*
			 * problem hvad skal der gøres ved update når der ikke er lige mange in og out
			 */
			//System.out.println(hl2[0].inputConnections.size());
			//System.out.println(hl2[3].outputConnections.get(0).delta);
			//System.out.println(hl1[0].inputConnections.size());
			//System.out.println(hl1[3].outputConnections.get(0).delta);
			hl2[3].update_w();
			
			for (int i = 0; i < hl2[0].inputConnections.size(); i++) {
				//hl2[i].update_w();	
			}
			
			double sm[] = softMax.getSoftMax(outputs);
			for (int i = 0; i < outputs.length; i++) {
				System.out.print("Sm: " + sm[i] + " ");
			}
			System.out.println("");
		}
		/*
		System.out.println(Arrays.deepToString(e_out));
		System.out.println(Arrays.toString(error));
		*/
	}
	/*
	 * setting targets
	 */
	public static int[] setTarget(String x) {
		int target_array[] = new int[3];
		if (x.contains("Iris-setosa")) {
			target_array[0] = 1;
			target_array[1] = 0;
			target_array[2] = 0;
		} 
		else if (x.contains("Iris-versicolor")) {
			target_array[0] = 0;
			target_array[1] = 1;
			target_array[2] = 0;
		} 
		else if (x.contains("Iris-virginica")) {
			target_array[0] = 0;
			target_array[1] = 0;
			target_array[2] = 1;
		}
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
	public static connection[] connectionsInputToFirstHiddenLayer(Random r, neuron_input[] inputs, neuron[] hl1, double rangeMin, double rangeMax, double n) {
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
		return c_1;
	}
	public static connection[] connectionsHiddenLayer(Random r, neuron[] hl1, neuron[] hl2, double rangeMin, double rangeMax, double n) {
		int cnt = 0;
		/*
		 * create input connections for hiddenlayers
		 */	
		connection[] c = new connection[hl1.length * hl2.length];
		for ( int i = 0; i < hl1.length; i++) {
			for ( int j = 0; j < hl2.length; j++) {
				/*
				 * create connection
				 */
				c[cnt] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				/*
				 * add input conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 */
				hl2[i].addInputConnection(c[cnt]);
			}
			cnt ++;
		}
		return c;
	}
	public static connection[] connectionsHiddenLayerToOutput (Random r, neuron[] hl2, neuron_output[] outputs, double rangeMin, double rangeMax, double n) {
		int cnt = 0;
		/*
		 * create input connections for output layer
		 */	
		connection[] c = new connection[hl2.length * outputs.length];
		for ( int i = 0; i < outputs.length; i++) {
			for ( int j = 0; j < hl2.length; j++) {
				/*
				 * create connection
				 */
				c[cnt] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				/*
				 * add input conection "j" to neuron "i", ammount of connections pr neuron = amount of neurons in previous layer
				 */
				outputs[i].addConnection(c[cnt]);
			}
			cnt ++;
		}
		return c;
	}
}
