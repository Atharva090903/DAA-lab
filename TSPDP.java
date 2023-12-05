public class TSPDP {
    private int numCities;
    private int[][] costMatrix;
    private int[][] memo;
    private int ALL_CITIES_MASK;

    public TSPDP(int[][] costMatrix) {
        this.numCities = costMatrix.length;
        this.costMatrix = costMatrix;
        this.memo = new int[numCities][1 << numCities]; // Use 1 << numCities for ALL_CITIES_MASK
        this.ALL_CITIES_MASK = (1 << numCities) - 1; // Initialize ALL_CITIES_MASK here
    }

    public int tsp(int mask, int pos) {
        if (mask == ALL_CITIES_MASK) {
            // All cities have been visited
            return costMatrix[pos][0];
        }

        if (memo[pos][mask] != 0) {
            return memo[pos][mask];
        }

        int minCost = Integer.MAX_VALUE;

        for (int nextCity = 0; nextCity < numCities; nextCity++) {
            if ((mask & (1 << nextCity)) == 0) {
                int newMask = (mask | (1 << nextCity));
                int newCost = costMatrix[pos][nextCity] + tsp(newMask, nextCity);

                minCost = Math.min(minCost, newCost);
            }
        }
        memo[pos][mask] = minCost;
        return minCost;

    }

    public void findOptimalPath() {
        int mask = 1;
        int pos = 0;
        System.out.println("Optimal TSP Path:");
        while (true) {
            System.out.print((pos + 1) + " -> ");
            int nextCity = -1;
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < numCities; i++) {
                if ((mask & (1 << i)) == 0) {
                    int newMask = (mask | (1 << i));
                    int newCost = costMatrix[pos][i] + memo[i][newMask];

                    if (newCost < minCost) {
                        minCost = newCost;
                        nextCity = i;
                    }
                }
            }
            if (nextCity == -1) {
                break;
            }
            mask |= (1 << nextCity);
            pos = nextCity;
        }
        System.out.println("1");

    }

    public static void main(String[] args) {
        int[][] costMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        TSPDP tspSolver = new TSPDP(costMatrix);
        int minCost = tspSolver.tsp(1, 0);

        System.out.println("Minimum Cost: " + minCost);
        tspSolver.findOptimalPath();
    }
}
