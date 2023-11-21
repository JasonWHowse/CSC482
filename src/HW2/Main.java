package HW2;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner input= new Scanner(System.in);
        int a = input.nextInt();
        int sizes = a;
        int longestPipe = input.nextInt();
        PipeCutting3 pc = new PipeCutting3(longestPipe);
        for(int i = 0; i<sizes;i++){
            pc.addPipeSizeValue(input.nextInt(), input.nextInt());
        }
        System.out.println(pc);
    }
}

class PipeCutting{
    private ArrayList<int[]> pipeSizeValueList;
    int longestPipe;
    int maxPipeValues = 0;
    public PipeCutting(){
        this.pipeSizeValueList = new ArrayList<>();
        this.longestPipe = 0;
    }

    public PipeCutting(int longestPipe){
        this();
        this.longestPipe = longestPipe;
    }

    public PipeCutting addPipeSizeValue(int pipesize, int value){
//        pipeSizeValueList.add(new int[]{pipesize, value, longestPipe/pipesize});
        maxPipeValues+=longestPipe/pipesize;

        int lengthCount = pipesize;
        while(longestPipe>=lengthCount) {
            pipeSizeValueList.add(new int[]{pipesize, value});
            lengthCount+=pipesize;
        }
//        System.out.println("mPV: " + maxPipeValues + " pSVL.size(): " + pipeSizeValueList.size()  + " MPV==pSVL.size(): " + (maxPipeValues==pipeSizeValueList.size()));
        return this;
    }

    public String showStoredValues(){
        StringBuilder output = new StringBuilder();
        for(int[] i : pipeSizeValueList){
            output.append("" + i[0] + ", " + i[1] /*+ ", " + i[2]*/ + "\r\n");
        }
        return output.toString();
    }

    public int maxValueOfPipes() {
        int[][] d = new int[pipeSizeValueList.size() + 1][];
        for (int i = 0; i < d.length; i++) {
            d[i] = new int[longestPipe + 1];
        }

        for (int i = 0; i < pipeSizeValueList.size(); i++) {
            for (int w = 0; w <= longestPipe; w++) {
                if (pipeSizeValueList.get(i)[0] <= w) {
                    d[i + 1][w] = Math.max(d[i][w], d[i][w - pipeSizeValueList.get(i)[0]] + pipeSizeValueList.get(i)[1]);
                } else {
                    d[i + 1][w] = d[i][w];
                }
            }
        }

//        StringBuilder tempoutput = new StringBuilder();
//        for(int[] i : d){
//            for(int j : i){
//                tempoutput.append(""+j+",");
//            }
//            tempoutput.append("\r\n");
//        }
//        System.out.println(tempoutput);
        return d[d.length - 1][d[d.length - 1].length - 1];
    }

    public String getBestValue(){
        return "" + maxValueOfPipes();
    }

    @Override
    public String toString() {
        return getBestValue();//showStoredValues();
    }
}
// for the 1d array version of this problem place the bast value for a given pipe dimension at the weight index of the 1d array having the 1d array go from 0 to the max weight.  Over write positions where they are better and build on them when certain ones have reached their peak
class PipeCutting2{
    private ArrayList<int[]> pipeSizeValueList;
    int longestPipe;
    int maxPipeValues = 0;
    public PipeCutting2(){
        this.pipeSizeValueList = new ArrayList<>();
        this.longestPipe = 0;
    }

    public PipeCutting2(int longestPipe){
        this();
        this.longestPipe = longestPipe;
    }

    public PipeCutting2 addPipeSizeValue(int pipesize, int value){
        int mp = longestPipe/pipesize;
        if(mp!=0) {
            pipeSizeValueList.add(new int[]{pipesize, value, longestPipe / pipesize});
            maxPipeValues += mp;
        }
        return this;
    }

    public String showStoredValues(){
        StringBuilder output = new StringBuilder();
        for(int[] i : pipeSizeValueList){
            output.append("" + i[0] + ", " + i[1] + ", " + i[2] + "\r\n");
        }
        return output.toString();
    }

    public int maxValueOfPipes() {
        int[][] d = new int[maxPipeValues + 1][];
        for (int i = 0; i < d.length; i++) {
            d[i] = new int[longestPipe + 1];
        }

        for (int i = 0; i < pipeSizeValueList.size(); i++) {
            for(int j = 0; j< pipeSizeValueList.get(i)[2]; j++) {
                for (int w = 0; w <= longestPipe; w++) {
                    if (pipeSizeValueList.get(i)[0] <= w) {
                        d[j+i + 1][w] = Math.max(d[j+i][w], d[j+i][w - pipeSizeValueList.get(i)[0]] + pipeSizeValueList.get(i)[1]);
                    } else {
                        d[j+i + 1][w] = d[j+i][w];
                    }
//                    StringBuilder tempoutput = new StringBuilder();
//                    tempoutput.append("New Matrix for i: " +i+" j: " + j+ " w: " + w + " pipe size: " + pipeSizeValueList.get(i)[0] + " pipe value: " + pipeSizeValueList.get(i)[1] + "\r\n");                    for(int[] m : d){
//                        for(int n : m){
//                            tempoutput.append(""+n+",");
//                        }
//                        tempoutput.append("\r\n");
//                    }
//                    tempoutput.append("\r\n");
//                    System.out.println(tempoutput);
                }
            }
        }
        return d[d.length - 1][d[d.length - 1].length - 1];
    }

    public String getBestValue(){
        return "" + maxValueOfPipes();
    }

    @Override
    public String toString() {
        return getBestValue();//showStoredValues();
    }
}
// for the 1d array version of this problem place the bast value for a given pipe dimension at the weight index of the 1d array having the 1d array go from 0 to the max weight.  Over write positions where they are better and build on them when certain ones have reached their peak
class PipeCutting3{
    private ArrayList<int[]> pipeSizeValueList;
    int longestPipe;

    public PipeCutting3(){
        this.pipeSizeValueList = new ArrayList<>();
        this.longestPipe = 0;
    }

    public PipeCutting3(int longestPipe){
        this();
        this.longestPipe = longestPipe;
    }

    public PipeCutting3 addPipeSizeValue(int pipesize, int value){
        if(pipesize<=longestPipe) {
            pipeSizeValueList.add(new int[]{pipesize, value});
        }
        return this;
    }

    public String showStoredValues(){
        StringBuilder output = new StringBuilder();
        for(int[] i : pipeSizeValueList){
            output.append("" + i[0] + ", " + i[1] + "\r\n");
        }
        return output.toString();
    }

    public int maxValueOfPipes() {
        int[] d = new int[longestPipe+1];
        for(int l = 1; l<d.length; l++){
            for(int[] p : pipeSizeValueList){
                if(l-p[0]>=0  && d[l-p[0]]+p[1]>d[l]){
                    d[l]=d[l-p[0]]+p[1];
                }
            }
        }
        return d[longestPipe];
    }

    public String getBestValue(){
        return "" + maxValueOfPipes();
    }

    @Override
    public String toString() {
        return getBestValue();
    }
}