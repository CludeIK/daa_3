package connectivity;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort {
    private boolean hasCycle = false;

    public List<Integer> sort(Graph graph) {
        int V = graph.getV();
        int[] state = new int[V];
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (state[i] == 0) {
                dfs(graph, i, state, order);
            }
        }

        if (hasCycle) {
            System.out.println("Error: Graph contains a cycle. Topological sorting is undefined.");
            return new ArrayList<>();
        }

        Collections.reverse(order);
        return order;
    }

    private void dfs(Graph graph, int v, int[] state, List<Integer> order) {
        state[v] = 1;
        for (Edge edge : graph.getAdjList().get(v)) {
            int next = edge.getDestination();
            if (state[next] == 1) {
                hasCycle = true;
                return;
            } else if (state[next] == 0) {
                dfs(graph, next, state, order);
            }
        }
        state[v] = 2;
        order.add(v);
    }
}