package shortestpath;

import graph.Edge;
import graph.Graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    public static long operationCount = 0;

    public static int[] findShortestPaths(Graph graph, int source) {
        operationCount = 0;
        int V = graph.getV();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{source, 0});
        operationCount++;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int currentDist = current[1];
            operationCount++;

            if (currentDist > dist[u]) {
                continue;
            }

            for (Edge edge : graph.getAdjList().get(u)) {
                operationCount++;
                int v = edge.getDestination();
                int weight = edge.getWeight();

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                    operationCount++;
                }
            }
        }
        return dist;
    }
}