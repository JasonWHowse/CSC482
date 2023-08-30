import java.util.Scanner;
public class Extended_gcd{
        private int GCD = -1;
        private double BC1 = 0;
        private double QT = 0;

        public Extended_gcd(int VAL1, int VAL2){
                this.gcd_calc(VAL1, VAL2);
        }

        public void gcd_calc(int a, int b){
                this.GCD = a;
                int r = b;
                this.BC1 = 1;
                double s = 0;
                this.QT = 1;

                while(r!=0){
                        System.out.println(this);
                        int quotient = this.GCD / r;
                        int temp_r = this.GCD - quotient * r;
                        this.GCD = r;
                        System.out.println(this);
                        r = temp_r;
                        double temp_s = this.BC1 - quotient * s;
                        this.BC1 = s;
                        System.out.println(this);
                        s = temp_s;
                        System.out.println();
                }

                if (b!=0){
                        this.QT = (this.GCD - this.BC1 * a)/b;
                }else{
                        this.QT  = 0;
                }
        }


        public static void main(String[] args) {
                // Create a Scanner
                Scanner input = new Scanner(System.in);
                System.out.print("Enter first number for GCD Comparison: ");
                int n = input.nextInt();
                System.out.print("Enter second number for GCD Comparison: ");
                int m = input.nextInt();
                Extended_gcd gcd = new Extended_gcd(n, m);
// Find the solution recursively
                System.out.println(gcd);

                input.close();
        }

        public String toString(){
                return "The GCD is: " + GCD + "\r\nBézout coefficients: " + BC1 + ", " + QT;
        }
}
