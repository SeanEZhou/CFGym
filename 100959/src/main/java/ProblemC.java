import java.util.*;

public class ProblemC {
    /*
    Thoughts:
    Obviously we could BFS over the search space step by step but this is going to take
    forever.

    I think the right idea might be to project/translate all the reflection movements into linear movements, because
    it's hard to deal with composing reflective movements but trivial to compose linear movements: it's just addition and
    subtraction. I noticed linear movements can be produced from composing reflective movements.
    For example, composing two reflections results in the point linearly moving some distance along the road.
    Once we capture all the possible movements of the point in terms of linear movements. The problem becomes a matter
    of figuring out a combination of big and small steps. We could start with modding distance to travel with the biggest
    possible step, then go with a smaller step, rinse and repeat.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfReflectionPoles = scanner.nextInt();
        List<Integer> reflectionPoles = new ArrayList<>();
        for (int i = 0; i < numberOfReflectionPoles; i++) {
            reflectionPoles.add(scanner.nextInt());
        }

        // combining any two reflection operations results in a linear movement
        // let's therefore collect all the possible linear step sizes
        Set<Integer> stepSizes = new HashSet<>();
        for (int i = 0; i < numberOfReflectionPoles; i++) {
            for (int j = 0; j < numberOfReflectionPoles; j++) {
                stepSizes.add(Math.abs(2 * reflectionPoles.get(i) - 2 * reflectionPoles.get(j)));
            }
        }

        int qSize = scanner.nextInt();
        for (int i = 0; i < qSize; i++) {
            int s = scanner.nextInt();
            int t = scanner.nextInt();

            // even number of reflections == moving with only linear movements
            // odd number of reflections == reflecting once and moving with only linear movements
            // these are the two possibilities we should explore
            int maxSteps = -1;
            int step = coverDistance(Math.abs(t - s), stepSizes); // even number of reflection
            if (step >= 0) maxSteps = Math.max(maxSteps, step);
            for (int reflectionPole : reflectionPoles) { // odd number of reflection
                int sNew = 2 * reflectionPole - s;
                step = coverDistance(Math.abs(t - sNew), stepSizes);
                if (step >= 0) maxSteps = Math.max(maxSteps, step);
            }
            System.out.println(maxSteps);
        }
    }

    private static int coverDistance(int distance, Set<Integer> availableStepSizes) {
        // returns the minimum linear steps needed to cover distance or -1 if impossible

        // not sure how to implement this. If there's only two step sizes than it's simply
        // bezout's theorem, but when there are more than two step sizes?
        return -1;
    }
}
