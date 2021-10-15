
import java.util.Stack;
import java.util.Scanner;

class SubSet {

    int set[];
    int sum;
    int k;

    Stack<Integer> solutionSet;
    Stack<Integer> solutionSetbinary;
    boolean hasSolution;

    SubSet(int set[], int sum) {
        this.set = set;
        this.sum = sum;
        this.solutionSet = new Stack<>();
        this.solutionSetbinary = new Stack<>();
        hasSolution = false;
    }

    public void solve(int s, int idx) {

        this.k = idx;
        if (s > sum) {
            return;
        }

        if (s == sum) {
            hasSolution = true;

            displaySolutionSet();

            return;
        }

        for (int i = idx; i < set.length; i++) {

            solutionSet.push(set[i]);
            solutionSetbinary.push(1);

            solve(s + set[i], i + 1);

            for (int j = i; j < k; j++) {
                solutionSetbinary.pop();
            }
            solutionSetbinary.push(0);
            solutionSet.pop();
        }
    }

    private void displaySolutionSet() {
        System.out.print("solutionSet : ");
        for (Integer item : solutionSet) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.print("solutionSetBinary : ");
        for (int i = 0; i < set.length; i++) {
            if (solutionSetbinary.size() > i) {
                System.out.print(solutionSetbinary.get(i) + " ");
            } else {
                System.out.print("0 ");
            }
        }
        System.out.println();
    }

}

public class backtracking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int set[] = {5, 10, 12, 13, 15, 18};
        int count = set.length;

        //int set[] = new int[6];
        /*for (int i = 0; i < count; i++) {
            set[i] = sc.nextInt();
        }*/
        //int size = 30;
        //int size =sc.nextInt();
        int size = 30;
        SubSet ss = new SubSet(set, size);

        ss.solve(0, 0);

        if (ss.hasSolution == false) {
            System.out.print("No Solution");
        }
    }
}
