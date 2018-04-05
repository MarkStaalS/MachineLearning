package src;
import java.util.Arrays;
import java.util.Random;

public class test {
	static Random rnd = new Random();
	public static void main(String[] args) {
		
		double _bias = 0.2;
		double n = 0.2;
		int epoch =5;
		//example 4 inputs, 6 hidden layer neurons, 1 output
		//input vector
		double[][] x = {{1,2},{1,1},{1,3}};
		double[] target= {2,1,3};
		// len is the length of data/ inputs, here = 2
		int len = 2;
		int dataLen = 3;
		//amount of neurons in next layer
		int hln = 1;
		//weight matrix, can have as many rows as there are connections
		double w[][] = new double[len][hln]; 
		//z = result
		double z[] = new double[len];

		//test.traning(w, x, len, epoch, hln, target, dataLen, z, n);
		int i = 0;
		double g = 0;
		int j = 0;
		int row = 0;
		System.out.println(Math.exp(5));
		/*
		test.calcOutput(w, x, len, hln, z, i, j);
		g = test.actFunc(j, z, g);
		System.out.println(" + bias = "+ (g + _bias) );
		g = test.actFunc(1, z, g);
		System.out.println(" + bias = "+ (g + _bias) );
		}
		
	
	//Initialize weights
	public static double[][] intW(double [][] w,int len, int hln){
		//fills matrix for w with random numbers 
		for (int row = 0;  row<hln;  row++) {
			System.out.print("w\t"+ " \t");
			for(int colum = 0; colum<len; colum++) {
				w[colum][row]= rnd.nextDouble();
				System.out.print(w[colum][row]+"\t");
			}
			System.out.println(" ");
		}
		return w;
	}
	
	//initialize z as 0
	public static double[] intZ(int len, double[]z){
		for (int colum = 0;  colum<len;  colum++) {
			z[colum]= 0;
		}
		return z;
	}
	
	//calculating output z
	public static double [] calcOutput(double [][] w,double [][] x, int len, int hln, double [] z, int i, int j){
		// preforms matrix multiplication between x and w
		//selects first row and runs through the colums
		for(int row=0; row<hln; row++) {
			System.out.print("epoch\t"+(i+1) +" w*x\t");
			for(int colum =0; colum<len; colum++) {
				w[colum][row]=w[colum][row]*x[j][colum];
				System.out.print(w[colum][row]+"\t");
				//Last we add the result to the apropriate colum of the y vector
				z[colum]+=w[colum][row];
			}
			System.out.println(" ");
		}
		//returns the y vector
		return Arrays.copyOf(z, len);
	}
	//Activation function
	public static double actFunc(int j, double[] z, double g) {
		if(z[j]>=0) {
			g=z[j];
		} else {
			g=-1;		
		}
		System.out.print("g " + g);
		return  g;
	}
	/*
	//runs traning in set number of epochs 
	//************************ fejl her omkring!! *********************************'
	public static double[][] traning(double [][] w,double [][] x, int len, int epoch, int hln, double [] target, int dataLen, double[] z, double n) {
		int i = 0;
		int g = 0;
		for(int row=0; row<epoch;row++) {
			//if the selector j is larger than the length of our data set resets j
			if(row<dataLen) {
				test.calcOutput(w,x,len,hln,z,i,row);
				g = test.actFunc(row, z, g);
				if(g==target[row]) {
					epoch = row;
					//break;
				}
				double delta_w=0;
				//updating weights
				for (int colum = 0; colum< len;colum++) {
					delta_w = n*(target[row]-g)*x[colum][row];
					System.out.println("col: " + colum + " row: " + row);
					w[colum][row] += delta_w;
				}
			} else {
				row=0;
			}
		}
		return Arrays.copyOf(w, len);
		*/
	}
}
