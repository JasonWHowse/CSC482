import java.util.Scanner;
public class Hanoi_2 {
    /** Main method */
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = input.nextInt();
// Find the solution recursively
        System.out.println("The moves are:");
        move(n, 'A', 'C', 'B');
        input.close();
    }
    /** The method for finding the solution to move n disks
     from fromTower to toTower with auxTower */
    public static void move(int n, char from,
                            char to, char aux) {
        // make an even simpler stop condition. Note that this will go
        // through one more recursive step
        // than the previous solution.
        if (n != 0) {// Stopping condition
            if (aux != 'B') {
                move(n - 1, from, aux, to);
                moveDisk(n, from, to);
                move(n - 1, aux, to, from);
            } else {
                move(n - 1, from, to, aux);
                moveDisk(n, from, aux);
                move(n - 1, to, from, aux);
                moveDisk(n, aux, to);
                move(n - 1, from, to, aux);
            }
        }
    }
    public static void moveDisk(int n, char from, char to)
    {
        System.out.println("Move disk " + n + " from " + from + " to " +
                to);
    }
}