package src;
import java.util.Arrays;
import java.util.Random;

public class SingleLayerPerceptron {
	public static void main(String[] args) {
		//epoch
		int epoch = 500;
		//input
		double[] x= {0,1,0,1};
		//target
		double target = -1;
		//learning rate, n
		double n = 0.2;
		//Initialize weights
		Random rnd = new Random();
		double[] w = new double[x.length];
		for (int i = 0; i< x.length;i++) {
			w[i] = rnd.nextDouble();
		}
		//linear combination of input values z
		double z = 0;
		//g(z) = g
		double g =0;
		for(int j =0; j<epoch;j++) {
			for(int i=0; i< x.length;i++) {
				z= z +x[i]*w[i];
			}
			//activation function
			//if statement skal være en range istedet for en precis værdi
			if(z>=0) {
				g= 1;
				//g=z

			} else {
				g=-1;		
			}
			if(g==target) {
				epoch = j;
				break;
			}
			System.out.println(g);
			double delta_w=0;
			//updating weights
			for (int i = 0; i< x.length;i++) {
				delta_w = n*(target-g)*x[i];
				w[i] = w[i] + delta_w;
			}

			System.out.println(Arrays.toString(w));
		}
		
		/*
		//last guess without target
		for(int i=0; i< x.length;i++) {
			z= z +x[i]*w[i];
		}
		//activation function
		if(z>=0) {
			g= z;
		}
		else {
			g=0;
		}
		*/
		System.out.println("target "+ target + " guess " +g+ " epoch " + epoch);
		System.out.println(Arrays.toString(w));
		
	}
}