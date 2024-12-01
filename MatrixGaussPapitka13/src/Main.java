public class Main {

    public static void invertMatrix(double[][] A, int N) {
        double temp;

        // Проверка на сингулярность
        if (isSingular(A, N)) {
            System.out.println("Ошибка: Матрица сингулярная и не может быть обращена.");
            return;
        }

        // Создаём единичную матрицу
        double[][] E = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                E[i][j] = (i == j) ? 1.0 : 0.0;
            }
        }

        // Прямой ход для получения верхнетреугольной матрицы
        for (int k = 0; k < N; k++) {
            temp = A[k][k];
            if (temp == 0) {
                System.out.println("Ошибка: Деление на ноль обнаружено.");
                return;
            }
            for (int j = 0; j < N; j++) {
                A[k][j] /= temp; // Нормализация строки
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < N; i++) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        // Обратный ход для получения единичной матрицы
        for (int k = N - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        // Копируем обратную матрицу обратно в A
        for (int i = 0; i < N; i++) {
            System.arraycopy(E[i], 0, A[i], 0, N);
        }
    }

    // Метод для проверки сингулярности матрицы
    public static boolean isSingular(double[][] A, int N) {
        for (int i = 0; i < N; i++) {
            if (A[i][i] == 0) {
                return true; // Нулевой диагональный элемент обнаружен
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Пример использования
        double[][] matrix = {
                {4, 7, 8, 9, 10, 245, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {2, 64, 7, 8, 9, 76, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {238, 83, 9, 93, 63, 8, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {74, 42, 28, 4, 4, 3, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {32, 26, 287, 2, 24, 24, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {2, 64, 7, 8, 9, 76, 4, 7, 8, 9, 4, 7, 8, 9, 10, 8, 4, 7, 8, 9},
                {238, 83, 9, 93, 63, 8, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {74, 42, 28, 4, 4, 3, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {32, 26, 287, 2, 24, 24, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 9},
                {34, 85, 5, 9, 9, 6, 4, 7, 8, 9, 4, 7, 8, 9, 10, 245, 4, 7, 8, 89}
        };

        int size = matrix.length;

        invertMatrix(matrix, size);

        // Выводим обратную матрицу
        System.out.println("Обратная матрица:");
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%.4f ", value);
            }
            System.out.println();
        }
    }
}
