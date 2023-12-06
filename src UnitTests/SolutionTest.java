import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    @Test
    public void test1() {
        int[] startTimeCase = new int[]{1, 2, 3, 3};
        int[] endTimeCase = new int[]{3, 4, 5, 6};
        int[] profitCase = new int[]{50, 10, 40, 70};
        int solution = 120;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

    @Test
    public void test2() {
        int[] startTimeCase = new int[]{1, 2, 3, 4, 6};
        int[] endTimeCase = new int[]{3, 5, 10, 6, 9};
        int[] profitCase = new int[]{20, 20, 100, 70, 60};
        int solution = 150;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

    @Test
    public void test3() {
        int[] startTimeCase = new int[]{1, 1, 1};
        int[] endTimeCase = new int[]{2, 3, 4};
        int[] profitCase = new int[]{5, 6, 4};
        int solution = 6;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

    @Test
    public void test4() {
        int[] startTimeCase = new int[]{1, 2, 3, 3};
        int[] endTimeCase = new int[]{3, 4, 5, 1000000000};
        int[] profitCase = new int[]{50, 10, 40, 70};
        int solution = 120;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

    @Test
    public void test5() {
        int[] startTimeCase = new int[]{4, 2, 4, 8, 2};
        int[] endTimeCase = new int[]{5, 5, 5, 10, 8};
        int[] profitCase = new int[]{1, 2, 8, 10, 4};
        int solution = 18;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

    @Test
    public void test6() {
        int[] startTimeCase = new int[]{24, 24, 7, 2, 1, 13, 6, 14, 18, 24};
        int[] endTimeCase = new int[]{27, 27, 20, 7, 14, 22, 20, 24, 19, 27};
        int[] profitCase = new int[]{6, 1, 4, 2, 3, 6, 5, 6, 9, 8};
        int solution = 20;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

//    @Test
//    public void test7_1() {
//        int[] startTimeCase = startTimeCase7;
//        int[] endTimeCase = endTimeCase7;
//        int[] profitCase = profitCase7;
//        int solution = 20;
//        test(startTimeCase, endTimeCase, profitCase, solution);
//    }
//
//    @Test
//    public void test7_2() {
//        int[] startTimeCase = startTimeCase7;
//        int[] endTimeCase = endTimeCase7;
//        int[] profitCase = profitCase7;
//        int solution = 20;
//        test2(startTimeCase, endTimeCase, profitCase, solution);
//    }

    @Test
    public void test8() {
        int[] startTimeCase = new int[]{4,3,1,2,4,8,3,3,3,9};
        int[] endTimeCase = new int[]{5,6,3,5,9,9,8,5,7,10};
        int[] profitCase = new int[]{9,9,5,12,10,11,10,4,14,7};
        int solution = 37;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

    @Test
    public void test9() {
        int[] startTimeCase = new int[]{11,10,14,24,5,9,3,17,27,20};
        int[] endTimeCase = new int[]{20,23,22,29,9,13,9,23,28,30};
        int[] profitCase = new int[]{2,2,3,2,4,3,4,4,7,2};
        int solution = 18;
        test(startTimeCase, endTimeCase, profitCase, solution);
    }

    private void test(int[] startTimeCase, int[] endTimeCase, int[] profitCase, int solution) {
        assertEquals(Solution.jobScheduling(startTimeCase, endTimeCase, profitCase), solution);
    }
}