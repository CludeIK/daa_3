import connectivity.KosarajuSCC;
import connectivity.TopologicalSort;
import graph.Graph;
import mst.KruskalMST;
import mst.PrimMST;
import shortestpath.Dijkstra;

import java.util.Random;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000};

        System.out.println("=== Assignment 3: Benchmarks ===\n");

        for (int V : sizes) {
            System.out.println("--------------------------------------------------");
            System.out.println("Testing for V = " + V);
            System.out.println("--------------------------------------------------");

            Graph undirectedGraph = generateConnectedUndirectedGraph(V);

            long start = System.nanoTime();
            KruskalMST.findMST(undirectedGraph);
            long kruskalTime = System.nanoTime() - start;

            start = System.nanoTime();
            PrimMST.findMST(undirectedGraph);
            long primTime = System.nanoTime() - start;

            System.out.printf("MST Kruskal Time: %,d ns\n", kruskalTime);
            System.out.printf("MST Prim Time:    %,d ns\n", primTime);

            Graph directedGraph = generateDirectedGraph(V);
            start = System.nanoTime();
            KosarajuSCC.findSCCs(directedGraph);
            long sccTime = System.nanoTime() - start;
            System.out.printf("SCC Kosaraju Time: %,d ns\n", sccTime);

            start = System.nanoTime();
            Dijkstra.findShortestPaths(directedGraph, 0);
            long dijkstraTime = System.nanoTime() - start;
            System.out.printf("Dijkstra Time:     %,d ns | Operations: %,d\n", dijkstraTime, Dijkstra.operationCount);

            Graph dag = generateDAG(V);
            TopologicalSort ts = new TopologicalSort();
            start = System.nanoTime();
            ts.sort(dag);
            long topoTime = System.nanoTime() - start;
            System.out.printf("Topological Sort:  %,d ns\n", topoTime);
            System.out.println();
        }
    }

    private static Graph generateConnectedUndirectedGraph(int V) {
        Graph graph = new Graph(V);
        for (int i = 0; i < V - 1; i++) {
            graph.addUndirectedEdge(i, i + 1, random.nextInt(100) + 1);
        }
        int extraEdges = V * 2;
        for (int i = 0; i < extraEdges; i++) {
            int u = random.nextInt(V);
            int v = random.nextInt(V);
            if (u != v) {
                graph.addUndirectedEdge(u, v, random.nextInt(100) + 1);
            }
        }
        return graph;
    }

    private static Graph generateDirectedGraph(int V) {
        Graph graph = new Graph(V);
        int edges = V * 3;
        for (int i = 0; i < edges; i++) {
            int u = random.nextInt(V);
            int v = random.nextInt(V);
            if (u != v) {
                graph.addDirectedEdge(u, v, random.nextInt(100) + 1);
            }
        }
        return graph;
    }

    private static Graph generateDAG(int V) {
        Graph graph = new Graph(V);
        int edges = V * 2;
        for (int i = 0; i < edges; i++) {
            int u = random.nextInt(V);
            int v = random.nextInt(V);
            if (u < v) {
                graph.addDirectedEdge(u, v, random.nextInt(100) + 1);
            } else if (u > v) {
                graph.addDirectedEdge(v, u, random.nextInt(100) + 1);
            }
        }
        return graph;
    }
}