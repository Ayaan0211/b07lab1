public class Polynomial {
    
    double[] coefficients;
        
    public Polynomial() {

        double[] temp = {0};
        coefficients = temp;

    }
    
    public Polynomial(double[] input) {

        /*for (int i=0; i<input.length; i++) {
            coefficients[i] = input[i];
        }*/
        coefficients = input;

    }
    
    public Polynomial add(Polynomial p) {

        int coeff_len = coefficients.length;
        int p_len = p.coefficients.length;
        double[] new_arr = new double[Math.max(coeff_len, p_len)];

        if (coeff_len >= p_len) {
            for (int i=0; i<p_len; i++) {
                new_arr[i] = coefficients[i] + p.coefficients[i];
            }
            for (int i=p_len; i<coeff_len; i++) {
                new_arr[i] = coefficients[i];
            }
        }
        else {
            for (int i = 0; i<coeff_len; i++) {
                new_arr[i] = coefficients[i] + p.coefficients[i];
            }
            for (int i=coeff_len; i<p_len; i++) {
                new_arr[i] = p.coefficients[i];
            }
        }

        return new Polynomial(new_arr);

    }
    
    public double evaluate(double x) {

        if (coefficients.length == 0) return 0;

        double sum = 0;
        for (int i = 0; i<coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x, i);
        }

        return sum;
    }
    
    public boolean hasRoot(double x) {
        return (evaluate(x) == 0);
    }

}



