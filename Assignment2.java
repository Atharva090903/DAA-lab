import java.util.Arrays;

public class Assignment2 {
    static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr,int low,int high){
        int i = low - 1;
        int pivot = arr[high];

        for(int j = low;j<high;j++){
            if(arr[j] < pivot){
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, high);
        return i+1;
    }

    static void quickSort(int[] arr,int low,int high){
        if(low < high){
            int pi = partition(arr,low,high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static void merge(int[] arr,int l,int m,int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i=0;i<n1;i++){
            L[i] = arr[l+i];
        }
        for(int j=0;j<n2;j++){
            R[j] = arr[m+1+j];
        }

        int i=0;
        int j=0;
        int k=l;

        while (i<n1 && j<n2) {
            if(L[i] < R[j]){
                arr[k] = L[i];
                i++;
            }
            else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void mergeSort(int[] arr,int l,int r){
        if(l < r){
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);

            merge(arr,l,m,r);
        }
    }
    public static void main(String[] args) {
        int[] arr = {10,9,8,7,6,5,4,3,2,1};

        System.out.println(Arrays.toString(arr));

        long start = System.nanoTime();
        quickSort(arr, 0, arr.length-1);
        // mergeSort(arr, 0, arr.length-1);
        long end = System.nanoTime();

        System.out.println(Arrays.toString(arr));

        double timeTaken = (end - start) / 1e6;
        System.out.println("Time taken is: " + timeTaken + "ms");
    }
}
