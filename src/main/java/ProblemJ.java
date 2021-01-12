import java.util.*;

public class ProblemJ {
    private static int M = 1000000000 + 7;
    public static void main(String[] args) {
        /*
        Thoughts:
        This problem asks us to find the number of spanning trees such that deg(i) = a_i.

        Brute force: loop through all possible spanning trees and check each. Not good because exponential number of
        spanning trees to check.

        sum(deg) = 2(N-1). Maybe start with the leaves first? Recursive approach, removing the leaves and then adding
        them back? In fact, even better, maybe I can just remove one leaf at a time. Since removing a leaf from a tree
        the rest is still a tree, I could recurse on the rest of the nodes since they still need to satisfy all criterion.

        I just need to pick a leaf and, for each possible scenario this leaf is attached to the rest of the nodes,
        remove the leaf and recurse on the rest of the nodes (minus 1 on the degree of the leaf's neighbor). Summing up
        all the recursive results.
         */

        Scanner sc = new Scanner(System.in);
        M = 1000000000 + 7;
        int N = sc.nextInt();
        int[] deg = new int[N];
        for (int i = 0; i < N; i++) {
            deg[i] = sc.nextInt();
        }
        System.out.println(helper(deg) % M);
    }

    // this approach passed three tests on codeforces but ran out of memory for the fourth test
    private static int helper(int[] deg) {
        // base case
        if (deg.length == 1) {
            if (deg[0] == 0) return 1;
            else return 0;
        }

        // recursive case
        int len = deg.length;
        int leafCount = 0;
        int[] restDeg = new int[len-1]; // array to contain the degrees of the non-chosen nodes
        int restI = 0;
        for (int j : deg) {
            if (j == 1) {
                leafCount++;
                if (leafCount == 1) continue; // remove first leaf
            }
            if (restI >= len - 1) return 0; // there's no leaf at all!
            restDeg[restI] = j;
            restI++;
        }
        if (leafCount < 2) return 0; // tree must have at least two leaves
        int sum = 0;
        for (int i = 0; i < len-1; i++) {
            if (restDeg[i] > 1 || len-1 <= 1) {
                restDeg[i]--;
                sum = (sum + helper(restDeg) % M) % M;
                restDeg[i]++;
            }
        }
        return sum;
    }

    // I tried the following approach to save memory but it failed correctness

//    static int help2(Map<Integer, Integer> degCount) {
//        // base case N = 2
//        if (degCount.values().size() == 2) {
//            if (degCount.get(1) == 2) return 1;
//            else return 0;
//        }
//
//        // base case: no longer a tree
//        if (!degCount.containsKey(1) || degCount.get(1) < 2) return 0;
//
//        // recursive case
//        degCount.put(1, degCount.get(1) - 1); // remove a leaf
//        int sum = 0;
//        Set<Map.Entry<Integer, Integer>> entrySet = new HashSet<>(degCount.entrySet());
//        for (Map.Entry<Integer, Integer> entry: entrySet) {
//            int key = entry.getKey();
//            int val = entry.getValue();
//            if (key == 1) continue;
//            if (val - 1 == 0) {
//                degCount.remove(key);
//            } else {
//                degCount.put(key, val-1);
//            }
//            degCount.put(key - 1, degCount.getOrDefault(key - 1, 0) + 1);
//            sum = (sum + val * help2(degCount) % M) % M;
//
//            // put the dict back into shape
//            degCount.put(key-1, degCount.get(key - 1) - 1);
//            if (degCount.get(key-1) == 0) degCount.remove(key-1);
//            degCount.put(key, val);
//        }
//        degCount.put(1, degCount.get(1) + 1);
//        return sum;
//    }
}
