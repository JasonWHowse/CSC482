public class BitMagic {
    public static int BitMagicMultiply(int a, int b) {
        int smaller = Math.min(a,b);
        int larger = Math.max(a,b);
        if (smaller<10){
            return larger*smaller;
        }
        int shifter = 0;
        int remainder = 1;
        for(int smallerCopy = smaller; smallerCopy>1;smallerCopy >>= 1){
            remainder<<=1;
            shifter++;
        }
        return (larger << shifter) + BitMagicMultiply(smaller- remainder, larger);
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int result = BitMagicMultiply(a, b);
        System.out.println("Result: " + result);

        System.out.println("Answer: " + BitMagicMultiply(1234,78));
        System.out.println("Answer: " + new Karatsuba(1234,78));
        System.out.println("Answer: " + (1234 *78));
        System.out.println("Answer: " + BitMagicMultiply(1234,5678));
        System.out.println("Answer: " + new Karatsuba(1234,5678));
        System.out.println("Answer: " + (1234 * 5678));
        System.out.println("Answer: " + BitMagicMultiply(1234,2));
        System.out.println("Answer: " + new Karatsuba(1234,2));
        System.out.println("Answer: " + (1234 *2));
        System.out.println("Answer: " + BitMagicMultiply(1234,12341234));
        System.out.println("Answer: " + new Karatsuba(1234,12341234));
        System.out.println("Answer: " + (1234 * 12341234));
        System.out.println("Answer: " + BitMagicMultiply(20,20));
        System.out.println("Answer: " + new Karatsuba(20,20));
        System.out.println("Answer: " + (20 * 20));
        System.out.println("Answer: " + BitMagicMultiply(999999999,999999999));
        System.out.println("Answer: " + new Karatsuba(999999999,999999999));
        System.out.println("Answer: " + (999999999 * 999999999));
    }
}