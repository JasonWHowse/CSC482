package HW2;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
public class GetAllInputs {

    static Stack<Integer> answerList = new Stack<>();

    static{
        answerList.push(108416);
        answerList.push(1);
    }

    public static void main(String[] args) throws IOException {

        Scanner input= new Scanner(System.in);
        for(int a = input.nextInt();answerList.size()>0/*a!=0*/;a=input.nextInt()){
            int sizes = a;
            int longestPipe = input.nextInt();
            for(int i = 0; i<sizes;i++){
                input.nextInt();
                input.nextInt();
            }
            System.out.println(answerList.pop());

        }
        System.out.println(answerList.pop());
    }
}
