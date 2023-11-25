import java.util.ArrayList;
public class BinSorting {
    int[] baseBox = new int[]{6450, 3600};

    int[][] list = new int[][]{
            new int[] { 4155, 1570, 0}, //S12
            new int[] { 4034, 1613, 0}, //SR7.62R
            new int[] { 4431, 1868, 0}, //D.3
            new int[] { 3253, 1887, 0}, //C.300
            new int[] { 3123, 1671, 0}, //CR5IWTEMP
            new int[] { 3314, 1886, 0}, //CR5PDW
            new int[] { 1962, 1274, 0}, //P.45
            new int[] { 1362, 1205, 0}, //P9AS
            new int[] { 1290, 1220, 0}, //P9365ML(RDLI)
            new int[] { 1190, 1158, 0}, //P9365
            new int[] { 1538, 1205, 0}, //P22
            new int[] { 1391, 1072, 0}, //KL
            new int[] { 1152, 994, 0} //KS
    };

    String[] listName = new String[]{
            "S12", "SR7.62R", "D.3", "C.300", "CR5IWTEMP", "CR5PDW", "P.45", "P9AS", "P9365ML(RDLI)", "P9365", "P22", "KL", "KS"
    };

    int totalArea = 0;

    ArrayList<ArrayList<String>> optimalArrangement = new ArrayList<>();
    int[] newBoxSize = new int[]{6450, 3600};

    float ratio;
    public BinSorting(){
        this.ratio = (float)this.baseBox[0]/this.baseBox[1];
        optimalArrangement.add(new ArrayList<>());
        for(int i = 0; i < this.list.length; i++){
            list[i][2] = list[i][0]*list[i][1];
            totalArea += list[i][2];
            optimalArrangement.get(0).add(listName[i]);
        }
    }

    public BinSorting printHeight(){
        int out = 0;
        for (int[] i : list) {
            out+=i[1];
        }
        System.out.println(out);
        return this;
    }

    public BinSorting printWidth(){
        int out = 0;
        for (int[] i : list) {
            out+=i[0];
        }
        System.out.println(out);
        return this;
    }

    public BinSorting setBestArrangement(){
        int[] newBoxSize = new int[2];
        float bestRemainingArea = (float)baseBox[0]*baseBox[1];
        for(int i = 1; i<=list.length;i++){
            ArrayList<ArrayList<String>> optimalArrangement = new ArrayList<>();



            //ifoptimal
                this.optimalArrangement = optimalArrangement;
        }
        this.newBoxSize = newBoxSize;
        return this;
    }


    public static void main(String[] args) {
        BinSorting bs = new BinSorting();
        bs.printHeight().printWidth();
    }
}
