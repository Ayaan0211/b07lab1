import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Polynomial {
    
    double[] coefficients;
    int[] exponents;
        
    public Polynomial() {

        double[] temp1 = {0};
        coefficients = temp1;

        int[] temp2 = {0};
        exponents = temp2;

    }
    
    public Polynomial(double[] coeff, int[] exp) {

        coefficients = coeff;
        exponents = exp;

    }

    public Polynomial(File f) {
        
        ArrayList<Double> final_coeff = new ArrayList<Double>();
        ArrayList<Integer> final_exp = new ArrayList<Integer>();

        try {

            Scanner reader = new Scanner(f);
            String s = reader.nextLine();
            s = s.replace("+", " +");
            s = s.replace("-", " -");
            String[] arr = s.split(" ");
            if (arr[0].length() == 0) arr = Arrays.copyOfRange(arr, 1, arr.length);
            
            for (int i = 0; i<arr.length; i++) {

                //System.out.println(arr[i]);

                int pos_x = arr[i].indexOf('x');
                //int pos_plus = arr[i].indexOf('+');
                //int pos_minus = arr[i].indexOf('-');

                if (pos_x == -1) {
                    final_exp.add(0);
                    final_coeff.add(Double.parseDouble(arr[i]));
                }
                else {
                    final_exp.add(Integer.parseInt(Character.toString(arr[i].charAt(pos_x+1))));
                    if (pos_x == 0) final_coeff.add(1.0);
                    else final_coeff.add(Double.parseDouble(arr[i].substring(0, pos_x)));
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception!");
        }

/*         System.out.println(final_exp);
        System.out.println(final_coeff); */

        int[] ret_exp = new int[final_coeff.size()];
        for (int i = 0; i<final_exp.size(); i++) ret_exp[i] = final_exp.get(i).intValue();
        exponents = ret_exp;

        double[] ret_coeff = new double[final_exp.size()];
        for (int i = 0; i<final_coeff.size(); i++) ret_coeff[i] = final_coeff.get(i).doubleValue();
        coefficients = ret_coeff;
    }

    public void saveToFile(String s) {

        if (coefficients.length == 0) return;

        try {

            FileWriter f = new FileWriter(s);

            f.write(String.valueOf(coefficients[0]));
            if (exponents[0] != 0) {
                f.write("x");
                f.write(String.valueOf(exponents[0]));
            }
            
            for (int i = 1; i<exponents.length; i++) {
                if (coefficients[i] < 0) f.write("-");
                else f.write("+");
                f.write(String.valueOf(Math.abs(coefficients[i])));
                
                if (exponents[i] != 0) {
                    f.write("x");
                    f.write(String.valueOf(exponents[i]));
                }
            }
            f.close();

        } catch (Exception e) {
            System.out.println("Error Exception!");
        }

        return;
    }
    
    public Polynomial add(Polynomial p) {

        ArrayList<Double> final_coeff = new ArrayList<Double>();
        ArrayList<Integer> final_exp = new ArrayList<Integer>();

        for (int i = 0; i<exponents.length; i++) {
            boolean flag = false;
            for (int j = 0; j<p.exponents.length; j++) {
                if (exponents[i] == p.exponents[j]) {
                    final_exp.add(exponents[i]);
                    final_coeff.add(coefficients[i] + p.coefficients[j]);
                    flag = true;
                }
            }
            if (flag == false) {
                final_exp.add(exponents[i]);
                final_coeff.add(coefficients[i]);
            }
        }

        for (int i = 0; i<p.exponents.length; i++) {
            boolean flag = false;
            for (int j = 0; j<exponents.length; j++) {
                if (p.exponents[i] == exponents[j]) {
                    flag = true;
                }
            }
            if (flag == false) {
                final_exp.add(p.exponents[i]);
                final_coeff.add(p.coefficients[i]);
            }
        }

/*         System.out.println(final_exp);
        System.out.println(final_coeff); */

        int[] ret_exp = new int[final_coeff.size()];
        for (int i = 0; i<final_exp.size(); i++) ret_exp[i] = final_exp.get(i).intValue();

        double[] ret_coeff = new double[final_exp.size()];
        for (int i = 0; i<final_coeff.size(); i++) ret_coeff[i] = final_coeff.get(i).doubleValue();


        return new Polynomial(ret_coeff, ret_exp);

    }
    
    public double evaluate(double x) {

        if (coefficients.length == 0) return 0;

        double sum = 0;

        for (int i = 0; i<coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x, exponents[i]);
        }

        return sum;
    }
    
    public boolean hasRoot(double x) {
        return (evaluate(x) == 0);
    }

    public Polynomial multiply(Polynomial p) {

        ArrayList<Double> final_coeff = new ArrayList<Double>();
        ArrayList<Integer> final_exp = new ArrayList<Integer>();

        for (int i = 0; i<exponents.length; i++) {
            for (int j = 0; j<p.exponents.length; j++) {

                double coeff_temp = coefficients[i]*p.coefficients[j];
                int exp_temp = exponents[i]+p.exponents[j];
                
                if (final_exp.contains(exp_temp)) {
                    int idx = final_exp.indexOf(exp_temp);
                    final_coeff.set(idx, coeff_temp + final_coeff.get(idx));
                }
                else {
                    final_exp.add(exp_temp);
                    final_coeff.add(coeff_temp);
                }
            }
        }

/*         System.out.println(final_exp);
        System.out.println(final_coeff); */

        int[] ret_exp = new int[final_coeff.size()];
        for (int i = 0; i<final_exp.size(); i++) ret_exp[i] = final_exp.get(i).intValue();

        double[] ret_coeff = new double[final_exp.size()];
        for (int i = 0; i<final_coeff.size(); i++) ret_coeff[i] = final_coeff.get(i).doubleValue();


        return new Polynomial(ret_coeff, ret_exp);
    }

}