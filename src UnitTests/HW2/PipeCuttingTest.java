package HW2;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class PipeCuttingTest {
    @Test
    public void test1(){
        PipeCutting testcase = new PipeCutting(581);
        PipeCutting2 testcase2 = new PipeCutting2(581);
        PipeCutting3 testcase3 = new PipeCutting3(581);
        for(int[] i : tc1){
            testcase.addPipeSizeValue(i[0], i[1]);
            testcase2.addPipeSizeValue(i[0], i[1]);
            testcase3.addPipeSizeValue(i[0], i[1]);
        }
        String solution = "2868";
        test(testcase, testcase2, testcase3, solution);
    }

    @Test
    public void test2(){
        PipeCutting testcase = new PipeCutting(10);
        PipeCutting2 testcase2 = new PipeCutting2(10);
        PipeCutting3 testcase3 = new PipeCutting3(10);
        for(int[] i : tc2){
            testcase.addPipeSizeValue(i[0], i[1]);
            testcase2.addPipeSizeValue(i[0], i[1]);
            testcase3.addPipeSizeValue(i[0], i[1]);
        }
        String solution = "5";
        test(testcase, testcase2, testcase3, solution);
    }

    @Test
    public void test3(){
        PipeCutting testcase = new PipeCutting(11);
        PipeCutting2 testcase2 = new PipeCutting2(11);
        PipeCutting3 testcase3 = new PipeCutting3(11);
        testcase.addPipeSizeValue(5, 4);
        testcase3.addPipeSizeValue(5, 4);
        String solution = "8";
        test(testcase, testcase2, testcase3, solution);
    }

    private void test(PipeCutting testcase, PipeCutting2 testcase2, PipeCutting3 testcase3,  String solution){
        assertEquals(solution, testcase.toString());
//        assertEquals(solution, testcase2.toString());
        assertEquals(solution, testcase3.toString());
//        System.out.println(testcase2.showStoredValues());
    }
//
//    private void writetodocument(String writehtis){
//        String path = "D:\Jason Howse\Downloads";
//    }

    int[][] tc1 = new int[][]{
        new int[]{681, 163},
        new int[]{3811, 121},
        new int[]{3833, 25},
        new int[]{581, 218},
        new int[]{1873, 59},
        new int[]{1528, 196},
        new int[]{2908, 194},
        new int[]{191, 123},
        new int[]{433, 107},
        new int[]{1625, 70},
        new int[]{1636, 140},
        new int[]{1291, 68},
        new int[]{4083, 109},
        new int[]{4276, 265},
        new int[]{4665, 63},
        new int[]{1650, 119},
        new int[]{2873, 269},
        new int[]{4115, 91},
        new int[]{2025, 82},
        new int[]{2503, 34},
        new int[]{2724, 133},
        new int[]{2276, 75},
        new int[]{722, 189},
        new int[]{580, 252},
        new int[]{4681, 135},
        new int[]{4697, 231},
        new int[]{2902, 267},
        new int[]{3877, 207},
        new int[]{366, 196},
        new int[]{3579, 59},
        new int[]{3342, 264},
        new int[]{1361, 27},
        new int[]{3264, 55},
        new int[]{2835, 246},
        new int[]{3636, 21},
        new int[]{2749, 133},
        new int[]{2567, 269},
        new int[]{1685, 3},
        new int[]{3859, 188},
        new int[]{4279, 213},
        new int[]{2457, 59},
        new int[]{2666, 4},
        new int[]{570, 176},
        new int[]{4707, 78},
        new int[]{1306, 109},
        new int[]{3498, 42},
        new int[]{4014, 61},
        new int[]{3873, 192},
        new int[]{2206, 109},
        new int[]{2867, 278},
        new int[]{3454, 70},
        new int[]{3499, 173},
        new int[]{2493, 248},
        new int[]{3803, 68},
        new int[]{3173, 11},
        new int[]{1680, 51},
        new int[]{812, 38},
        new int[]{3060, 201},
        new int[]{4337, 235},
        new int[]{2645, 60},
        new int[]{1047, 211},
        new int[]{2618, 32},
        new int[]{3954, 66},
        new int[]{3825, 198},
        new int[]{3102, 79},
        new int[]{4360, 13},
        new int[]{4363, 224},
        new int[]{435, 30},
        new int[]{3083, 88},
        new int[]{3817, 108},
        new int[]{3016, 2},
        new int[]{432, 3},
        new int[]{4620, 192},
        new int[]{337, 121},
        new int[]{1002, 268},
        new int[]{1475, 36},
        new int[]{1443, 129},
        new int[]{2321, 92},
        new int[]{3219, 171},
        new int[]{3547, 93},
        new int[]{629, 271},
        new int[]{941, 262},
        new int[]{2811, 93},
        new int[]{3572, 126},
        new int[]{4913, 248},
        new int[]{1168, 220},
        new int[]{3333, 92},
        new int[]{2345, 241},
        new int[]{2890, 119},
        new int[]{660, 70},
        new int[]{39, 192},
        new int[]{1300, 132},
        new int[]{2347, 270},
        new int[]{236, 137},
        new int[]{3490, 38},
        new int[]{2305, 178},
        new int[]{720, 130},
        new int[]{4602, 211},
        new int[]{381, 248},
        new int[]{1769, 207},
        new int[]{3383, 160},
        new int[]{4754, 25},
        new int[]{2391, 143},
        new int[]{264, 63},
        new int[]{1898, 13},
        new int[]{46, 228},
        new int[]{2452, 274},
        new int[]{4491, 247},
        new int[]{1209, 67},
        new int[]{3236, 19},
        new int[]{1489, 128},
        new int[]{573, 60},
        new int[]{3580, 46},
        new int[]{3395, 71},
        new int[]{2065, 60},
        new int[]{3433, 48},
        new int[]{4464, 254},
        new int[]{3936, 132},
        new int[]{3468, 185},
        new int[]{3138, 141},
        new int[]{3493, 16},
        new int[]{222, 253},
        new int[]{470, 277},
        new int[]{2771, 107},
        new int[]{3017, 182},
        new int[]{2209, 110},
        new int[]{4883, 70},
        new int[]{4258, 238},
        new int[]{4765, 102},
        new int[]{1728, 80},
        new int[]{1902, 189},
        new int[]{1939, 199},
        new int[]{4846, 173},
        new int[]{3769, 265},
        new int[]{155, 72},
        new int[]{3724, 11},
        new int[]{3600, 104},
        new int[]{3236, 4},
        new int[]{4375, 179},
        new int[]{3421, 86},
        new int[]{2641, 131},
        new int[]{1846, 235},
        new int[]{2885, 226},
        new int[]{283, 256},
        new int[]{594, 57},
        new int[]{2060, 15},
        new int[]{2794, 233},
        new int[]{2421, 109},
        new int[]{4389, 8},
        new int[]{4058, 186},
        new int[]{1779, 148},
        new int[]{4525, 188},
        new int[]{1369, 83},
        new int[]{4483, 273},
        new int[]{2208, 59},
        new int[]{2463, 29},
        new int[]{4719, 65},
        new int[]{510, 176},
        new int[]{736, 250},
        new int[]{415, 138},
        new int[]{1441, 250},
        new int[]{1441, 229},
        new int[]{1704, 20},
        new int[]{3424, 71},
        new int[]{3933, 218},
        new int[]{2918, 198},
        new int[]{1616, 103},
        new int[]{2531, 34},
        new int[]{1219, 198},
        new int[]{1466, 219},
        new int[]{1119, 249},
        new int[]{3681, 73},
        new int[]{1132, 106},
        new int[]{168, 197},
        new int[]{3003, 174},
        new int[]{4411, 45},
        new int[]{208, 15},
        new int[]{2517, 35},
        new int[]{3298, 91},
        new int[]{4572, 53},
        new int[]{1650, 207},
        new int[]{1086, 29},
        new int[]{3907, 75},
        new int[]{2090, 265},
        new int[]{2621, 161},
        new int[]{2520, 15},
        new int[]{4689, 4},
        new int[]{3399, 244},
        new int[]{273, 18},
        new int[]{779, 103},
        new int[]{4308, 228},
        new int[]{2512, 203},
        new int[]{2984, 83},
        new int[]{873, 251},
        new int[]{1058, 267},
        new int[]{573, 150},
        new int[]{4707, 278},
        new int[]{1702, 216},
        new int[]{3615, 52},
        new int[]{3041, 248},
        new int[]{2979, 261},
        new int[]{3257, 29},
        new int[]{3198, 128},
        new int[]{4077, 80},
        new int[]{3361, 33},
        new int[]{1153, 63},
        new int[]{1610, 77},
        new int[]{76, 64},
        new int[]{1008, 112},
        new int[]{318, 90},
        new int[]{948, 204},
        new int[]{3319, 165},
        new int[]{627, 177},
        new int[]{2863, 126},
        new int[]{1108, 265},
        new int[]{3876, 117},
        new int[]{1702, 197},
        new int[]{3112, 141},
        new int[]{4388, 25},
        new int[]{1752, 81},
        new int[]{429, 99},
        new int[]{3240, 258},
        new int[]{2686, 256},
        new int[]{2214, 29},
        new int[]{830, 28},
        new int[]{2369, 247},
        new int[]{4290, 84},
        new int[]{2593, 225},
        new int[]{174, 244},
        new int[]{2620, 39},
        new int[]{4684, 248},
        new int[]{2486, 49},
        new int[]{556, 27},
        new int[]{746, 206},
        new int[]{2827, 277},
        new int[]{3304, 263},
        new int[]{4051, 199},
        new int[]{4604, 274},
        new int[]{2609, 161},
        new int[]{4086, 165},
        new int[]{735, 223},
        new int[]{2191, 15},
        new int[]{4396, 176},
        new int[]{2662, 86},
        new int[]{3743, 211},
        new int[]{1373, 234},
        new int[]{1305, 43},
        new int[]{875, 12},
        new int[]{4244, 143},
        new int[]{2540, 173},
        new int[]{1330, 74},
        new int[]{3070, 41},
        new int[]{754, 35},
        new int[]{632, 187},
        new int[]{292, 44},
        new int[]{1545, 39},
        new int[]{4170, 114},
        new int[]{1934, 183},
        new int[]{287, 208},
        new int[]{1154, 98},
        new int[]{224, 254},
        new int[]{3814, 170},
        new int[]{4525, 116},
        new int[]{3637, 249},
        new int[]{2158, 149},
        new int[]{3618, 257},
        new int[]{4890, 188},
        new int[]{3106, 40},
        new int[]{1030, 272},
        new int[]{2222, 53},
        new int[]{3261, 19},
        new int[]{4245, 210},
        new int[]{4162, 253},
        new int[]{3417, 252},
        new int[]{4077, 43},
        new int[]{3256, 40},
        new int[]{1231, 202},
        new int[]{3443, 60}
    };

    int[][] tc2 = new int[][]{
        new int[]{6, 3},
        new int[]{2, 1},
        new int[]{5, 2}
    };
}