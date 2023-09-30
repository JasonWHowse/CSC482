import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input= new Scanner(System.in);
        for(int a = input.nextInt();a!=0;a=input.nextInt()){
            System.out.println(new SCEN(a));
        }
    }
}

class SCEN{
    private String answer = "";

    SCEN(int x){
        this.answer = ""+(x + Math.abs(x%2) + 4) * 5;
    }

    @Override
    public String toString() {
        return "" + answer;
    }
}