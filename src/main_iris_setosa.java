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
		 * Save w as .txt file
		 * PrintWriter writer = new PrintWriter("C:\\Users\\mark\\Desktop\\w.txt", "UTF-8");
		 * writer.println(c0.w);
		 * writer.println(c1.w);
		 * writer.close();
		 * 
		 * Missing traning:
		 * Save and loading of weights
		 * input
		 * epoch
		 * batch size and implementation
		 * Division of data
		 * Final control
		*/
		double n = 0.01;
		//input layer
		neuron_input in0 = new neuron_input();
		neuron_input in1 = new neuron_input();
		neuron_input in2 = new neuron_input();
		neuron_input in3 = new neuron_input();
		neuron_input[] inputs = {in0, in1, in2, in3};
		
		//1st hiddenlayer
		neuron n0_1 = new neuron(0);
		neuron n1_1 = new neuron(0);	
		neuron n2_1 = new neuron(0);	
		neuron n3_1 = new neuron(0);	
		neuron[] hl1 = {n0_1, n1_1, n2_1, n3_1};
		
		Random r = new Random();
		double rangeMin = 0;
		double rangeMax = 1;
		
		
		//connect input to hidden layer 1
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
		
		//connection hiddenlayer 1 to 2, output connections
		//n0_1
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
		

		//add hiddenalyer 1 connections
		//n0_1 input
		n0_1.addInputConnection(c0_1);
		n0_1.addInputConnection(c1_1);
		n0_1.addInputConnection(c2_1);
		n0_1.addInputConnection(c3_1);
		//n0_1 output
		n0_1.addOutputConnection(c0_2);
		n0_1.addOutputConnection(c1_2);
		n0_1.addOutputConnection(c2_2);
		n0_1.addOutputConnection(c3_2);
		
		//n1_1 input
		n1_1.addInputConnection(c4_1);
		n1_1.addInputConnection(c5_1);
		n1_1.addInputConnection(c6_1);
		n1_1.addInputConnection(c7_1);
		//n1_1 output
		n1_1.addOutputConnection(c4_2);
		n1_1.addOutputConnection(c5_2);
		n1_1.addOutputConnection(c6_2);
		n1_1.addOutputConnection(c7_2);
		
		//n2_1 input
		n2_1.addInputConnection(c8_1);
		n2_1.addInputConnection(c9_1);
		n2_1.addInputConnection(c10_1);
		n2_1.addInputConnection(c11_1);
		//n2_1 output
		n2_1.addOutputConnection(c8_2);
		n2_1.addOutputConnection(c9_2);
		n2_1.addOutputConnection(c10_2);
		n2_1.addOutputConnection(c11_2);
		
		//n3_1 input
		n3_1.addInputConnection(c12_1);
		n3_1.addInputConnection(c13_1);
		n3_1.addInputConnection(c14_1);
		n3_1.addInputConnection(c15_1);
		//n3_1 output
		n3_1.addOutputConnection(c12_2);
		n3_1.addOutputConnection(c13_2);
		n3_1.addOutputConnection(c14_2);
		n3_1.addOutputConnection(c15_2);
		//end of 1st layer
		
		
		//2nd layer
		neuron n0_2 = new neuron(0);
		neuron n1_2 = new neuron(0);
		neuron n2_2 = new neuron(0);
		neuron n3_2 = new neuron(0);
		neuron[] hl2 = {n0_2, n1_2, n2_2, n3_2};
		
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
		
		//add hiddenalyer 2 connections
		//n0_2 input
		n0_2.addInputConnection(c0_2);
		n0_2.addInputConnection(c4_2);
		n0_2.addInputConnection(c8_2);
		n0_2.addInputConnection(c12_2);
		//n0_2 output
		n0_2.addOutputConnection(c0_3);
		n0_2.addOutputConnection(c1_3);
		n0_2.addOutputConnection(c2_3);

		
		//n1_2 input
		n1_2.addInputConnection(c1_2);
		n1_2.addInputConnection(c5_2);
		n1_2.addInputConnection(c9_2);
		n1_2.addInputConnection(c13_2);
		//n1_2 output
		n1_2.addOutputConnection(c4_3);
		n1_2.addOutputConnection(c5_3);
		n1_2.addOutputConnection(c6_3);
		
		//n2_2 input
		n2_2.addInputConnection(c2_2);
		n2_2.addInputConnection(c6_2);
		n2_2.addInputConnection(c10_2);
		n2_2.addInputConnection(c14_2);
		//n2_2 output
		n2_2.addOutputConnection(c8_3);
		n2_2.addOutputConnection(c9_3);
		n2_2.addOutputConnection(c10_3);
		
		//n3_2 input
		n3_2.addInputConnection(c3_2);
		n3_2.addInputConnection(c7_2);
		n3_2.addInputConnection(c11_2);
		n3_2.addInputConnection(c15_2);
		//n3_2 output
		n3_2.addOutputConnection(c12_3);
		n3_2.addOutputConnection(c13_3);
		n3_2.addOutputConnection(c14_3);
		
		//output layer
		neuron_output on_0 = new neuron_output(0);
		neuron_output on_1 = new neuron_output(0);
		neuron_output on_2 = new neuron_output(0);
		neuron_output[] outputs = {on_0, on_1, on_2};
		//on_0
		on_0.addConnection(c0_3);
		on_0.addConnection(c4_3);
		on_0.addConnection(c8_3);
		on_0.addConnection(c12_3);
		//on_1
		on_1.addConnection(c1_3);
		on_1.addConnection(c5_3);
		on_1.addConnection(c9_3);
		on_1.addConnection(c13_3);
		//on_2
		on_2.addConnection(c2_3);
		on_2.addConnection(c6_3);
		on_2.addConnection(c10_3);
		on_2.addConnection(c14_3);
		//Here we have our full network
		
		
		/*
		 * Full network needs to be controled
		 */

		//Our traning set has 150 lines of data
		int epoch_max = 5;
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
		double target = 0;
		String [] y = new String[4];
		int teller_output = 0;
		
		for (int epoch = 0; epoch < epoch_max; epoch ++) {
			/*
			 * Segmentation of data
			 * 0-49   = Setosa
			 * 50-99  = Versicolor
			 * 10-149 = Virginica
			 * Ammount of test data
			 * Missing normalizationg and perhaps scrambling and augmentation
			 */
			if (epoch == 49 || epoch == 99 || epoch == 149) {
				test[i] = (String) listOfSt.get(epoch);
				i++;
			} else {
				//get string of data
				String x; 
				x = (String) listOfSt.get(epoch);
				//split string
				y = x.split(",");
				
				for ( int j = 0; j < 4; j++) {
					//convert from string to double
					double conv_double = Double.parseDouble(y[j]);
					//set input from 0 to 3 (0,1,2,3)
					inputs[j].setInput(conv_double);
				}
				
				//set target
				if (x.contains("Iris-setosa")) {
					target = 0;
				} 
				else if (x.contains("Iris-versicolor")) {
					target = 1;
				} 
				else if (x.contains("Iris-virginica")) {
					target = 2;
				}
				
				//feeding input from input layer to 1st set of connections
				c0_1.setInput(in0.getOutput());
				c1_1.setInput(in1.getOutput());
				c2_1.setInput(in2.getOutput());
				c3_1.setInput(in3.getOutput());
				c4_1.setInput(in0.getOutput());
				c5_1.setInput(in1.getOutput());
				c6_1.setInput(in2.getOutput());
				c7_1.setInput(in3.getOutput());
				c8_1.setInput(in0.getOutput());
				c9_1.setInput(in1.getOutput());
				c10_1.setInput(in2.getOutput());
				c11_1.setInput(in3.getOutput());
				c12_1.setInput(in0.getOutput());
				c13_1.setInput(in1.getOutput());
				c14_1.setInput(in2.getOutput());
				c15_1.setInput(in3.getOutput());
				//checking flower and setting target

				//hiddenlayers
				hl1[0].calcOut();
				hl1[1].calcOut();
				hl1[2].calcOut();
				hl1[3].calcOut();
				
				hl2[0].calcOut();
				hl2[1].calcOut();
				hl2[2].calcOut();
				hl2[3].calcOut();
				//output layer
				//double output = outputs[0].calcOut();
				
				/*
				if (epoch % 10 == 0 && epoch > 1) {
					//traning (backpropagation), every 10th epoch
					outputs[0].update_w(target, output);
					hl2[0].update_w();
					hl1[0].update_w();	
					System.out.print("update " + epoch + " ");
				}
				*/
				System.out.println("Target" + "\t on_0" + "\t on_1" + "\t on_2" );
				System.out.print(target + "\t" + outputs[0].calcOut());
				System.out.print("\t" + outputs[1].calcOut());
				System.out.println("\t" + outputs[2].calcOut());
				
				/*Online traning, fejl
				outputs[0].update_w(target, output);
				hl2[0].update_w();
				hl1[0].update_w();	
				*/
				
				
				
			}
		}	
		
		/*
		writer.println("Weights: 1st layer: ");
		writer.println(" " + "\tc00_1" + "\tc10_1" + "\tc20_1" + "\tc30_1");
		writer.println(" " + "\t" + c00_1.w + "\t" + c10_1.w + "\t" + c20_1.w + "\t" + c30_1.w );
		writer.println("Weights: 2nd layer: ");
		writer.println(" " + "\t" + c00_2.w);
		writer.println("Weights: 3rd layer: ");
		writer.println(" " + "\t" + c00_3.w);
		writer.println();
		writer.close();
		*/
	}
}
