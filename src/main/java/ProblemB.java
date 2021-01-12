import java.util.*;

public class ProblemB {
    private static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        // construct a weighted graph where each airport is a node
        // and every two airports are connected by an edge with weight
        // the manhattan distance between the two nodes
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            coordinates.add(new Coordinate(x, y));
        }
        int[][] adjMatrix = new int[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                Coordinate a = coordinates.get(i);
                Coordinate b = coordinates.get(j);
                adjMatrix[i][j] = Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
            }
        }

        // find a spanning tree of this graph such that the longest edge of
        // the tree is the minimum among all possible spanning trees.
        // I believe there should be an existing algorithm for this, or we could
        // change algorithms such as the minimum spanning tree ones into computing
        // such a spanning tree.

        // not sure how to do this

        // return the longest edge of the spanning tree
    }
}
