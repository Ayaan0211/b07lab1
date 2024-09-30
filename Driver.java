import java.io.*;
import java.util.*;

public class Driver {

	public static void main(String [] args) {
		
		double [] c1 = {10, 11, 12};
		double [] c2 = {13, 14, 15};
		int [] e1 = {1, 2, 3};
		int [] e2 = {4, 5, 6};
		Polynomial p1 = new Polynomial(c1, e1);
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		//System.out.println(Arrays.toString(s.exponents));
		//System.out.println(Arrays.toString(s.coefficients));

		double [] c3 = {5, 7, 10};
		double [] c4 = {13, 7, 2};
		int [] e3 = {1, 2, 3};
		int [] e4 = {3, 2, 1};
		Polynomial p3 = new Polynomial(c3, e3);
		Polynomial p4 = new Polynomial(c4, e4);
		Polynomial q = p3.add(p4);
		//System.out.println(Arrays.toString(q.exponents));
		//System.out.println(Arrays.toString(q.coefficients));

		//System.out.println(p1.evaluate(-2));

		double [] c5 = {3, 5};
		int [] e5 = {0, 1};
		double [] c6 = {4, 6};
		int [] e6 = {0, 1};
		Polynomial p5 = new Polynomial(c5, e5);
		Polynomial p6 = new Polynomial(c6, e6);
		Polynomial r = p5.multiply(p6);
		//System.out.println(Arrays.toString(r.exponents));
		//System.out.println(Arrays.toString(r.coefficients));

		double [] c7 = {3, 2, 5};
		int [] e7 = {2, 0, 1};
		double [] c8 = {7, 3};
		int [] e8 = {4, 0};
		Polynomial p7 = new Polynomial(c7, e7);
		Polynomial p8 = new Polynomial(c8, e8);
		Polynomial t = p7.multiply(p8);
		//System.out.println(Arrays.toString(t.exponents));
		//System.out.println(Arrays.toString(t.coefficients));

		File f = new File("/Users/ayaanislam/Desktop/YEAR 2/CSCB07/Labs/b07lab1/test.txt");
		Polynomial text = new Polynomial(f);
		System.out.println(Arrays.toString(text.exponents));
		System.out.println(Arrays.toString(text.coefficients));

		text.saveToFile("/Users/ayaanislam/Desktop/YEAR 2/CSCB07/Labs/b07lab1/write.txt");

	}

}