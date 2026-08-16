package com.campus.optimizer.algorithms;

import com.campus.optimizer.structures.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    private Graph graph;
    private Dijkstra dijkstra;

    @BeforeEach
    void setUp() {
        graph = new Graph();
        dijkstra = new Dijkstra();
    }

    @Test
    void testShortestPathSimpleGraph() {
        graph.addEdge("A", "B", 4.0);
        graph.addEdge("A", "C", 2.0);
        graph.addEdge("C", "B", 1.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 8.0);

        Map<String, Double> distances = dijkstra.findShortestPaths(graph, "A");

        assertEquals(0.0, distances.get("A"));
        assertEquals(3.0, distances.get("B"));
        assertEquals(2.0, distances.get("C"));
        assertEquals(8.0, distances.get("D"));
    }

    @Test
    void testPathToSelf() {
        graph.addVertex("A");
        Map<String, Double> distances = dijkstra.findShortestPaths(graph, "A");

        assertEquals(0.0, distances.get("A"));
    }

    @Test
    void testUnreachableNode() {
        graph.addVertex("A");
        graph.addVertex("B");

        Map<String, Double> distances = dijkstra.findShortestPaths(graph, "A");

        assertEquals(Double.POSITIVE_INFINITY, distances.getOrDefault("B", Double.POSITIVE_INFINITY));
    }
}
