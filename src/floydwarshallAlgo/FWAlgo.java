package floydwarshallAlgo;


public class FWAlgo {

    int vertex;
    int graph[][];
    int paths[][];

    public FWAlgo(int vertex, int[][] graph) {
        this.vertex = vertex;
        this.graph = graph;
        this.paths=new int[vertex][vertex];
        for (int i = 0; i < vertex; i++)
            for (int j = 0; j < vertex; j++) {
                if (graph[i][j] >0 && graph[i][j]!=Integer.MAX_VALUE) {
                    paths[i][j] =i+1;
                }
            }
    }

    void computeAllPairShortestPath() {
        for (int k = 0; k < vertex; k++)
            for (int i = 0; i < vertex; i++)
                for (int j = 0; j < vertex; j++) {
                    if (  (graph[i][k]!=Integer.MAX_VALUE && graph[k][j] !=Integer.MAX_VALUE  ) && graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                      //  paths[i][j]=k;
                    }
                }

    }
    public static void main(String ar[]){
        int V=4;
     int    graph[][] = { {0,   5,  Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE,  0,  3,  Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0,   1},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0} };

        FWAlgo g=new  FWAlgo(V,graph);
        g.computeAllPairShortestPath();
        printGraph(V, g.graph);
        printPaths(V, g.paths);


    }

    private static void printGraph(int v,  int    graph[][]) {
        for (int i = 0; i < v; i++) {
            System.out.println();
            for (int j = 0; j < v; j++) {
                if(graph[i][j]!=Integer.MAX_VALUE) {
                    System.out.print(" " + graph[i][j]);
                }
                else{
                    System.out.print(" "+"INF");
                }
            }
        }
        System.out.println();
    }


    private static void printPaths(int v,  int    graph[][]) {
        for (int i = 0; i < v; i++) {
            System.out.println();
            for (int j = 0; j < v; j++) {
                if(graph[i][j]!=0) {
                    System.out.println("path from " + i + "to" + j);
                    findPath(i,j,graph,v);
                }

            }
        }
        System.out.println();
    }
    private static void findPath(int i, int j, int graph[][], int v){
        if(i!=-1 && j!=-1 && i<v && j<v &&  graph[i][j]!=0 ){
            System.out.print(j);
            findPath(i, graph[i][j]-1,graph, v);
        }else{
            return;

        }

    }
}