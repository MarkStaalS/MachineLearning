package src;
import java.util.Arrays;
import java.util.Random;

public class test {

	public static void main(String[] args) {
		double[] a= {0,0,1};
		
		System.out.println(findMax(a));
	}
	public static int findMax(double[] array) {
		int maxAt = 0;
		for (int i = 0; i < array.length; i++) {
		    maxAt = array[i] > array[maxAt] ? i : maxAt;
		}
		return maxAt;
	}
}
