package connectivityaaproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*Articulation point is a vertex if you remove it graph become disconnected
1.root of DFS tree with two or more child is articulation point
2.Non root node of DFS tree if has a vertex V such that all edges passing through it
  don't have back edges to one of its ancestor
*
* */
public class ArticulationPoint {
    HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    List<Integer> forest = new ArrayList<>();
     int time=0;
    Integer parent[];
    Integer low[];
    Integer disc[];

    public ArticulationPoint(int i) {

        this.parent = new Integer[i];
        this.low = new Integer[i];
        this.disc = new Integer[i];
        for(int j=0;j<i;j++){
            parent[j]=low[j]=disc[j]=Integer.MAX_VALUE;
        }
    }

    void addEdge(Integer value, Integer value1) {
        ArrayList<Integer> list;
        if (!graph.containsKey(value)) {
            list = new ArrayList<>();

            list.add(value1);
            graph.put(value, list);

        } else {
             graph.get(value).add(value1);


        }


        if (!graph.containsKey(value1)) {
             list = new ArrayList<>();
            list.add(value);
            ;
            graph.put(value1, list);

        } else {
         graph.get(value1).add(value);


        }

    }

    void DFS(Integer value) {
        forest.add(value);
       int child=0;
        disc[value]=low[value]=++time;

        if (graph.containsKey(value)) {

            // Iterator for adjacent vertices.
            ArrayList<Integer> list = graph.get(value);
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer	temp = iterator.next();
                if (!forest.contains(temp)) {
                    parent[temp]=value;
                    child++;
                    DFS(temp);
                    low[value]=Math.min(low[value],low[temp]);
                    if(parent[value]==Integer.MAX_VALUE && child>1)
                        System.out.println(value);
                    if(parent[value]!=Integer.MAX_VALUE &&low[temp]>=disc[value]){
                    System.out.println(temp);
                    }

                }else if(temp!=parent[value]){
                    low[value]=Math.min(low[value],disc[temp]);
                }

            }
           
        }

    }

    void DFSUtil(){
        Iterator<Integer> it=graph.keySet().iterator();
        while(it.hasNext()){
            Integer temp=it.next();
            if(!forest.contains(temp)){
                DFS(temp);
            }


        }

    }




    public static void main(String arg[]) {
        ArticulationPoint articulationPoint=new ArticulationPoint(5);
        articulationPoint.addEdge(1,0);
        articulationPoint.addEdge(0,2);
        articulationPoint.addEdge(2,1);
        articulationPoint.addEdge(0,3);
        articulationPoint.addEdge(3,4);
        articulationPoint.DFSUtil();
/*
        Graph<Integer> gp = new Graph<Integer>();

        gp.createGraph(1, 2);
        gp.createGraph(4, 1);
        gp.createGraph(2, 3);
        gp.createGraph(3, 1);
        gp.createGraph(4, 3);
       */
/* gp.createGraph(0, 1);
        gp.createGraph(0, 2);
        gp.createGraph(0, 3);
        gp.createGraph(2, 3);
        gp.createGraph(3, 4);
        gp.createGraph(4, 0);*//*


        gp.DFS();
        // gp. DFSUtil(2);
        gp.printGraph();
        gp.printStack();
*/
    }

}
