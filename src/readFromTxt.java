package src;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class readFromTxt {
	public static void main(String[] args)throws Exception {
		//Loads txt file
		File file = new File("C:\\Users\\mark\\Desktop\\iris.data.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList listOfSt = new ArrayList();
		//Transforms file to list of strings
		String st;
		while ((st = br.readLine()) != null) {
			listOfSt.add(st);
		}
		
		//Gets specific string
		System.out.println(listOfSt.get(50));
		String x; 
		x = (String) listOfSt.get(50);
		System.out.println(x.indexOf(","));
		//Delimits string based on ",", this gives us a String[] contaning the differen values
		String[] y = x.split(",");
		System.out.println(Arrays.toString(y));
		System.out.println(y[3]);
		
		//identify flower
		if (x.contains("Iris-setosa")) {
			System.out.println("versi");
		}
	}
}