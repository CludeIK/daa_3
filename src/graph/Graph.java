package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int V;
    private final List<List<Edge>> adjList;
    private final List<Edge> allEdges;

    public Graph(int V) {
        this.V = V;
        this.adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        this.allEdges = new ArrayList<>();
    }

    public void addDirectedEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjList.get(source).add(edge);
        allEdges.add(edge);
    }

    public void addUndirectedEdge(int source, int destination, int weight) {
        Edge forwardEdge = new Edge(source, destination, weight);
        Edge backwardEdge = new Edge(destination, source, weight);

        adjList.get(source).add(forwardEdge);
        adjList.get(destination).add(backwardEdge);

        allEdges.add(forwardEdge);
    }

    public int getV() {
        return V;
    }

    public List<List<Edge>> getAdjList() {
        return adjList;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }
}