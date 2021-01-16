import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemE {
    private static class MirrorCake {
        private final int height;
        private final int weight;

        public MirrorCake(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        /*
        Thoughts: strengthen the statement to calculate the Mirror Rice Cake with the maximum number of rice cakes
        while ALSO being the minimum weight among all such possible cakes. This strengthened statement seems to allow
        for recursion with linear time.

        Passed pretest on codeforces
         */
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        List<Integer> cakes = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            cakes.add(sc.nextInt());
        }
        cakes.sort((x, y) -> y - x);

        MirrorCake result = getMinWeightHighCake(cakes);
        System.out.println(result.height);
    }

    // returns the Mirror Rice Cake with the maximum number of rice cakes
    //        while ALSO being the minimum weight among all such possible cakes
    private static MirrorCake getMinWeightHighCake(List<Integer> cakes) {
        // base case
        if (cakes.size() == 1) return new MirrorCake(1, cakes.get(0));

        // recursive case
        int maxWeight = cakes.get(0);
        MirrorCake cake = getMinWeightHighCake(cakes.subList(1, cakes.size()));
        if (cake.weight >= maxWeight) {
            return cake; // not possible to build a higher cake
        } else {
            return new MirrorCake(cake.height + 1, cake.weight + maxWeight);
        }
    }
}
