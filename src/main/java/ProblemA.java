public class ProblemA {
    public static void main(String[] args) {
        /*
        Thoughts:
        I couldn't solve this problem but I could think of two possible approaches. The first approach is to find
        some way to efficiently get the next divisorful number based on the current and previous divisorful numbers.
        If such a method exist, we can just keep deducing the next divisorful number until we meet the kth or go pass
        10^18. There seem to be some interesting properties among divisorful numbers: for example, it seems that the
        product of two divisorful number is also divisorful, though I couldn't prove it. Based on such relationships,
        it might be possible to come up with an algorithm for getting the next divisorful number.

        If the above approach doesn't exist, i.e. knowing one divisorful number does not give any information on the
        next, then I think the best we can do is to come up with an algorithm to efficiently check if a number is divisorful
        and go through all integers until we collect k or go beyond 10^18. It seems that whether a number is divisorful
        cann be observed from its prime factorization. In non-divisorful numbers' factorization, it's possible to replace
        some prime factors with the product of smaller ones, increasing the number of distinct prime factors. But I'm
        not exactly sure how to code this up.
         */
    }
}
