package connectivity;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KosarajuSCC {

    public static List<List<Integer>> findSCCs(Graph graph) {
        int V = graph.getV();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(graph, i, visited, stack);
            }
        }

        Graph transposed = getTranspose(graph);

        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        List<List<Integer>> sccs = new ArrayList<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> scc = new ArrayList<>();
                dfs(transposed, v, visited, scc);
                sccs.add(scc);
            }
        }
        return sccs;
    }

    private static void fillOrder(Graph graph, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (Edge edge : graph.getAdjList().get(v)) {
            int next = edge.getDestination();
            if (!visited[next]) {
                fillOrder(graph, next, visited, stack);
            }
        }
        stack.push(v);
    }

    private static Graph getTranspose(Graph graph) {
        Graph transposed = new Graph(graph.getV());
        for (int i = 0; i < graph.getV(); i++) {
            for (Edge edge : graph.getAdjList().get(i)) {
                transposed.addDirectedEdge(edge.getDestination(), edge.getSource(), edge.getWeight());
            }
        }
        return transposed;
    }

    private static void dfs(Graph graph, int v, boolean[] visited, List<Integer> scc) {
        visited[v] = true;
        scc.add(v);
        for (Edge edge : graph.getAdjList().get(v)) {
            int next = edge.getDestination();
            if (!visited[next]) {
                dfs(graph, next, visited, scc);
            }
        }
    }
}