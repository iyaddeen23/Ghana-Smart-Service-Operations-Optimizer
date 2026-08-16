package com.campus.optimizer.algorithms.graph;

import com.campus.optimizer.structures.PriorityQueue;
import java.util.*;

/**
 * Dijkstra's Shortest Path Algorithm implementation.
 * Calculates single-source shortest paths in a weighted graph.
 */
public class Dijkstra {

    public static class Edge {
        public final String target;
        public final double weight;

        public Edge(String target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static class NodeDistance implements Comparable<NodeDistance> {
        public final String node;
        public final double distance;

        public NodeDistance(String node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public static class Result {
        public final Map<String, Double> distances;
        public final Map<String, String> previousNodes;

        public Result(Map<String, Double> distances, Map<String, String> previousNodes) {
            this.distances = distances;
            this.previousNodes = previousNodes;
        }

        public List<String> getShortestPathTo(String target) {
            List<String> path = new ArrayList<>();
            if (!distances.containsKey(target) || distances.get(target) == Double.POSITIVE_INFINITY) {
                return path;
            }
            for (String at = target; at != null; at = previousNodes.get(at)) {
                path.add(at);
            }
            Collections.reverse(path);
            return path;
        }
    }

    public static Result findShortestPaths(Map<String, List<Edge>> graph, String source) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();

        for (String node : graph.keySet()) {
            distances.put(node, Double.POSITIVE_INFINITY);
            previousNodes.put(node, null);
        }

        distances.put(source, 0.0);
        pq.insert(new NodeDistance(source, 0.0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.extractMin();
            String u = current.node;

            if (current.distance > distances.get(u)) {
                continue;
            }

            List<Edge> neighbors = graph.getOrDefault(u, Collections.emptyList());
            for (Edge edge : neighbors) {
                double newDist = distances.get(u) + edge.weight;
                if (newDist < distances.getOrDefault(edge.target, Double.POSITIVE_INFINITY)) {
                    distances.put(edge.target, newDist);
                    previousNodes.put(edge.target, u);
                    pq.insert(new NodeDistance(edge.target, newDist));
                }
            }
        }

        return new Result(distances, previousNodes);
    }
}
