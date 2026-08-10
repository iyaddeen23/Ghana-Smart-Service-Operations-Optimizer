package com.campus.optimizer.algorithms.graph;

import com.campus.optimizer.structures.DisjointSet;

/**
 * Kruskal's Minimum Spanning Tree Algorithm
 * 
 * Finds a minimum spanning tree (MST) of a weighted undirected graph.
 * Uses a greedy approach: sort edges by weight and add edges that don't create cycles.
 * 
 * Time complexity: O(E log E + E α(V)) = O(E log E) with sorting and disjoint set
 * Space complexity: O(V + E)
 * 
 * Algorithm:
 * 1. Sort all edges by weight in ascending order
 * 2. Create a disjoint set for all vertices
 * 3. For each edge in sorted order:
 *    - If the edge connects two different components (find):
 *      - Add edge to MST
 *      - Union the two components
 * 4. Return the MST edges and total weight
 * 
 * Correctness: By the cut property, the minimum weight edge crossing any cut
 * must be in some MST. Kruskal considers edges in increasing weight order,
 * ensuring we always pick the minimum edge for each component merge.
 */
public class Kruskal {
    
    /**
     * Represents an edge in the graph
     */
    public static class Edge implements Comparable<Edge> {
        public int u;           // first vertex
        public int v;           // second vertex
        public double weight;   // edge weight

        public Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        /**
         * Compare edges by weight for sorting
         */
        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.weight, other.weight);
        }

        @Override
        public String toString() {
            return String.format("(%d-%d, w=%.2f)", u, v, weight);
        }
    }

    /**
     * Result object containing MST information
     */
    public static class KruskalResult {
        public Edge[] mstEdges;         // edges in the minimum spanning tree
        public double totalWeight;      // sum of all edge weights in MST
        public int numEdgesInMST;       // number of edges in MST (V-1 for connected graph)

        public KruskalResult(int capacity) {
            mstEdges = new Edge[capacity];
            totalWeight = 0;
            numEdgesInMST = 0;
        }

        /**
         * Check if MST is valid (spans all vertices)
         * A connected graph with V vertices should have V-1 edges in MST
         */
        public boolean isValidMST(int numVertices) {
            return numEdgesInMST == numVertices - 1;
        }

        /**
         * Add an edge to the MST
         */
        public void addEdge(Edge edge) {
            if (numEdgesInMST < mstEdges.length) {
                mstEdges[numEdgesInMST++] = edge;
                totalWeight += edge.weight;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Kruskal MST Result:\n");
            sb.append("Total weight: ").append(String.format("%.2f", totalWeight)).append("\n");
            sb.append("Edges in MST (").append(numEdgesInMST).append("):\n");
            for (int i = 0; i < numEdgesInMST; i++) {
                sb.append("  ").append(mstEdges[i]).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Run Kruskal's algorithm on an undirected weighted graph
     * @param numVertices number of vertices in the graph
     * @param edges array of all edges in the graph
     * @return KruskalResult containing MST edges and total weight
     */
    public static KruskalResult kruskal(int numVertices, Edge[] edges) {
        KruskalResult result = new KruskalResult(numVertices - 1);

        // Sort edges by weight
        Edge[] sortedEdges = new Edge[edges.length];
        System.arraycopy(edges, 0, sortedEdges, 0, edges.length);
        mergeSort(sortedEdges, 0, sortedEdges.length - 1);

        // Initialize disjoint set
        DisjointSet ds = new DisjointSet(numVertices);

        // Process edges in sorted order
        for (Edge edge : sortedEdges) {
            // If endpoints are in different components
            if (!ds.sameSet(edge.u, edge.v)) {
                // Add edge to MST
                result.addEdge(edge);
                
                // Union the two components
                ds.union(edge.u, edge.v);
                
                // MST is complete when we have V-1 edges
                if (result.numEdgesInMST == numVertices - 1) {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Get total weight of MST
     * @param numVertices number of vertices
     * @param edges all edges in graph
     * @return total weight of minimum spanning tree
     */
    public static double getMSTWeight(int numVertices, Edge[] edges) {
        return kruskal(numVertices, edges).totalWeight;
    }

    /**
     * Check if graph is connected by testing if MST spans all vertices
     * @param numVertices number of vertices
     * @param edges all edges
     * @return true if graph is connected
     */
    public static boolean isConnected(int numVertices, Edge[] edges) {
        KruskalResult result = kruskal(numVertices, edges);
        return result.numEdgesInMST == numVertices - 1;
    }

    // ========== Helper: Merge Sort for edges ==========
    // We implement our own sort to avoid built-in equivalents

    /**
     * Sort edges using merge sort
     */
    private static void mergeSort(Edge[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merge two sorted subarrays
     */
    private static void merge(Edge[] arr, int left, int mid, int right) {
        Edge[] temp = new Edge[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
