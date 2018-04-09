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
		Random r = new Random();
		double rangeMin = 0;
		double rangeMax = 1;
		/*
		 * create layers
		 * TODO neurons updated, now we only have one type
		 * TODO modyfying and testing new network 
		 * TODO weight decay
		 */
		neuron[] inputs = createHiddenLayer(2, 0);
		neuron[] hl1 = createHiddenLayer(2, 0);
		neuron[] hl2 = createHiddenLayer(2, 0);
		neuron[] outputs = createHiddenLayer(2, 0);
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
				c_1[ctr] = new connection(0.1, n);
				//c_1[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
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
				c_3[ctr] = new connection(0.3, n);
				//c_3[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
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
				c_2[ctr] = new connection(0.2, n);
				//c_2[ctr] = new connection(rangeMin + (rangeMax - rangeMin) * r.nextDouble(), n);
				hl2[i].addInputConnection(c_2[ctr]);
				/*
				 * add output connection 
				 */
				hl1[ctr % hl1.length].addOutputConnection(c_2[ctr]);			
			}
		}
		/*
		System.out.println("hl1 neuron: input connections" + hl1[0].inputConnections.toString() + " output connections" + hl1
				[0].outputConnections.toString());
		System.out.println("hl1 neuron: input connections" + hl1[1].inputConnections.toString() + " output connections" + hl1
				[1].outputConnections.toString());
		System.out.println("hl2 neuron: input connections" + hl2[0].inputConnections.toString() + " output connections" + hl2
				[0].outputConnections.toString());
		System.out.println("output neuron: input connections" + outputs[0].inputConnections.toString() + " output connections" + outputs
				[0].outputConnections.toString());
		*/
		inputs[0].setInput(0);
		inputs[1].setInput(1);
		
		c_1[0].setInput(inputs[0].getOutput());
		c_1[1].setInput(inputs[1].getOutput());
		c_1[2].setInput(inputs[0].getOutput());
		c_1[3].setInput(inputs[1].getOutput());
		
		for (int i = 0; i < hl1.length; i++) {
			hl1[i].calcOutHidden();
			hl2[i].calcOutHidden();
		}
		
		System.out.println("weight:");
		System.out.println("layer:\t1\t2\t3");
		System.out.printf("c[0]\t%.3f\t%.3f\t%.3f\n",c_1[0].w,c_2[0].w,c_3[0].w);
		System.out.printf("c[1]\t%.3f\t%.3f\t%.3f\n",c_1[1].w,c_2[1].w,c_3[1].w);
		System.out.printf("c[2]\t%.3f\t%.3f\t%.3f\n",c_1[2].w,c_2[2].w,c_3[2].w);
		System.out.printf("c[3]\t%.3f\t%.3f\t%.3f\n\n",c_1[3].w,c_2[3].w,c_3[3].w);
		
		System.out.println("c_ out\t1\t2\t3");
		System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[0].getOutput(),c_2[0].getOutput(),c_3[0].getOutput());
		System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[1].getOutput(),c_2[1].getOutput(),c_3[1].getOutput());
		System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[2].getOutput(),c_2[2].getOutput(),c_3[2].getOutput());
		System.out.printf("\t%.3f\t%.3f\t%.3f\n\n",c_1[3].getOutput(),c_2[3].getOutput(),c_3[3].getOutput());
		
		System.out.println("hl out\thl1\thl2\tout");
		for(int i = 0; i < 2; i++) {
			System.out.printf("\t%.3f\t%.3f\t%.3f\n",hl1[i].z,hl2[i].z,outputs[i].calcOut());
		}
		
		for (int i = 0; i < 2; i++) {
			outputs[i].calcErrorOut(1);
		}
		for (int i = 0; i < 2; i++) {
			hl2[i].calcErrorFactorHidden();
		}
		for (int i = 0; i < 2; i++) {
			hl1[i].calcErrorFactorHidden();
		}

		System.out.println("\nBackPropagetion");
		System.out.println("delta\thl1\thl2\tout");
		for (int i = 0; i < 2; i++) {
			
			System.out.printf("\t%.3f\t%.3f\t%.3f\n",hl1[i].delta,hl2[i].delta,outputs[i].delta);
		}
		for(int i = 0; i < 2; i++) {
			outputs[i].updateFreeParameters(n);
		}
		for(int i = 0; i < 2; i++) {
			hl2[i].updateFreeParameters(n);
		}
		for(int i = 0; i < 2; i++) {
			hl1[i].updateFreeParameters(n);
		}
		System.out.println("c_w");
		for(int i = 0; i < 4; i++) {
			System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[i].w,c_2[i].w,c_3[i].w);
		}
		
		
		
		for (int j = 0; j < 0; j++) {
			System.out.println("\nepoch: " + j);
			inputs[0].setInput(0);
			inputs[1].setInput(1);
			for ( int i = 0; i < hl1.length; i++) {
				c_1[i].setInput(inputs[i].getOutput());
				hl1[i].calcOutHidden();
				hl2[i].calcOutHidden();
			}
			
			System.out.println("weight:");
			System.out.println("layer:\t1\t2\t3");
			System.out.printf("c[0]\t%.3f\t%.3f\t%.3f\n",c_1[0].w,c_2[0].w,c_3[0].w);
			System.out.printf("c[1]\t%.3f\t%.3f\t%.3f\n",c_1[1].w,c_2[1].w,c_3[1].w);
			System.out.printf("c[2]\t%.3f\t%.3f\t%.3f\n",c_1[2].w,c_2[2].w,c_3[2].w);
			System.out.printf("c[3]\t%.3f\t%.3f\t%.3f\n\n",c_1[3].w,c_2[3].w,c_3[3].w);
			
			System.out.println("c_ out\t1\t2\t3");
			System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[0].getOutput(),c_2[0].getOutput(),c_3[0].getOutput());
			System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[1].getOutput(),c_2[1].getOutput(),c_3[1].getOutput());
			System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[2].getOutput(),c_2[2].getOutput(),c_3[2].getOutput());
			System.out.printf("\t%.3f\t%.3f\t%.3f\n\n",c_1[3].getOutput(),c_2[3].getOutput(),c_3[3].getOutput());
			
			System.out.println("hl out\thl1\thl2\tout\tsm\ttarget");
			for(int i = 0; i < 2; i++) {
				double sm[] = softMax.getSoftMax(outputs);
				System.out.printf("\t%.3f\t%.3f\t%.3f\t%.3f\t%.1f\n",hl1[i].calcOut(),hl2[i].calcOut(),outputs[i].calcOut(), sm[i],inputs[i].getOutput());
			}
			outputs[0].calcErrorOut(0);
			outputs[1].calcErrorOut(1);
			for (int i = 0; i < 2; i++) {
				hl2[i].calcErrorFactorHidden();
			}
			for (int i = 0; i < 2; i++) {
				hl1[i].calcErrorFactorHidden();
			}
			System.out.println("\nBackPropagetion");
			System.out.println("delta\thl1\thl2\tout");
			for (int i = 0; i < 2; i++) {
				
				System.out.printf("\t%.3f\t%.3f\t%.3f\n",hl1[i].delta,hl2[i].delta,outputs[i].delta);
			}
			for(int i = 0; i < 2; i++) {
				outputs[i].updateFreeParameters(n);
			}
			for(int i = 0; i < 2; i++) {
				hl2[i].updateFreeParameters(n);
			}
			for(int i = 0; i < 2; i++) {
				hl1[i].updateFreeParameters(n);
			}
			System.out.println("c_w");
			for(int i = 0; i < 4; i++) {
				System.out.printf("\t%.3f\t%.3f\t%.3f\n",c_1[i].w,c_2[i].w,c_3[i].w);
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
		
		File file = new File("C:\\Users\\mark\\Desktop\\iris.data.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList listOfSt = new ArrayList();
		/*
		 * Transforms file to list of strings
		 
		String st;
		while ((st = br.readLine()) != null) {
			listOfSt.add(st);
		}
		String [] y = new String[4];
		/*
		 * normalize; find min and max
		 
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
		int cnt = 0;
		int stCnt = 0;	

		cost cost = new cost();
		
		int epoch_max = 2;
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
			 *
			if (stCnt == listOfSt.size()) {
				stCnt = 0;
			}
			//System.out.println("Input: " + listOfSt.get(stCnt));	
			/*
			 * get string of data
			 *
			String x = (String) listOfSt.get(0);
			/*
			 * split string
			 *
			y = x.split(",");
			
			for ( int j = 0; j < 4; j++) {
				/*
				 * convert from string to double
				 *
				double conv_double = Double.parseDouble(y[j]);
				/*
				 * normalize data
				 *
				conv_double = (conv_double - min[j]) / (max[j] - min[j]);
				/*
				 * set input from 0 to 3 (0,1,2,3)
				 *
				inputs[j].setInput(conv_double);
				//System.out.print("inp " + conv_double + " ");
			}
			//System.out.println("");
			 * 
			 *
			double[] x = {0,0,1};
			for (int i = 0; i < outputs.length; i++) {
				inputs[i].setInput(x[i]);
			}
			/*
			 * feeding input from input layer to 1st set of connections
			 *
			for (int i = 0; i < c_1.length; i++) {
				for (int j = 0; j < inputs.length; j++) {
					c_1[i].setInput(inputs[j].getOutput());
				}
			}
			/*
			 * hiddenlayers calc out
			 *
			for (int i = 0; i < hl1.length; i++) 
				hl1[i].calcOutHidden();
			
			for (int i = 0; i < hl2.length; i++) 
				hl2[i].calcOutHidden();

			for (int i = 0; i < outputs.length; i++) 
				outputs[i].calcOutOut();
			/*
			 * set target
			 *
			//double[] tA = setTarget(x);
			double[] tA = x;
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
			 *
			System.out.printf("\nEpoch\t %d:\n", epoch);
			System.out.printf("Target: \t%.1f\t%.1f\t%.1f\n",tA[0],tA[1],tA[2] );
			//System.out.println("\t on_0" + "\t on_1" + "\t on_2" );
			System.out.printf("Output: \t %.3f \t %.3f \t %.3f\n", outputs[0].calcOutOut(), outputs[1].calcOutOut(), outputs[2].calcOutOut());
			System.out.print("Softmax: ");
			
			//TODO 
			double sm[] = softMax.getSoftMax(outputs);
			for (int i = 0; i < outputs.length; i++) {
				System.out.printf("\t%.3f",sm[i]);
			}
			
			System.out.println("");
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
			 *
			for (int i = 0; i < outputs.length; i++) {
				outputs[i].calcErrorOut(x[i]);
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
	public static neuron[] createHiddenLayer(int size, double bias) {
		neuron[] hl = new neuron[size];
		for (int i = 0; i < size; i++) 
			hl[i] = new neuron(bias);
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
