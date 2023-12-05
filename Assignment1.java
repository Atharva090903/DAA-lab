//Assignment 1
import java.util.Arrays;
import java.util.Random;

public class Assignment1 {

    public static void main(String[] args) {
        int[] array = generateRandomArray(5000);
        Arrays.sort(array);

        // Print the sorted array
        System.out.println("Sorted Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        int target = 42;
        int index = binarySearch(array, target);

        if (index != -1) {
            System.out.println("Found " + target + " at index " + index);
        } else {
            System.out.println(target + " not found in the array.");
        }
    }

    // Generate an array of random numbers
    public static int[] generateRandomArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100); // Generate random numbers 0-99
        }
        return array;
    }

    // Binary Search on a sorted array
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Target not found
    }
}

