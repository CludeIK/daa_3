package mst;

import dsu.DSU;
import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMST {
    public static List<Edge> findMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.getAllEdges());

        Collections.sort(edges);

        DSU dsu = new DSU(graph.getV());

        for (Edge edge : edges) {
            int u = edge.getSource();
            int v = edge.getDestination();

            if (dsu.union(u, v)) {
                result.add(edge);
            }
        }
        return result;
    }
}