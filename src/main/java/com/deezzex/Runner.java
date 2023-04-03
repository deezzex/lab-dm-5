package com.deezzex;


import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Введіть назву файлу з матрицею інцидентності для графу А: ");

        Scanner scanner = new Scanner(System.in);

        String fileNameA = scanner.nextLine();

        System.out.println("Введіть назву файлу з матрицею інцидентності для графу B: ");

        String fileNameB = scanner.nextLine();

        scanner.close();

        int[][] incidentMatrixA = MatrixUtil.getInputMatrix(fileNameA);
        int[][] incidentMatrixB = MatrixUtil.getInputMatrix(fileNameB);

        System.out.println("Матриця інцидентності для графу А:");
        printInputMatrix(incidentMatrixA);
        System.out.println("Матриця інцидентності для графу B:");
        printInputMatrix(incidentMatrixB);

        IsomorphismSolver isomorphismSolver = new IsomorphismSolver(incidentMatrixA, incidentMatrixB);

        boolean isIsomorphic = isomorphismSolver.checkIsomorphism();

        System.out.println("Граф A ізоморфний до графу B: " + isIsomorphic);
    }


    private static void printInputMatrix(int[][] matrix) {
        for (int[] curRow : matrix) {
            for (int curCol : curRow) System.out.printf("%d ", curCol);
            System.out.println();
        }
        System.out.println();
    }
}