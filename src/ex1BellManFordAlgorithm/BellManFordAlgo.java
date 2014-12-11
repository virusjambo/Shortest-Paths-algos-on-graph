package ex1BellManFordAlgorithm;


public class BellManFordAlgo {

    int dist[];//Length of this equal to number of vertices in a graph

    Node[] graph;//Total edges in a graph which forms a graph

    int parent[];


    public BellManFordAlgo(int vertices, int edges) {
        this.dist = new int[vertices];
        this.graph = new Node[edges];
        this.parent=new int[vertices];
    }

    class Node{
        int source;
        int destination;
        int distance;


        Node(int source, int destination, int distance) {
            this.source = source;
            this.destination = destination;
            this.distance = distance;
        }


    }
    void INTIALIZE_VERTICES(){
        for(int i=0;i<dist.length;i++){
            dist[i]= Integer.MAX_VALUE;
        }
        dist[0]=0;
        parent[0]=0;
    }


    void BFA(){
        INTIALIZE_VERTICES();
        for(int i=1;i<=dist.length-1;i++){
            for(int j=0;j<graph.length;j++){
                Node temp=graph[j];
                int u=temp.source;
                int v=temp.destination;
                int distance=temp.distance;
                if(dist[u]!= Integer.MAX_VALUE && dist[v]>dist[u]+distance){
                    dist[v]=dist[u]+distance;
                    parent[v]=u;
                }



            }

        }

        for(int j=0;j<graph.length;j++){
            Node temp=graph[j];
            int u=temp.source;
            int v=temp.destination;
            int distance=temp.distance;
            if(dist[u]!= Integer.MAX_VALUE && dist[v]>dist[u]+distance){
                System.out.println("Negetive Cycle Found");

            }




        }


    }

    void printGraph(){

        for(int k=0;k<dist.length;k++){
            System.out.println(parent[k]+ "---->" +k);


        }

    }
public static  void main (String ar[]){

    BellManFordAlgo bellManFordAlgo=new BellManFordAlgo(4,6);
    Node node0=bellManFordAlgo.new Node(0,1,-1);
    Node node1=bellManFordAlgo.new Node(0,3,2);
    Node node2=bellManFordAlgo.new Node(1,2,2);
    Node node3=bellManFordAlgo.new Node(1,3,-1);
    Node node4=bellManFordAlgo.new Node(0,2,4);
    Node node5=bellManFordAlgo.new Node(2,3,-7);
    bellManFordAlgo.graph[0]=node0;
    bellManFordAlgo.graph[1]=node1;
    bellManFordAlgo.graph[2]=node2;
    bellManFordAlgo.graph[3]=node3;
    bellManFordAlgo.graph[4]=node4;
    bellManFordAlgo.graph[5]=node5;
    bellManFordAlgo.BFA();
    bellManFordAlgo. printGraph();

}


}
