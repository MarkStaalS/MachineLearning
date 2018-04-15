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
		double rangeMin = 0;
		double rangeMax = 1;
		Random r = new Random();
		int batchSize = 1; // get oof my stream NORMIES REEEEEEE!!!!!!
		int epoch_max = 1000;
		/*
		 * create layers
		 * TODO neurons updated, now we only have one type
		 * TODO modyfying and testing new network 
		 * TODO weight decay
		 */
		int[] topology = {4,10,10,3};
		
		neuron[] inputs  = createNeuronLayer(topology[0], (rangeMin + (rangeMax - rangeMin) * r.nextDouble()), topology[0]);
		neuron[] hl1 	 = createNeuronLayer(topology[1], (rangeMin + (rangeMax - rangeMin) * r.nextDouble()), topology[0] * topology[1]);
		neuron[] hl2 	 = createNeuronLayer(topology[2], (rangeMin + (rangeMax - rangeMin) * r.nextDouble()), topology[1] * topology[2]);
		neuron[] outputs = createNeuronLayer(topology[3], (rangeMin + (rangeMax - rangeMin) * r.nextDouble()), topology[2] * topology[3]);
		
		
		/*
		 * establish connections:
		 */
		/*
		 * connect input layer and first hidden layer
		 */
		int ctr = 0;
		connection[] c_1 = new connection[inputs.length * hl1.length];
		for (int i = 0; i < inputs.length; i++) {
			for (int j = 0; j < hl1.length; j++, ctr++) {
				//c_1[ctr] = new connection(0.1, n);
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
				//c_3[ctr] = new connection(0.3, n);
				c_3[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				outputs[ctr % outputs.length].addInputConnection(c_3[ctr]);
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
				//c_2[ctr] = new connection(0.2, n);
				c_2[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				hl2[i].addInputConnection(c_2[ctr]);
				/*
				 * add output connection 
				 */
				hl1[ctr % hl1.length].addOutputConnection(c_2[ctr]);			
			}
		}
		/*
		System.out.println("inp neuron: input connections" + inputs[0].inputConnections.toString() + " output connections" + inputs
				[0].outputConnections.toString());
		System.out.println("hl1 neuron: input connections" + hl1[0].inputConnections.toString() + " output connections" + hl1
				[0].outputConnections.toString());
		System.out.println("hl2 neuron: input connections" + hl2[0].inputConnections.toString() + " output connections" + hl2
				[0].outputConnections.toString());
		System.out.println("output neuron: input connections" + outputs[0].inputConnections.toString() + " output connections" + outputs
				[0].outputConnections.toString());
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
		 *
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
		//Collections.shuffle(listOfSt);
		/*
		int batch_size = 5;
		double[][] e_out = new double[batch_size][outputs.length];
		double[] error = new double[outputs.length];
		double e = 0;
		*/
		int cnt = 0;
		int stCnt = 0;	
		for (int epoch = 1; epoch <= epoch_max; epoch++, stCnt++) {
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
			String x = (String) listOfSt.get(stCnt);
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
				//conv_double = (conv_double - min[j]) / (max[j] - min[j]);
				/*
				 * set input from 0 to 3 (0,1,2,3)
				 */
				inputs[j].setInput(conv_double);
				//System.out.print("inp " + conv_double + " ");
			}
			
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
				hl1[i].feedForward();
			
			for (int i = 0; i < hl2.length; i++) 
				hl2[i].feedForward();

			for (int i = 0; i < outputs.length; i++) 
				outputs[i].calcOut();
			/*
			 * set target
			 */
			double[] tA = setTarget(x);
			

			/*Print
			 * Target for: on_ 0 , on_1 , on_2
			 * Output for: on_ 0 , on_1 , on_2
			 * We have a seperate target for each output 0 or 1
			 */
			System.out.printf("\nEpoch\t %d:\n", epoch);
			System.out.println(x);
			System.out.printf("Target: \t%.1f\t%.1f\t%.1f\n",tA[0],tA[1],tA[2] );
			//System.out.println("\t on_0" + "\t on_1" + "\t on_2" );
			System.out.printf("Output: \t %.3f \t %.3f \t %.3f\n", outputs[0].calcOut(), outputs[1].calcOut(), outputs[2].calcOut());
			System.out.print("Softmax: ");
			
			//TODO 
			double sm[] = softMax.getSoftMax(outputs);
			for (int i = 0; i < outputs.length; i++) {
				System.out.printf("\t%.2f",sm[i]);
			}
			
			/*
			 * backPropagation
			 */
			/*
			 * Calc hiddenlayer cost
			 */
			
			for (int i = 0; i < outputs.length; i++) {
				outputs[i].calcErrorOut(tA[i]);
				outputs[i].updateFreeParameters(n);
			}
			for (int i = 0; i < hl2.length; i++) {
				hl2[i].calcErrorFactorHidden();
				hl2[i].updateFreeParameters(n);
			}
			for (int i = 0; i < hl1.length; i++) {
				hl1[i].calcErrorFactorHidden();
				hl1[i].updateFreeParameters(n);
			}
			if (epoch % batchSize == 0) {
				for (int i = 0; i < outputs.length; i++)
					outputs[i].updateW(epoch);
				for (int i = 0; i < hl2.length; i++)
					hl2[i].updateW(epoch);
				for (int i = 0; i < hl1.length; i++)
					hl1[i].updateW(epoch);
			}
			
		}
		
		int error = 0;
		for (int k = 0; k < listOfSt.size(); k++) {
			/*
			 * Segmentation of data
			 * 0-49   = Setosa
			 * 50-99  = Versicolor
			 * 100-149 = Virginica
			 * Missing augmentation
			 */	
			/*
			 * get string of data
			 */
			String x = (String) listOfSt.get(k);
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
				//conv_double = (conv_double - min[j]) / (max[j] - min[j]);
				/*
				 * set input from 0 to 3 (0,1,2,3)
				 */
				inputs[j].setInput(conv_double);
				//System.out.print("inp " + conv_double + " ");
			}
			
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
				hl1[i].feedForward();
			
			for (int i = 0; i < hl2.length; i++) 
				hl2[i].feedForward();
			
			double[] op = new double[outputs.length];
			for (int i = 0; i < outputs.length; i++) 
				op[i] = outputs[i].calcOut();
			/*
			 * set target
			 */
			double[] tA = setTarget(x);
			double sm[] = softMax.getSoftMax(outputs);

			if(findMax(tA) != findMax(op))
				error++;
				
			
		}
		double success = 1 - (error / 150.0) ;
		System.out.println("\n\nSuccess rate:");
		System.out.println(success);
		System.out.println("\n error: " + error);
		System.out.println( listOfSt.size());
	}
	
	public static int findMax(double[] array) {
		int maxAt = 0;
		for (int i = 0; i < array.length; i++) {
		    maxAt = array[i] > array[maxAt] ? i : maxAt;
		}
		return maxAt;
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

	public static neuron[] createNeuronLayer(int size, double bias, int InputConnectionSize) {
		neuron[] l = new neuron[size];
		for (int i = 0; i < size; i++) {
			l[i] = new neuron(InputConnectionSize);
			l[i].setBias(0);
		}
		return l;
	}	

	
	public static connection[] createConnection(neuron[][] net, double rangeMax, double rangeMin, double n, Random r, int inputLayer) {
		int ctr = 0;
		connection[] c = new connection[net[inputLayer].length * net[inputLayer + 1].length];
		for (int i = 0; i < net[inputLayer].length; i++) {
			for (int j = 0; j < net[inputLayer + 1].length; j++, ctr++) {
				/*
				 * add input connection
				 */
				//c_2[ctr] = new connection(0.2, n);
				c[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				net[inputLayer + 1][i].addInputConnection(c[ctr]);
				/*
				 * add output connection 
				 */
				net[inputLayer][ctr % net[1].length].addOutputConnection(c[ctr]);			
			}
		}
		return c;
	}
}
