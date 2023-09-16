public class BitMagic {
    public static int count = 0;
    public static int BitMagicMultiply(int a, int b) {
        int smaller = Math.min(a,b);
        int larger = Math.max(a,b);
        if (smaller<2){
            count++;
            return smaller == 1? larger:0;
        }
        int shifter = 0;
        int remainder = 1;
        for(int smallerCopy = smaller; smallerCopy>1;smallerCopy >>= 1){
            count++;
            remainder<<=1;
            shifter++;
        }
        return (larger << shifter) + BitMagicMultiply(smaller- remainder, larger);
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        testMultiple(a,b);
        testMultiple(1234,78);
        testMultiple(1234,5678);
        testMultiple(1234,2);
        testMultiple(1234,12341234);
        testMultiple(20, 20);
        testMultiple(999999999,999999999);
    }

    public static void testMultiple(int a, int b){
        System.out.println("" + a + " * " + b + " = ");
        BitMagic.count = 0;
        System.out.println("BM Answer: " + BitMagicMultiply(a,b));
        System.out.println("Count: " + BitMagic.count);
        Karatsuba.count = 0;
        System.out.println("Karatsuba Answer: " + new Karatsuba(a,b));
        System.out.println("Count: " + Karatsuba.count);
        System.out.println("Standard Answer: " + (a * b));
        System.out.println("Count: " + (String.valueOf(a).length() * String.valueOf(b).length()));
        System.out.println();
    }
}