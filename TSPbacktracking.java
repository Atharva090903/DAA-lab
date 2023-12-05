import java.util.ArrayList;

public class TSPbacktracking{

    private static int[][] graph;
    private static boolean[] visited;
    private static ArrayList<Integer> currentPath;
    private static ArrayList<Integer> bestPath;
    private static int minCost = Integer.MAX_VALUE;

    private static void tsp(int currentCity, int depth) {
        visited[currentCity] = true;
        currentPath.add(currentCity);
        if (depth == graph.length) {
            int cost = calculateCost(currentPath);
            if (cost < minCost) {
                minCost = cost;
                bestPath = new ArrayList<>(currentPath);
            }
        } else {
            for (int nextCity = 0; nextCity < graph.length; nextCity++) {
                if (!visited[nextCity]) {
                    tsp(nextCity, depth + 1);
                }
            }
        }
        visited[currentCity] = false;
        currentPath.remove(currentPath.size() - 1);
    }

    private static int calculateCost(ArrayList<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph[path.get(i)][path.get(i + 1)];
        }
        cost += graph[path.get(path.size() - 1)][path.get(0)];
        return cost;
    }

    public static void main(String[] args) {
        graph = new int[][] {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int numCities = graph.length;
        visited = new boolean[numCities];
        currentPath = new ArrayList<>();
        bestPath = new ArrayList<>();

        tsp(0, 1);

        // Print the result
        System.out.println("Best TSP Path: " + bestPath);
        System.out.println("Minimum Cost: " + minCost);
    }
}



