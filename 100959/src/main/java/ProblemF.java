import java.util.*;

public class ProblemF {
    private static class Interval {
        private final int low;
        private final int high;

        public Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public static void main(String[] args) {
        /*
        Thoughts:
        If the same-colored cards observed do not form a continuous interval (e.g. 1 2 1) then no M is possible because the coloring scheme
        is already broken. Otherwise, an M is possible iff it satisfies:
        1. none of M's multiples cut through a continuous interval of observed same-colored cards. Otherwise we would break the coloring scheme
        2. every space between two intervals of observed same-colored cards must have at least one multiple of M, otherwise we
        would have some [NM+1, (N+1)M] containing cards of more than one color, also breaking the coloring scheme. In fact,
        M itself must be within the first such empty space.
        3. 20M >= the largest known a_i, otherwise there will not be enough colors
         */
        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();
        // extract all the intervals while checking for incontinous intervals
        Set<Integer> seenColor = new HashSet<>();
        List<Interval> sameColorIntervals = new ArrayList<>();
        int low = 1;
        int high = sc.nextInt();
        int color = sc.nextInt();
        for (int i = 1; i < count; i++) {
            int nextNumber = sc.nextInt();
            int nextColor = sc.nextInt();
            if (nextColor == color) {
                high = nextNumber;
            } else {
                if (seenColor.contains(nextColor)) {
                    System.out.println(0); // non-consistent interval, color scheme broken, no possible M
                    return;
                }
                seenColor.add(color);
                sameColorIntervals.add(new Interval(low, high));
                low = nextNumber;
                high = nextNumber;
                color = nextColor;
            }
        }
        int maxNum = high;
        sameColorIntervals.add(new Interval(low, high));

        if (sameColorIntervals.size() <= 1) {
            System.out.println(-1); // only seen one color, infinite possible M
            return;
        }

        int mCount = 0;
        for (int m = sameColorIntervals.get(0).high; m < sameColorIntervals.get(1).low; m++) {
            // the first space between two different color groups. If m were to exist, it comes from this place
            if (20 * m > 0 && 20 * m < maxNum || !validM(m, sameColorIntervals)) continue;
            mCount++;
        }
        System.out.println(mCount);
    }

    private static boolean validM(int m, List<Interval> sameColorIntervals) {
        for (int j = 1; j < sameColorIntervals.size(); j++) {
            Interval interval = sameColorIntervals.get(j);
            for (int x = interval.low; x < interval.high; x++) {
                if (x % m == 0) {
                    return false;
                }
            }
            if (j+1 < sameColorIntervals.size()) {
                Interval nextInterval = sameColorIntervals.get(j+1);
                boolean foundMultiple = false;
                for (int i = interval.high; i < interval.low; i++) {
                    if (i % m == 0) {
                        foundMultiple = true;
                        break;
                    }
                }
                if (!foundMultiple) return false;
            }
        }
        return true;
    }
}
