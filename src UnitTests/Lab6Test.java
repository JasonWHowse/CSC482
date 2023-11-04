import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lab6Test {
    @Test
    public void test1(){
        int[] testcase = {1, 5,11,5};
        boolean solution = true;
        test(testcase, solution);
    }

    @Test
    public void test2(){
        int[] testcase = {1, 2, 3,5};
        boolean solution = false;
        test(testcase, solution);
    }

    private void test(int[] testcase, boolean solution){
        if(solution){
            assertTrue(new Lab6().canPartition1d(testcase));
            assertTrue(new Lab6().canPartition2d(testcase));
        }else{
            assertFalse(new Lab6().canPartition2d(testcase));
            assertFalse(new Lab6().canPartition1d(testcase));
        }
    }
}