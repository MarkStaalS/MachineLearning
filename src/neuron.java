package src;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class neuron {
	//output
	double g;
	double z;
	int index;
	//Array of connections
	//connection[] connections;
	HashMap<Integer, connection> connections = new HashMap<Integer, connection>();
	
	public neuron(double bias) {
		index = 0;
	}
	public double activationFunc() {
		//activation function
		if(z>=0) {
			g= z;
		} else {
			g=-1;		
		}	
		return g;
	}
	public double calcOut() {
		//summes the inputs and gives z
		int len = connections.size();
		for(int i =0 ; i<len;i++) {
			z += connections.get(i).getOutput();			
		}
		return z;
	}
	public void addConnection(connection c) {
		//add a connection at index
		connections.put(index, c);
		index += 1;
	}
}

