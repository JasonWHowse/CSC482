import javax.print.attribute.standard.PrinterResolution;
public class Lab6 {
    public boolean canPartition1d(int[] nums) {
        int solution = 0;
        for (int i : nums) {
            solution += i;
        }
        if (solution % 2 == 1) {
            return false;
        }
        solution/=2;
        boolean[] d = new boolean[solution+1];
        d[0]=true;
        for(int num: nums){
            for(int i = solution; i>=num; i--){
                d[i]=d[i] || d[i-num];
            }
        }
        return d[solution];
    }

    public boolean canPartition2d(int[] nums) {
        int solution = 0;
        for(int i : nums){
            solution+=i;
        }
        if(solution%2==1){
            return false;
        }
        solution/=2;
        int[][] d = new int[nums.length+1][];
        for(int i = 0; i < d.length; i++){
            d[i] = new int[solution+1];
            for(int j = 0; j<d[i].length;j++){
                d[i][j]=0;
            }
        }

        for (int i = 0; i <nums.length; i++){
            for (int w = 0; w <=solution; w++){
                if (nums[i] <= w){
                    d[i+1][w] = Math.max(d[i][w], d[i][w - nums[i]] + nums[i]);
                }else{
                    d[i+1][w] = d[i][w];
                }
                if(solution == d[i][w]){
                    return true;
                }
            }
        }
        return false;
    }
}
