package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class norm {

	public norm() {

	}

	public static double[][] setupNorm (ArrayList listOfSt) {
		/*
		 * normalize data
		 * initialize arrays
		 */
		String [] y = new String[4];
		double[] max = new double[4];
		double[] min = new double[4];
		String [] minMaxSplit = new String[4];
		/*
		 * get string of data
		 */
		String minMax = (String) listOfSt.get(0);
		/*
		 * split string
		 */
		minMaxSplit = minMax.split(",");
		for (int i = 0; i < 4; i++) {
			double conv_double = Double.parseDouble(minMaxSplit[i]);
			max[i] = conv_double;
			min[i] = conv_double;
			
		}
		
		for (int i = 0; i < listOfSt.size(); i++) {
			/*
			 * get string of data
			 */
			String x = (String) listOfSt.get(i);
			/*
			 * split string
			 */
			y = x.split(",");
			
			for ( int j = 0; j < 4; j++) {
				/*
				 * convert from string to double
				 */
				double conv_double = Double.parseDouble(y[j]);
				if (conv_double < min[j]) {
					min[j] = conv_double;
				}
				else if ( conv_double > max[j]) {
					max[j] = conv_double;
				}
			}
		}
		double[][] rMinMax = {min, max};
		return rMinMax;
	}

}
