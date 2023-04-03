package com.deezzex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IsomorphismSolver {

    private final List<List<Integer>> graphA;
    private final List<List<Integer>> graphB;

    public IsomorphismSolver(int[][] incidentMatrixA, int[][] incidentMatrixB) {
        validateInputMatrix(incidentMatrixA, incidentMatrixB);

        graphA = buildGraph(incidentMatrixA);
        graphB = buildGraph(incidentMatrixB);
    }

    private void validateInputMatrix(int[][] incidentMatrixA, int[][] incidentMatrixB) {
        if (incidentMatrixA.length != incidentMatrixB.length || incidentMatrixA[0].length != incidentMatrixB[0].length){
            throw new RuntimeException("Matrix sizes are different.");
        }
    }

    private List<List<Integer>> buildGraph(int[][] matrix){
        List<List<Integer>> graph = new ArrayList<>();
        for (int[] curRow : matrix) {
            List<Integer> row = new ArrayList<>();
            for (int curCol : curRow){
                row.add(curCol);
            }
            graph.add(row);
        }
        return graph;
    }

    public boolean checkIsomorphism() {
        List<Integer> edgesForGraphA = getEdgesCount(graphA);
        List<Integer> edgesForGraphB = getEdgesCount(graphB);
        boolean isIsomorphic = false;
        if (edgesForGraphA.size() == edgesForGraphB.size()) {
            List<List<Integer>> availableCombinations = generateCombinations(edgesForGraphA);
            isIsomorphic = availableCombinations.stream().anyMatch(combination -> isGraphsSimilar(combination, edgesForGraphB));
        }
        return isIsomorphic;
    }

    private List<Integer> getEdgesCount(List<List<Integer>> graph) {
        return graph.stream()
                .map(x -> Collections.frequency(x, 1))
                .collect(Collectors.toList());
    }

    private boolean isGraphsSimilar(List<Integer> edgesForGraphA, List<Integer> edgesForGraphB) {
        for (int i = 0; i < edgesForGraphA.size(); i++) {
            if (!Objects.equals(edgesForGraphA.get(i), edgesForGraphB.get(i))){
                return false;
            }
        }
        return true;
    }

    private List<List<Integer>> generateCombinations(List<Integer> edges) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>(edges);
        Collections.sort(tempList);
        combinationsRecursive(result, tempList, 0);
        return result;
    }

    private void combinationsRecursive(List<List<Integer>> graph, List<Integer> tempEdges, int start) {
        if (start == tempEdges.size()) {
            graph.add(new ArrayList<>(tempEdges));
            return;
        }
        for (int i = start; i < tempEdges.size(); i++) {
            if (i > start && tempEdges.get(i).equals(tempEdges.get(start))){
                continue;
            }
            Collections.swap(tempEdges, i, start);
            combinationsRecursive(graph, tempEdges, start + 1);
            Collections.swap(tempEdges, start, i);
        }
    }
}
