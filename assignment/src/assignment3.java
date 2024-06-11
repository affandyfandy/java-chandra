import java.util.ArrayList;
import java.util.List;

public class assignment3 {
    public static void main(String[] args) {
        int[] arr = { 1, 4, 3, -6, 5, 4 };
        List<Integer> temp = new ArrayList<Integer>();
        int biggestArr = arr[0];
        int secBiggest = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (biggestArr < arr[i]) {
                biggestArr = arr[i];

            }
        }

        for (int j = 0; j < arr.length; j++) {
            if (secBiggest < arr[j] && arr[j] < biggestArr) {
                secBiggest = arr[j];
            }
        }

        for (int k = 0; k < arr.length; k++) {
            if (arr[k] == secBiggest) {
                temp.add(k);
            }
        }
        System.out.println(temp);

    }
}
