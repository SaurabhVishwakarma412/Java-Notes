import java.util.ArrayList;
import java.util.List;

// Lesson 16: backtracking and dynamic programming. Practice: practice-questions.md#16-backtracking-and-dynamic-programming.
public class sixteenth {
    public static void main(String[] args) {
        System.out.println("Subsets of [1, 2, 3]:");
        printSubsets(new int[] {1, 2, 3}, 0, new ArrayList<Integer>());

        int index = 40;
        long[] memo = new long[index + 1];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = -1;
        }
        System.out.println("Fibonacci with memoization: " + fibonacciMemo(index, memo));
        System.out.println("Fibonacci with tabulation: " + fibonacciTabulated(index));

        int[] coins = {1, 3, 4};
        int amount = 6;
        System.out.println("Fewest coins for " + amount + ": " + minimumCoins(coins, amount));
    }

    // Backtracking makes a choice, explores it, then undoes the choice.
    public static void printSubsets(int[] values, int index, List<Integer> chosen) {
        if (index == values.length) {
            System.out.println(chosen);
            return;
        }

        printSubsets(values, index + 1, chosen);
        chosen.add(values[index]);
        printSubsets(values, index + 1, chosen);
        chosen.remove(chosen.size() - 1);
    }

    // Memoization is top-down DP: cache each overlapping subproblem once.
    public static long fibonacciMemo(int index, long[] memo) {
        if (index < 0 || index > 92 || memo == null || memo.length <= index) {
            throw new IllegalArgumentException("index must be 0..92 and memo must have index + 1 entries");
        }
        if (index <= 1) return index;
        if (memo[index] != -1) return memo[index];
        memo[index] = fibonacciMemo(index - 1, memo) + fibonacciMemo(index - 2, memo);
        return memo[index];
    }

    // Tabulation is bottom-up DP: solve smaller states before larger states.
    public static long fibonacciTabulated(int index) {
        if (index < 0 || index > 92) {
            throw new IllegalArgumentException("index must be between 0 and 92");
        }
        if (index <= 1) return index;
        long previous = 0;
        long current = 1;
        for (int i = 2; i <= index; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    // Unbounded coin change: dp[value] is the fewest coins needed for that value.
    public static int minimumCoins(int[] coins, int amount) {
        if (coins == null || amount < 0) {
            throw new IllegalArgumentException("coins must not be null and amount must be non-negative");
        }
        for (int coin : coins) {
            if (coin <= 0) {
                throw new IllegalArgumentException("coin denominations must be positive");
            }
        }
        int impossible = amount + 1;
        int[] dp = new int[amount + 1];
        for (int value = 1; value <= amount; value++) {
            dp[value] = impossible;
            for (int coin : coins) {
                if (coin <= value && dp[value - coin] != impossible) {
                    dp[value] = Math.min(dp[value], dp[value - coin] + 1);
                }
            }
        }
        return dp[amount] == impossible ? -1 : dp[amount];
    }
}