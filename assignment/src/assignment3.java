import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class assignment3 {

    public static int largest(int[] arr) {
        int[] arrayCopy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrayCopy);
        return arrayCopy[arrayCopy.length - 2];
    }

    public static List<Integer> secondLargest(int[] arr) {
        List<Integer> index = new ArrayList<Integer>();

        int secBiggest = largest(arr);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == secBiggest) {
                index.add(i);
            }
        }
        // int biggestArr = arr[0];
        // int secBiggest = arr[0];

        // // Find the biggest element
        // for (int i = 0; i < arr.length; i++) {
        // if (biggestArr < arr[i]) {
        // biggestArr = arr[i];
        // }
        // }

        // // Find the second biggest element
        // for (int j = 0; j < arr.length; j++) {
        // if (secBiggest < arr[j] && arr[j] < biggestArr) {
        // secBiggest = arr[j];
        // }
        // }

        // // Find indices of the second biggest element
        // for (int k = 0; k < arr.length; k++) {
        // if (arr[k] == secBiggest) {
        // index.add(k);
        // }
        // }

        return index;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 3, -6, 5, 4 };

        List<Integer> result = secondLargest(arr);
        System.out.println(result);

    }
}
