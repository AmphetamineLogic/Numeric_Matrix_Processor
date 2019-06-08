package processor;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrixA = fillMatrix(n, m);

        scanner.nextLine();

        n = scanner.nextInt();
        m = scanner.nextInt();
        int[][] matrixB = fillMatrix(n, m);

        int[][] sum = sumMatrix(matrixA, matrixB);
        if (sum == null) {
            System.out.println("ERROR");
        }
        else {
            printMatrix(sum);
        }
//        printMatrix(matrixA);
//        printMatrix(matrixB);

    }

    static int[][] fillMatrix(int n, int m) {
//        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int[] ints : matrix) {
            for (int j = 0; j < m; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] sumMatrix (int[][] matrixA, int[][] matrixB) {
        int n = matrixA.length;
        int m = matrixA[0].length;

        int[][] result;

        if (n != matrixB.length && m != matrixB[0].length) {
            result = null;
        }
        else {
            result = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j] = matrixA[i][j] + matrixB[i][j];
                }

            }
        }

        return result;
    }
}
