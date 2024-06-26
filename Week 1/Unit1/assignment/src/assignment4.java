import java.util.HashMap;
import java.util.Map;

public class assignment4 {

    public static int[] subArray(int arr[], int n) {

        Map<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum == 0) {
                return new int[] { 0, i };
            }

            if (sumMap.containsKey(sum)) {
                return new int[] { sumMap.get(sum) + 1, i };
            }

            sumMap.put(sum, i);
        }
        // int[] indexes = new int[2];

        // for (int i = 0; i < n; i++) {
        // int sum = arr[i];
        // if (sum == 0) {
        // indexes[0] = i;
        // indexes[1] = i;
        // return indexes;
        // }
        // for (int j = i + 1; j < n; j++) {
        // sum += arr[j];
        // if (sum == 0) {
        // indexes[0] = i;
        // indexes[1] = j;
        // return indexes;
        // }
        // }
        // }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        int arr[] = { 4, 1, 2, 3, -6, 5, 4, 0 };
        int temp = arr.length;

        // Function call
        int[] result = subArray(arr, temp);
        if (result[0] != -1 && result[1] != -1)
            System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
