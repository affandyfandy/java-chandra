import java.util.Arrays;

public class Assignment4C {
    public void multiThreadSort(int threads, int[] arr, int start, int stop) {
        if (threads > 1) {
            int midpoint = partition(arr, start, stop);
            Thread t1 = new Thread(() -> multiThreadSort(threads - 1, arr, start, midpoint));
            Thread t2 = new Thread(() -> multiThreadSort(threads - 1, arr, midpoint + 1, stop));

            // Start the threads
            t1.start();
            t2.start();

            try {
                // Ensure that the main thread waits for t1 and t2 to complete
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Sort the subarray if only one thread is available
            Arrays.sort(arr, start, stop);
        }
    }

    public int partition(int[] arr, int start, int stop) {
        int pivot = arr[stop - 1];
        int i = start - 1;
        for (int j = start; j < stop - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[stop - 1];
        arr[stop - 1] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 4, 3, 99, 62, 0, 33, 1 };
        int threads = 2;
        Assignment4C sorter = new Assignment4C();
        sorter.multiThreadSort(threads, arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
