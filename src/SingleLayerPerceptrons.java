import java.util.Arrays;
import java.util.Random;

public class SingleLayerPerceptrons {
	public static void main(String[] args) {
		//epoch
		int epoch = 50;
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
		double g;
		for(int j =0; j<epoch;j++) {
			for(int i=0; i< x.length;i++) {
				z= z +x[i]*w[i];
			}
			//activation function
			if(z>=0) {
				g= 1;
				if(g==target) {
					epoch = j;
					break;
				}
			}
			else {
				g=-1;
				if(g==target) {
					epoch = j;
					break;
				}
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
		//last guess without tar
		for(int i=0; i< x.length;i++) {
			z= z +x[i]*w[i];
		}
		//activation function
		if(z>=0) {
			g= 1;
		}
		else {
			g=-1;
		}
		System.out.println("target "+ target + " guess " +g+ " epoch " + epoch);
		System.out.println(Arrays.toString(w));
	}
}
