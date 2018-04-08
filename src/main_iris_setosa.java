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
import java.util.Collections;
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
		 * TODO Missing :
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
		 * TODO neurons updated, now we only have one type
		 * TODO modyfying and testing new network 
		 */
		neuron_input[] inputs = createInputLayer(4);
		neuron[] hl1 = createHiddenLayer(4);
		neuron[] hl2 = createHiddenLayer(4);
		neuron[] outputs = createHiddenLayer(3);
		/*
		 * establish connections:
		 */
		Random r = new Random();
		double rangeMin = -1;
		double rangeMax = 1;
		/*
		 * connect input layer and first hidden layer
		 */
		int ctr = 0;
		connection[] c_1 = new connection[inputs.length * hl1.length];
		for (int i = 0; i < inputs.length; i++) {
			for (int j = 0; j < hl1.length; j++, ctr++) {
				c_1[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				hl1[i].addInputConnection(c_1[ctr]);
			}
		}

		/*
		 * TODO connect final layer and output layer
		 */
		ctr = 0;
		connection[] c_3 = new connection[hl2.length * outputs.length];
		for (int i = 0; i < hl2.length; i++) {
			for (int j = 0; j < outputs.length; j++, ctr++) {
				c_3[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				outputs[ctr % outputs.length].addConnection(c_3[ctr]);
				hl2[i].addOutputConnection(c_3[ctr]);
			}
		}
		/*
		 * connect hl1 and hl2
		 */
		ctr = 0;
		connection[] c_2 = new connection[hl1.length * hl2.length];
		for (int i = 0; i < hl1.length; i++) {
			for (int j = 0; j < hl2.length; j++, ctr++) {
				/*
				 * add input connection
				 */
				c_2[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				hl2[i].addInputConnection(c_2[ctr]);
				/*
				 * add output connection 
				 */
				hl1[ctr % hl1.length].addOutputConnection(c_2[ctr]);			
			}
		}
		/*
		 * We now have our full network
		 */
	
		/*
		 * Our traning set has 150 lines of data
		 */
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
		/*
		 * normalize; find min and max
		 */
		norm norm = new norm();
		double[][] rMinMax = norm.setupNorm(listOfSt);
		double[] min = new double[4];
		double[] max = new double[4];
		for (int i = 0; i < 4; i++) {
			min[i] = rMinMax[0][i];
			max[i] = rMinMax[1][i];
		}
			
		//System.out.println("max: " + Arrays.toString(max) + " min: " + Arrays.toString(min));
		/*
		 *Shufling data set 
		 */
		Collections.shuffle(listOfSt);
		
		int batch_size = 5;
		double[][] e_out = new double[batch_size][outputs.length];
		double[] error = new double[outputs.length];
		double e = 0;
		int cnt = 0;
		int stCnt = 0;	

		cost cost = new cost();
		
		int epoch_max = 20;
		for (int epoch = 0; epoch < epoch_max; epoch++, stCnt++) {
			/*
			 * Segmentation of data
			 * 0-49   = Setosa
			 * 50-99  = Versicolor
			 * 100-149 = Virginica
			 * Missing augmentation
			 */
			
			/*
			 * String count
			 */
			if (stCnt == listOfSt.size()) {
				stCnt = 0;
			}
			//System.out.println("Input: " + listOfSt.get(stCnt));	
			/*
			 * get string of data
			 */
			String x = (String) listOfSt.get(0);
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
				 * normalize data
				 */
				conv_double = (conv_double - min[j]) / (max[j] - min[j]);
				/*
				 * set input from 0 to 3 (0,1,2,3)
				 */
				inputs[j].setInput(conv_double);
				//System.out.print("inp " + conv_double + " ");
			}
			//System.out.println("");
			
			/*
			 * feeding input from input layer to 1st set of connections
			 */
			for (int i = 0; i < c_1.length; i++) {
				for (int j = 0; j < inputs.length; j++) {
					c_1[i].setInput(inputs[j].getOutput());
				}
			}
			/*
			 * hiddenlayers calc out
			 */
			for (int i = 0; i < hl1.length; i++) 
				hl1[i].calcOut();
			
			for (int i = 0; i < hl2.length; i++) 
				hl2[i].calcOut();

			for (int i = 0; i < outputs.length; i++) 
				outputs[i].calcOut();
			/*
			 * set target
			 */
			double[] tA = setTarget(x);
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
				
				for(int i = 0; i < outputs.length; i++) {
					outputs[i].update_w( error[i]);
				}
			}
			
			/*Print
			 * Target for: on_ 0 , on_1 , on_2
			 * Output for: on_ 0 , on_1 , on_2
			 * We have a seperate target for each output 0 or 1
			 */
			System.out.printf("\nTarget %d: on_0\ton_1\ton_2\n", epoch);
			System.out.printf("\t%.1f\t%.1f\t%.1f\n",tA[0],tA[1],tA[2] );
			//System.out.println("\t on_0" + "\t on_1" + "\t on_2" );
			//System.out.println("\t" + outputs[0].calcOut() + "\t" + outputs[1].calcOut() + "\t" + outputs[2].calcOut());
			
			//TODO 
			double sm[] = softMax.getSoftMax(outputs);
			for (int i = 0; i < outputs.length; i++) {
				System.out.printf("\t%.3f",sm[i]);
			}

			/*
			 * cost function
			 */
			//System.out.println("cost: " + cost.getCost(outputs, tA));
			/* 
			 * update weights
			 */
			/*
			 * backPropagation
			 * Output layer error / cost
			 */
			/*
			 * Calc hiddenlayer cost
			 */
			
			
		}
		/*
		System.out.println(Arrays.deepToString(e_out));
		System.out.println(Arrays.toString(error));
		*/
	}
	public static double[] setTarget(String x) {
		double target_array[] = new double[3];
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
		neuron[] hl = new neuron[size];
		for (int i = 0; i < size; i++) 
			hl[i] = new neuron(0);
		return hl;
	}
	
	public static neuron_input[] createInputLayer(int size) {
		neuron_input[] inputs = new neuron_input[size];
		for (int i = 0; i < size; i++)
			inputs[i] = new neuron_input();
		return inputs;
	}
	
	public static neuron_output[] createOutputLayer(int size) {
		neuron_output[] outputs = new neuron_output[size];
		for (int i = 0; i < size; i++)
			outputs[i] = new neuron_output(0);
		return outputs;
	}
}
