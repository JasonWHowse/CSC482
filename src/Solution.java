import java.util.Arrays;
import java.util.Comparator;
public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int output = 0;
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[1]));
        int[] lastGoodest = intervals.length>0?intervals[0]:null;
        for(int i = 1; i<intervals.length; i++){
            if(isConflict(lastGoodest,intervals[i])){
                output++;
                lastGoodest = lastGoodest[1]<intervals[i][1]?lastGoodest:intervals[i];
            }else{
                lastGoodest = intervals[i];
            }
        }
        return output;
    }

    private boolean isConflict(int[] first, int[] second){
        return first[1]!=second[0] && ((first[0]>=second[0] && first[0]<=second[1])  || (first[1]>=second[0] && first[1]<=second[1]));
    }
}