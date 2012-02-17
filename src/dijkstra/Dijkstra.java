/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;


import java.util.ArrayList;



/**
 *
 * @author Alain Janin-Manificat <alain.janinm@hotmail.fr>
 */

public class Dijkstra {

    static long totalGraphTimes = 0;
    static long totalAlgoTimes = 0;
    static long minGraphTimes = Long.MAX_VALUE;
    static long minAlgoTimes = Long.MAX_VALUE;
    static long maxGraphTimes = 0;
    static long maxAlgoTimes = 0;
    static int totalNbTest = 0;
    static int nbUnitTest = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length<2){
            System.out.println("Need 2 args : nbTotalTests and nbUnitTest!");
            return;
        }
        try{
            totalNbTest=Integer.parseInt(args[0]);
            nbUnitTest=Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e){
            System.out.println("Args are invalid, have to be integer!");
            return;
        }
        
        for (int i=0;i <totalNbTest ; i++ ){
            benchMark();
        }
        
        System.out.println("Nombre de tests réalisés : "+totalNbTest+"\n\n"
                + "Temp moyen pour créer "+nbUnitTest+" graphe(s) "+totalGraphTimes/(1000000.0*totalNbTest)+"ms\n"
                + "Temp le plus court pour créer "+nbUnitTest+" graphe(s) "+minGraphTimes/(1000000.0)+"ms\n"
                + "Score par rapport à la moyenne :"+minGraphTimes*100.0/(totalGraphTimes/totalNbTest)+"%\n"
                + "Temp le plus long pour créer "+nbUnitTest+" graphe(s) "+maxGraphTimes/(1000000.0)+"ms\n"
                + "Score par rapport à la moyenne :"+maxGraphTimes*100.0/(totalGraphTimes/totalNbTest)+"%\n\n"
                + "Temp moyen pour résoudre "+nbUnitTest+" fois l'algorithme Dijkstra : "+totalAlgoTimes/(1000000.0*totalNbTest)+"ms\n"
                + "Temp le plus court pour résoudre "+nbUnitTest+" fois l'algorithme Dijkstra "+minAlgoTimes/(1000000.0)+"ms\n"
                + "Score par rapport à la moyenne :"+minAlgoTimes*100.0/(totalAlgoTimes/totalNbTest)+"%\n"
                + "Temp le plus long pour résoudre "+nbUnitTest+" fois l'algorithme Dijkstra "+maxAlgoTimes/(1000000.0)+"ms\n"
                + "Score par rapport à la moyenne :"+maxAlgoTimes*100.0/(totalAlgoTimes/totalNbTest)+"%\n");
    }
    public static void benchMark(){
        long start=0;
        long startG=0;
        long graphTime=0;
        long algoTime=0;
        for(int i=0;i<nbUnitTest;i++){
            startG=System.nanoTime();
            Graph g = new Graph(13);
            g.addVertex("A");
            g.addVertex("B");
            g.addVertex("C");
            g.addVertex("D");
            g.addVertex("E");
            g.addVertex("F");
            g.addVertex("G");
            g.addVertex("H");
            g.addVertex("I");
            g.addVertex("J");
            g.addVertex("K");
            g.addVertex("L");
            g.addVertex("M");

            g.addEdge ("A", "B", 5);
            g.addEdge ("A", "C", 4);
            g.addEdge ("A", "D", 3);
            g.addEdge ("B", "C", 2);
            g.addEdge ("B", "E", 5);
            g.addEdge ("C", "B", 1);
            g.addEdge ("C", "E", 4);
            g.addEdge ("E", "F", 3);
            g.addEdge ("E", "G", 7);
            g.addEdge ("E", "H", 3);
            g.addEdge ("F", "G", 2);
            g.addEdge ("F", "I", 7);
            g.addEdge ("G", "I", 6);
            g.addEdge ("H", "I", 5);
            g.addEdge ("I", "J", 3);
            g.addEdge ("I", "K", 4);
            g.addEdge ("I", "L", 5);
            g.addEdge ("J", "M", 4);
            g.addEdge ("K", "M", 7);
            g.addEdge ("L", "M", 3);
            graphTime+=System.nanoTime()-startG;
        
            start=System.nanoTime();
            ArrayList<String> path = g.resolveDijkstra("A", "M");
            algoTime+=System.nanoTime()-start;
            
            //System.out.println(path);
        }
        //System.out.println("Nombre d'exécution : "+nbUnitTest+"\nCréation du graphe : "+graphTime/1000000.0+"ms\nRésolution :"+algoTime/1000000.0+"ms");
        totalAlgoTimes+=algoTime;
        totalGraphTimes+=graphTime;
        
        if(algoTime<minAlgoTimes)minAlgoTimes=algoTime;
        if(algoTime>maxAlgoTimes)maxAlgoTimes=algoTime;
        
        if(graphTime<minGraphTimes)minGraphTimes=graphTime;
        if(graphTime>maxGraphTimes)maxGraphTimes=graphTime;
    }
}
