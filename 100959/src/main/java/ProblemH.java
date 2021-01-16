import java.util.*;

public class ProblemH {
    private static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        /*
        Thought process: We could calculate the probability of each cell being visited and then use linearity of expectation.
        We can maybe calculate the probability of a cell visited based on the probability of visiting surrounding
        cells? It seems that we can update the probabilities with each iteration up til the N iteration, in a sort of BFS
        manner, expanding from (0, 0).

        Turns out we can calculate the probabilities recursively based on P[(i, j) visited within N steps] = P[(i, j) visited within
        N-1 steps] + 1/4(P[Landing on (i-1, j) after N-1 steps] + P[Landing on (i+1, j) after N-1 steps] +
        P[Landing on (i, j-1) after N-1 steps] + P[Landing on (i, j+1) after N-1 steps]).

        And P[Landing on (i, j) after N steps] can similarly be calculated via
        P[Landing on (i, j) after N steps] = 1/4(sum of probabilities of landing on neighboring squares after N-1 steps)

        We can therefore keep two expanding grids of probabilities that update with each iteration based on the above
        formulas
         */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Map<Coordinate, Double> landingProbability = new HashMap<>();
        landingProbability.put(new Coordinate(0, 0), 1.0);
        Map<Coordinate, Double> visitedProbability = new HashMap<>();
        visitedProbability.put(new Coordinate(0, 0), 1.0);
        for (int i = 0; i < N; i++) {
            Map<Coordinate, Double> newLandingProbability = new HashMap<>();
            for (Map.Entry<Coordinate, Double> entry: landingProbability.entrySet()) {
                Coordinate coordinate = entry.getKey();
                double probability = entry.getValue();
                int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int[] delta : deltas) {
                    Coordinate neighbor = new Coordinate(coordinate.x + delta[0], coordinate.y + delta[1]);
                    visitedProbability.put(neighbor, visitedProbability.getOrDefault(neighbor, 0.0) + 1.0 / 4.0 * probability);
                    newLandingProbability.put(neighbor, visitedProbability.getOrDefault(neighbor, 0.0) + 1.0 / 4.0 * probability);
                }
                landingProbability = newLandingProbability;
            }
        }
        double expectation = 0;
        for (double probability: visitedProbability.values()) {
            expectation += probability;
        }

        // I know this is not the right way because it'll overflow and I'm supposed to mod M along the calculation
        // But I don't know how to deal with it since I'm using doubles rather than fractions.
        System.out.println((int)(expectation * (double)Math.pow(4.0, N)) % M);

    }
}
