public class Karatsuba {

    public static int count = 0;

    public Karatsuba( int  X, int Y ) {
        answer = ""+karatsuba(X, Y);
    }

    public Karatsuba(){}

    public int karatsuba(int X, int Y){
        count++;
        int maxLength = Math.max(String.valueOf(X).length(), String.valueOf(Y).length());
        //removed unneeded string building since 0 is 0
        if(maxLength==1){
            return X * Y;
        }
        int mid = Math.round(maxLength/2f); //this uses regular rounding
        int splitter = (int) Math.pow(10,(int)(maxLength/2f)); //this uses int floor rounding
        int xHigh = X/splitter;
        int xLow = X%splitter;
        int yHigh = Y/splitter;
        int yLow = Y%splitter;

        int high = karatsuba(xHigh,yHigh);
        int lowly = karatsuba(xLow,yLow);
        int middleTerm = karatsuba(xLow + xHigh ,yLow + yHigh) - high - lowly;

        high *= (int)Math.pow(10, 2*(maxLength-mid));
        middleTerm *= (int)Math.pow(10, (maxLength-mid));

        return high + middleTerm + lowly;
    }

    String answer = "0";

    public String toString(){
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("Answer: " + new Karatsuba(1234,78));
        System.out.println("Answer: " + (1234 *78));
        System.out.println("Answer: " + new Karatsuba(1234,5678));
        System.out.println("Answer: " + (1234 * 5678));
        System.out.println("Answer: " + new Karatsuba(1234,2));
        System.out.println("Answer: " + (1234 *2));
        System.out.println("Answer: " + new Karatsuba(1234,12341234));
        System.out.println("Answer: " + (1234 * 12341234));
    }
}
