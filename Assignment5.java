import java.util.Arrays;
import java.util.PriorityQueue;
class Node implements Comparable<Node> {
    int level, profit, weight;
    double bound;
    public Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }
    @Override
    public int compareTo(Node other) {
        return Double.compare(other.bound, this.bound);
    }
}
public class Assignment5 {
    public static int knapsackDP(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }
    public static int knapsackBacktrack(int[] weights, int[] values, int capacity) {
        return knapsackHelper(weights, values, capacity, 0, 0);
    }
    private static int knapsackHelper(int[] weights, int[] values, int capacity, int index, int currentValue) {
        if (index == weights.length || capacity == 0) {
            return currentValue;
        }
        if (weights[index] > capacity) {
            return knapsackHelper(weights, values, capacity, index + 1, currentValue);
        }

        int includeItem = knapsackHelper(weights, values, capacity - weights[index], index + 1, currentValue + values[index]);
        int excludeItem = knapsackHelper(weights, values, capacity, index + 1, currentValue);
        return Math.max(includeItem, excludeItem);
    }
    public static int knapsackBB(int[] weights,int[] values,int W) {
        int n = values.length;
        Node u, v;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] valuesCopy = Arrays.copyOf(values, n);
        int[] weightsCopy = Arrays.copyOf(weights, n);
        Arrays.sort(valuesCopy);
        Arrays.sort(weightsCopy);
        priorityQueue.add(new Node(-1, 0, 0, 0));
        int maxValue = 0;
        while (!priorityQueue.isEmpty()) {
            u = priorityQueue.poll();

            if (u.level == n - 1) {
                continue;
            }
            v = new Node(u.level + 1,
                    u.profit + values[u.level + 1],
                    u.weight + weights[u.level + 1],
                    0);
            if (v.weight <= W && v.profit > maxValue) {
                maxValue = v.profit;
            }
            v.bound = bound(v, n, W, values, weights);
            if (v.bound > maxValue) {
                priorityQueue.add(v);
            }
            v = new Node(u.level + 1, u.profit, u.weight, 0);
            v.bound = bound(v, n, W, values, weights);
            if (v.bound > maxValue) {
                priorityQueue.add(v);
            }
        }
        return maxValue;
    }
    private static double bound(Node u, int n, int W, int[] values, int[] weights) {
        if (u.weight >= W) {
            return 0;
        }
        double bound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        while (j < n && totalWeight + weights[j] <= W) {
            bound += values[j];
            totalWeight += weights[j];
            j++;
        }
        if (j < n) {
            bound += (W - totalWeight) * ((double) values[j] / weights[j]);
        }
        return bound;
    }

    public static void main(String[] args) {
        int[] values = {10,5,15,7};
        int[] weights = {2,3,5,7};
        int capacity = 10;

        int maxProfitDP = knapsackDP(weights, values, capacity);
        int maxProfitBacktrack = knapsackBacktrack(weights, values, capacity);
        int maxProfitBB = knapsackBB(weights, values, capacity);

        System.out.println("Max Profit using DP: " + maxProfitDP);
        System.out.println("Max Profit using Backtracking: " + maxProfitBacktrack);
        System.out.println("Max Profit using Branch and Bound: " + maxProfitBB);

    }
}







