package mst;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimMST {
    public static List<Edge> findMST(Graph graph) {
        int V = graph.getV();
        boolean[] inMST = new boolean[V];
        List<Edge> result = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        inMST[0] = true;
        for (Edge edge : graph.getAdjList().get(0)) {
            pq.add(edge);
        }

        while (!pq.isEmpty() && result.size() < V - 1) {
            Edge edge = pq.poll();
            int v = edge.getDestination();

            if (inMST[v]) {
                continue;
            }

            inMST[v] = true;
            result.add(edge);

            for (Edge nextEdge : graph.getAdjList().get(v)) {
                if (!inMST[nextEdge.getDestination()]) {
                    pq.add(nextEdge);
                }
            }
        }
        return result;
    }
}