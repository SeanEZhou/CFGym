public class ProblemK {
    public static void main(String[] args) {
        /*
            Thoughts:
            Brute force approach tries to find any possible grid for each value of k. Each of the given point can be
            any of the k^2 points on the grid. So the brute force approach checks k^2 * ... * (k-n+1)^2 grids for each
            value of k

            Recursive approach? Remove one point and let the rest form the smallest possible grid, then add the point
            back?

            First solve a simpler problem? Assuming all points are on the x axis. K points on a single line produces K-1
            segments. To form a grid, all the segments have to be divided into pieces of the same length. To form the
            smallest grid, the pieces need to be as long as possible. The longest possible piece is the gcd of all the
            segments formed. Once we find the gcd, this problem is solved.

            A slightly more difficult case is when the points are on a single line not parallel to the x or y axis.
            In this case, we can project the points onto the x axis and solve it using the last approach.

            Now what if the points form several lines all parallel to the x axis? Then we'll have to divide every segment
            on every line into pieces of equal length. We therefore need to find the gcd of all segments. Once we're done
            with the horizontal direction we also need to ensure the grid is correct vertically. To do so we need to find
            the gcd of all the height differences of the lines to determine how many more horizontal lines we need to replicate,
            so that vertically the points are also evenly spaced.

            If the points form several lines that are not parallel to the x or y axis, then again we can project each line
            to the x axis and then solve it similarly as the last problem.

            The above approaches allows me to calculate the answer given that I know the orientation of the grid, i.e.
            the c and d in the problem statement. I'm not sure how to find the c and d that would give me the best grid.

         */
    }
}
