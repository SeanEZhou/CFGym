import java.util.Arrays;
import java.util.Scanner;

public class ProblemD {
    public static void main(String[] args) {
        /*
        Thoughts:
        We can use recursion. Base case is when one array is empty, then there's just 1 possible array. In the recursive
        case, if the head of the two arrays are different, then we can recurse by merge(int[m], int[n]) =
        merge(int[m-1], int[n]) + merge(int[m] + int[n-1]). If the heads are the same, then we need to remove any overlaps
        merge(int[m], int[n]) = merge(int[m-1], int[n]) + merge(int[m] + int[n-1]) - overlap(int[m-1], int[n], int[m], int[n-1]).
        We will likely call overlap often so it's important that it's efficient.

        The overlap function can also be implemented via recursion.
         */
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] l = new int[len];
        for (int i = 0; i < len; i++) {
            l[i] = sc.nextInt();
        }
        int[] r = new int[len];
        for (int i = 0; i < len; i++) {
            r[i] = sc.nextInt();
        }
        System.out.println(merge(l, r));
    }

    private static int merge(int[] l, int[] r) {
        int lLen = l.length;
        int rLen = r.length;
        if (lLen == 0 && rLen == 0) return 1;
        if (l[0] != r[0]) {
            return merge(Arrays.copyOfRange(l, 1, lLen), r) +
                    merge(l, Arrays.copyOfRange(r, 1, rLen));
        }
        else {
            return merge(Arrays.copyOfRange(l, 1, lLen), r) +
                    merge(l, Arrays.copyOfRange(r, 1, rLen)) -
                    overlap(Arrays.copyOfRange(l, 1, lLen), r, l, Arrays.copyOfRange(r, 1, rLen));
        }
    }

    private static int overlap(int[] l1, int[] r1, int[] l2, int[] r2) {
        return 0;
    }
}
