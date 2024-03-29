package processor;

import javax.sound.midi.Soundbank;
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
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
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
            case 4:
                transposeMatricesMenu();
                break;
            case 5:
                determinantMenu();
                break;
            case 6:
                inverseMatrixMenu();
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

    private static void transposeMatricesMenu() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();

        System.out.print("Enter matrix size: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter matrix:");
        double[][] matrix = fillMatrix(n, m);

        transposeMatrix(matrix, choice);

        showMainMenu();
    }

    private static void determinantMenu() {
        System.out.print("Enter matrix size: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter matrix:");

        double[][] matrix = fillMatrix(n, m);
        System.out.println("The determinant is:\n" + determinant(matrix));
        System.out.println();

        showMainMenu();
    }

    private static void inverseMatrixMenu() {
        System.out.print("Enter matrix size: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter matrix:");

        double[][] matrix = fillMatrix(n, m);

        System.out.println("Inverse matrix is:");
        printMatrix(inverseMatrix(matrix));

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

    private static void transposeMatrix (double[][] matrix, int choice) {
        int m = matrix.length;
        int n = matrix[0].length;
        double[][] result = null;

        switch (choice) {
            case 1:
                if (m == n) {
                    result = transposeMatrixMainDiagonal(matrix);
                } else {
                    result = null;
                }
                break;
            case 2:
                if (m == n) {
                    result = transposeMatrixSideDiagonal(matrix);
                } else {
                    result = null;
                }
                break;
            case 3:
                result = transposeMatrixVerticalLine(matrix);
                break;
            case 4:
                result = transposeMatrixHorizontalLine(matrix);
                break;
            default:
                break;
        }

        if (result == null) {
            System.out.println("Operation not supported");
        }
        else {
            printMatrix(result);
        }
    }

    private static double[][] transposeMatrixMainDiagonal (double[][] matrix) {
        int n = matrix.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }

    private static double[][] transposeMatrixSideDiagonal (double[][] matrix) {
        int n = matrix.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[n - j - 1][n - i - 1];
            }
        }

        return result;
    }

    private static double[][] transposeMatrixVerticalLine (double[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        double[][] result = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = matrix[i][m - j - 1];
            }
        }

        return result;
    }

    private static double[][] transposeMatrixHorizontalLine (double[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        double[][] result = new double[n][m];

        for (int i = 0; i < n; i++) {
            result[i] = matrix[n - i - 1];
        }

        return result;
    }

    private static double determinant (double[][] matrix) {
        double determinant;
        if (matrix.length == 2) {
            determinant = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }
        else {
            determinant = 0;
            for (int i = 0; i < matrix.length; i++) {
                determinant += Math.pow(-1, i) * matrix[0][i] * determinant(generateSubmatrix(matrix, 0, i));
            }
        }

        return determinant;
    }

    private static double[][] generateSubmatrix (double[][] sourceMatrix, int row, int column) {
        int size = sourceMatrix.length - 1;
        double[][] result = new double[size][size];

        int rowShift = 0;
        int columnShift = 0;
        for (int i = 0; i < size; i++) {
            if (i < row) {
                rowShift = 0;
            }
            else {
                rowShift = 1;
            }
            for (int j = 0; j < size; j++) {
                if (j < column) {
                    columnShift = 0;
                }
                else {
                    columnShift = 1;
                }
                result[i][j] = sourceMatrix[i + rowShift][j + columnShift];
            }
        }
        return result;
    }

    private static double[][] inverseMatrix (double[][] matrix) {

        int size = matrix.length;
        double[][] invertedMatrix;

        double determinant = determinant(matrix);
        if (determinant == 0) {
            return null;
        }
        else {
            invertedMatrix = new double[size][size];
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                invertedMatrix[i][j] =
                        Math.pow(-1, i + 1 + j + 1) * determinant(generateSubmatrix(matrix, i, j));
            }
        }
        invertedMatrix = multiplyOnConstant(invertedMatrix, 1 / determinant);
        invertedMatrix = transposeMatrixMainDiagonal(invertedMatrix);
        return invertedMatrix;
    }
}