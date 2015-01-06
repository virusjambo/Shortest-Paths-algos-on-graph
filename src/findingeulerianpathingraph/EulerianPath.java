package findingeulerianpathingraph;

import java.util.*;

/**
 * Created by user on 23-12-2014.
 */
public class EulerianPath {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    //For adding Edges to graph
    void addEdge(int source, int dest) {
        if (graph.containsKey(source)) {
            graph.get(source).add(dest);
        } else {
            List<Integer> integers = new ArrayList<>();
            integers.add(dest);
            graph.put(source, integers);
        }

        if (graph.containsKey(dest)) {
            graph.get(dest).add(source);
        } else {
            List<Integer> integers = new ArrayList<>();
            integers.add(source);
            graph.put(dest, integers);
        }
    }


    /*
    *Eulerian circuit suggest there can be to vertices with odd edges...
    *if odd edges exist start at any one .
     * do
    * */
    int findOddDegreeEdges() {
        Iterator<Integer> keySet = graph.keySet().iterator();
        int edge = 0;//set to return first vertex if no odd edges vertices present
        while (keySet.hasNext()) {
            Integer odd = keySet.next();
            if ((graph.get(odd).size() % 2) != 0) {
           edge=odd;
                break;
            }

        }
        return edge;
    }

/*
*
*
* */
boolean isSafeToProceed(int vertex) {
    int count = graph.get(vertex).size();



    if ((count > 1)) {
        //choose non bridge node
        Iterator<Integer> adjEdges = graph.get(vertex).iterator();
        while (adjEdges.hasNext()) {
            int rootDfs = adjEdges.next();
            if (rootDfs != -1) {
                int before = DFS(rootDfs, 1);
                graph.get(vertex).remove((Integer)rootDfs);
                graph.get(rootDfs).remove((Integer)vertex);
                int after = DFS(rootDfs, 1);
                graph.get(vertex).add((Integer)rootDfs);
                graph.get(rootDfs).add((Integer)vertex);
                if (before != after) {
                    continue;
                } else {
                    dfsForest.clear();
                    return true;
                }
            }
        }
    } else {
        dfsForest.clear();
        return true;
    }
    dfsForest.clear();
    return false;
}



   List<Integer> dfsForest=new ArrayList<>();
   int  DFS(int vertex,int count){
       dfsForest.add(vertex);
       Iterator<Integer> adjEdges = graph.get(vertex).iterator();
       while(adjEdges.hasNext()){
           int rootDfs=adjEdges.next();
           if(rootDfs!=-1 && !dfsForest.contains(vertex)){
               count=DFS(vertex,count++);

           }


       }
       return count;


    }

  void eulerianPathUtil(int vertex){


      List<Integer> list= graph.get(vertex);
     for(Integer i:list){
          int rootDfs=i;
          if(rootDfs!=-1 && isSafeToProceed(rootDfs)){
              graph.get(vertex).remove((Integer)rootDfs);
              graph.get(rootDfs).remove((Integer)vertex);
              System.out.println(vertex + "--"+rootDfs );
              eulerianPathUtil(rootDfs);
          }


      }


 }

    void eulerPath(){
        int startVertex= findOddDegreeEdges();
        eulerianPathUtil(startVertex);
    }

    public static  void main(String a[]){
      /*  EulerianPath eulerianPat=new EulerianPath();
        eulerianPat.addEdge(0, 1);
        eulerianPat.addEdge(0, 2);
        eulerianPat.addEdge(1, 2);
        eulerianPat.addEdge(2, 3);
        eulerianPat.eulerPath();*/


        EulerianPath eulerianPat1=new EulerianPath();
        eulerianPat1.addEdge(1, 0);
        eulerianPat1.addEdge(0, 2);
        eulerianPat1.addEdge(2, 1);
        eulerianPat1.addEdge(0, 3);
        eulerianPat1.addEdge(3, 4);
        eulerianPat1.addEdge(3, 2);
        eulerianPat1.addEdge(3, 1);
        eulerianPat1.addEdge(2, 4);
        eulerianPat1.eulerPath();
    }

}
