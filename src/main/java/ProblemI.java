import java.util.*;

public class ProblemI {
    private enum Direction {
        U {
            @Override
            public long enroute(Robot head, Robot tail) {
                if (head.x != tail.x) {
                    return -1;
                }
                return tail.y - head.y;
            }
        },
        D {
            @Override
            public long enroute(Robot head, Robot tail) {
                if (head.x != tail.x) {
                    return -1;
                }
                return head.y - tail.y;
            }
        },
        L {
            @Override
            public long enroute(Robot head, Robot tail) {
                if (head.y != tail.y) {
                    return -1;
                }
                return head.x - tail.x;
            }
        },
        R {
            @Override
            public long enroute(Robot head, Robot tail) {
                if (head.y != tail.y) {
                    return -1;
                }
                return tail.x - head.x;
            }
        };
        private static Direction fromString(String s) {
            return Map.of(
                    "U", U,
                    "D", D,
                    "L", L,
                    "R", R
            ).get(s);
        }

        public abstract long enroute(Robot head, Robot tail);
    }

    private static class Robot {
        private final long x;
        private final long y;
        private final Direction direction;

        public Robot(long x, long y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    public static void main(String[] args) {
        /*
        Thoughts: once we figure out the time when each robot starts moving, it's easy to compute each of their
        final positions. Therefore, the key is to figure out when exactly each robot starts moving.

        A robot can possibly cause some other robots to move if its path touches the other robots' positions. Maybe
        we can model this relation as a directed graph, where each robot is a node and each directed edge means the
        tail robot is touched by the head robot's path. We can further add edge weights to represent the distance between
        the head robot and the tail robot.

        The starting node will be the robot touched by Snuke. This way, the distance from the starting node the
        each of the nodes will be the time when each robot starts moving. We can find the distances with Dijkstra's
        algorithm.
         */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            robots.add(new Robot(sc.nextInt(), sc.nextInt(), Direction.fromString(sc.next())));
        }

        // construct the graph
        List<List<long[]>> adjList = new ArrayList<>(robots.size());
        for (int i = 0; i < robots.size(); i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < robots.size(); i++) {
            for (int j = 0; j < robots.size(); j++) {
                if (i != j) {
                    Robot head = robots.get(i);
                    Robot tail = robots.get(j);
                    long enrouteDistance = head.direction.enroute(head, tail);
                    if (enrouteDistance > 0) {
                        adjList.get(i).add(new long[]{j, enrouteDistance});
                    }
                }
            }
        }

        // use Dijkstra's to figure out distance
        long[] distances = new long[robots.size()];
        for (int i = 1; i < robots.size(); i++) {
            distances[i] = Long.MAX_VALUE;
        }
        boolean[] processed = new boolean[robots.size()];
        for (int i = 0; i < robots.size(); i++) processed[i] = false;
        PriorityQueue<long[]> priorityQueue = new PriorityQueue<>((a, b) -> (int)(a[1] - b[1]));
        priorityQueue.offer(new long[]{0, 0});
        while (!priorityQueue.isEmpty()) {
            long[] pair = priorityQueue.poll();
            int index = (int) pair[0];
            long distance = pair[1];
            if (processed[index]) continue;
            processed[index] = true;
            for (long[] neighbor: adjList.get(index)) {
                if (distances[index] + neighbor[1] < distances[(int)neighbor[0]]) {
                    distances[(int)neighbor[0]] = distances[index] + neighbor[1];
                    priorityQueue.offer(new long[]{neighbor[0], -(distances[index] + neighbor[1])});
                }
            }
        }

        // now that we have the time when each robot starts moving, we can calculate the end result

    }
}
