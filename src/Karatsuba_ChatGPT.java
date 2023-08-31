import java.math.BigInteger;

public class Karatsuba_ChatGPT {

    public static BigInteger multiply(int x, int y){
        return multiply(new BigInteger(String.valueOf(x)), new BigInteger(String.valueOf(y)));
    }

    public static BigInteger multiply(BigInteger x, BigInteger y) {
        int n = Math.max(x.bitLength(), y.bitLength());

        // Base case: If either of the numbers has only one bit, use regular multiplication
        if (n <= 1) {
            return x.multiply(y);
        }

        // Compute n/2 and split the input numbers into two halves
        int n2 = (n + 1) / 2;
        BigInteger xHigh = x.shiftRight(n2);
        BigInteger xLow = x.subtract(xHigh.shiftLeft(n2));
        BigInteger yHigh = y.shiftRight(n2);
        BigInteger yLow = y.subtract(yHigh.shiftLeft(n2));

        // Recursively compute the three sub-products
        BigInteger a = multiply(xHigh, yHigh);
        BigInteger b = multiply(xLow, yLow);
        BigInteger c = multiply(xHigh.add(xLow), yHigh.add(yLow));

        // Calculate the result using the Karatsuba formula
        BigInteger firstTerm = a.shiftLeft(2 * n2);
        BigInteger secondTerm = c.subtract(a).subtract(b).shiftLeft(n2);
        BigInteger result = firstTerm.add(secondTerm).add(b);

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Answer: " + multiply(1234,78));
        System.out.println("Answer: " + multiply(1234,5678));
    }
}
