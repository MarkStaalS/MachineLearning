package src;

import java.util.HashMap;

public class net {
	int[] topology;
	HashMap<Integer, neuron[]> net = new HashMap<Integer, neuron[]>();
	
	
	
	public net() {
		
	}
	
	public void setTopology(int[] _topology) {
		topology = _topology;
	}
	
	public static neuron[] createNeuronLayer(int size, double bias) {
		neuron[] l = new neuron[size];
		for (int i = 0; i < size; i++) {
			l[i] = new neuron();
			l[i].setBias(0);
		}
		return l;
	}	
	public void createNet(double bias) {
		for (int layer = 0; layer < topology.length; layer++) {
			net.put(layer, createNeuronLayer(topology[layer], bias));
		}
	}

}
