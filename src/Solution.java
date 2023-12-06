import java.util.*;
public class Solution {
    private static int bindarySearch(int[][] jobs, int current_index, int start_index) {
        int lo = 0;
        int hi = current_index;
        int mid = (lo + hi) / 2;

        while (lo <= hi) {
            if (jobs[mid][1] == start_index ||
                    (mid < current_index && jobs[mid][1] < start_index && jobs[mid + 1][1] > start_index) ||
                    (mid == current_index && jobs[mid][1] < start_index)) {
                int output = jobs[mid][2];
                int endPoint = jobs[mid][1];
                int midSave = mid;
                while(mid < jobs.length && endPoint==jobs[mid][1]){
                    output = Math.max(output, jobs[mid][2]);
                    mid++;
                }
                while(midSave >= 0 && endPoint==jobs[midSave][1]){
                    output = Math.max(output, jobs[midSave][2]);
                    midSave--;
                }
                return output;
            } else if (jobs[mid][1] > start_index) {
                hi = mid - 1;
                mid = (lo + mid - 1) / 2;
            } else if (jobs[mid][1] < start_index) {
                lo = mid + 1;
                mid = (mid + 1 + hi) / 2;
            }
        }

        return 0;
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][];
        for(int i = 0; i<startTime.length; i++){
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, Comparator.comparingInt(job -> job[1]));

        for(int i=0; i<jobs.length; i++){
            jobs[i][2] = Math.max(jobs[i][2], jobs[i][2]+bindarySearch(jobs, i, jobs[i][0]));
            jobs[i][2]=Math.max(jobs[i][2], i>0?jobs[i-1][2]:0);
        }

        return jobs[jobs.length-1][2];
    }
}