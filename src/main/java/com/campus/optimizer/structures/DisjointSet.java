package com.campus.optimizer.structures;

/**
 * Disjoint Set (Union-Find) data structure
 * Supports O(α(n)) amortized time for makeSet, find, and union operations
 * Uses path compression and union by rank optimizations
 */
public class DisjointSet {
    private int[] parent;
    private int[] rank;
    private int numSets;

    /**
     * Initialize disjoint set with n elements
     * Initially each element is its own set
     */
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        numSets = n;
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Find the representative (root) of the set containing x
     * Uses path compression: every node visited points directly to root
     * @param x element to find
     * @return representative of the set containing x
     */
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    /**
     * Union two sets containing x and y by rank
     * Smaller rank tree is attached to larger rank tree to maintain balance
     * @param x element in first set
     * @param y element in second set
     * @return true if sets were different (union occurred), false if already in same set
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // already in same set
        }

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        numSets--;
        return true;
    }

    /**
     * Check if two elements are in the same set
     * @param x first element
     * @param y second element
     * @return true if x and y have the same representative
     */
    public boolean sameSet(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * Get the current number of disjoint sets
     * @return number of sets
     */
    public int getNumSets() {
        return numSets;
    }

    /**
     * Get the representative (root) of a set
     * Same as find()
     * @param x element
     * @return representative
     */
    public int getRepresentative(int x) {
        return find(x);
    }
}