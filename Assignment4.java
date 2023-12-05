public class Assignment4 {

    static void floydWarshall(int[][] dist){
        int v = dist.length;

        for(int k=0;k<v;k++){
            for(int i=0;i<v;i++){
                for(int j=0;j<v;j++){
                    if(dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        printGraph(dist);
    }
    
    static void printGraph(int[][] graph){
        int v = graph[0].length;

        for(int i=0;i<v;i++){
            for(int j=0;j<v;j++){
                if(graph[i][j] == 99999){
                    System.out.print("INF ");
                }
                else{
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int inf = 99999;

        int[][] graph = {{0,5,3},{inf,0,5},{3,1,0}};

        floydWarshall(graph);

    }
}