import org.junit.Test;

import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class SolutionTest {
    @Test
    public void test1() {
        int[][] input = {new int[]{ 1, 2 }, new int[]{ 2,3 }, new int[]{ 3,4 }, new int[]{ 1,3 } };
        int solution = 1;
        test(input, solution);
    }

    @Test
    public void test2() {
        int[][] input = {new int[]{1,2}, new int[]{1,2}, new int[]{1,2}};
        int solution = 2;
        test(input, solution);
    }

    @Test
    public void test3() {
        int[][] input = {new int[]{1, 2},new int[]{2, 3}};
        int solution = 0;
        test(input, solution);
    }


    private void test(int[][] input, int solution){
        assertTimeout(Duration.ofSeconds(3),()->new Solution().eraseOverlapIntervals(input));
        assertEquals(solution,new Solution().eraseOverlapIntervals(input));
    }
}