package processor;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        showMainMenu();

    }

    private static void showMainMenu() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix to a constant\n" +
                "3. Multiply matrices\n" +
                "0. Exit\n");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addMatricesMenu();
                break;
            case 2:
                multiplyMarixOnConstantMenu();
                break;
            case 3:
                multiplyMatricesMenu();
                break;
            case 0:
                return;
            default:
                showMainMenu();
                break;
        }
    }

    private static void addMatricesMenu() {
        System.out.print("Enter size of first matrix: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        double[][] matrixA = fillMatrix(n, m);

        System.out.print("Enter size of second matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        double[][] matrixB = fillMatrix(n, m);

        System.out.println("Adding result is:");
        printMatrix(addMatrices(matrixA, matrixB));

        showMainMenu();
    }

    private static void multiplyMarixOnConstantMenu() {
        System.out.print("Enter size of the matrix: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        double[][] matrix = fillMatrix(n, m);

        System.out.print("Enter the constant: ");
        double c = scanner.nextInt();

        System.out.println("Multiplying on constant result is: ");
        printMatrix(multiplyOnConstant(matrix, c));

        showMainMenu();
    }

        private static void multiplyMatricesMenu() {
        System.out.print("Enter size of first matrix: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter first matrix:");
        double[][] matrixA = fillMatrix(n, m);

        System.out.print("Enter size of second matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();

        System.out.println("Enter second matrix:");
        double[][] matrixB = fillMatrix(n, m);

        System.out.println("The multiplication result is:");
        printMatrix(multiplyMatrices(matrixA, matrixB));

        showMainMenu();
    }

    private static double[][] multiplyMatrices(double[][] matrixA, double[][] matrixB) {
        double[][] result = null;
        int nA = matrixA.length;
        int mA = matrixA[0].length;
        int nB = matrixB.length;
        int mB = matrixB[0].length;

        // check if matrices can be multiplied
        if (mA == nB) {
            result = new double[nA][mB];
            double temp = 0;

            for (int i = 0; i < nA; i++) {
                for (int j = 0; j < mB; j++) {
                    for (int k = 0; k < nB; k++) {
                        temp += matrixA[i][k] * matrixB[k][j];
                    }
                    result[i][j] = temp;
                    temp = 0;
                }
            }
        }
        return result;
    }

    private static double[][] fillMatrix(int n, int m) {
        double[][] matrix = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    private static void printMatrix(double[][] matrix) {
        int n = matrix[0].length;
        for (double[] ints : matrix) {
            for (int j = 0; j < n; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static double[][] addMatrices (double[][] matrixA, double[][] matrixB) {
        int n = matrixA.length;
        int m = matrixA[0].length;

        double[][] result;

        if (n != matrixB.length && m != matrixB[0].length) {
            result = null;
        }
        else {
            result = new double[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j] = matrixA[i][j] + matrixB[i][j];
                }
            }
        }

        return result;
    }

    private static double[][] multiplyOnConstant (double[][] matrix, double constant) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] *= constant;
            }
        }
        return matrix;
    }
}
