import java.io.FileWriter;
import java.io.IOException;

public class App {

    // jumlah pengulangan untuk ambil rata-rata
    private static final int REPEAT = 10;

    public static void main(String[] args) {

        int[] testSizes = {1, 10, 50, 100, 200, 500, 1000, 2000, 3000, 5000, 10000};

        try (FileWriter writer = new FileWriter("results.csv")) {

            writer.write("n,iterative_time_ns,recursive_time_ns\n");

            for (int n : testSizes) {

                long iterativeTime = measureIterative(n);
                long recursiveTime = measureRecursive(n);

                writer.write(n + "," + iterativeTime + "," + recursiveTime + "\n");

                System.out.println(
                    "n = " + n +
                    " | Iterative: " + iterativeTime +
                    " ns | Recursive: " + recursiveTime + " ns"
                );
            }

            System.out.println("\nBenchmark selesai. Hasil disimpan di results.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long measureIterative(int n) {
        long totalTime = 0;

        for (int i = 0; i < REPEAT; i++) {
            long start = System.nanoTime();
            FactorialIterative.factorial(n);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        return totalTime / REPEAT;
    }

    private static long measureRecursive(int n) {
        long totalTime = 0;

        for (int i = 0; i < REPEAT; i++) {
            long start = System.nanoTime();
            FactorialRecursive.factorial(n);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        return totalTime / REPEAT;
    }
}
