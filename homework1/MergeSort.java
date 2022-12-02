import java.util.Arrays;

public class MergeSort {
    public final static void main(String[] args) {
        int arr1[] = {};
        int arr2[] = {1,2,3};
        int arr3[] = {6,1,24,5,1,2};

        boolean ok = true;

        ok &= Arrays.equals(mergeSort(arr1), new int[] {});
        ok &= Arrays.equals(mergeSort(arr2), new int[] {1,2,3});
        ok &= Arrays.equals(mergeSort(arr3), new int[] {1,1,2,5,6,24});
        System.out.println(ok ? "Tests passed" : "Tests did not pass");
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        } else {
            int middle = arr.length / 2;

            // Split array in two and mergeSort them
            int[] left = Arrays.copyOfRange(arr, 0, middle);
            int[] right = Arrays.copyOfRange(arr, middle, arr.length);
            left = mergeSort(left);
            right = mergeSort(right);

            // Merge the sorted sub-arrays
            return merge(left, right);
        }
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int left = 0, right = 0;
        int i = 0;

        for (; left < arr1.length && right < arr2.length; i++) {
            // Add elements to the result in order
            if (arr1[left] < arr2[right]) {
                result[i] = arr1[left];
                left++;
            } else {
                result[i] = arr2[right];
                right++;
            }
        }

        // Add any elements left in either of the arrays
        while (left < arr1.length) {
            result[i] = arr1[left];
            left++;
            i++;
        }
        while (right < arr2.length) {
            result[i] = arr2[right];
            right++;
            i++;
        }

        return result;
    }
}
