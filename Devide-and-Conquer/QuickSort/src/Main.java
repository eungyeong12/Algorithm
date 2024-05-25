public class Main {

    public static void main(String[] args) {
        int[] arr = {3,9,4,7,5,0,1,6,8,2};
        printArray(arr);
        quickSort(arr);
        printArray(arr);
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        int part2 = partition(arr, start, end); // partition을 기준으로 오른쪽 첫 번째 값
        if(start < part2-1) {
            quickSort(arr, start, part2-1); // 왼쪽 정렬
        }
        if(part2 < end) {
            quickSort(arr, part2, end); // 오른쪽 정렬
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start+end)/2];
        while(start <= end) {
            while(arr[start] < pivot) start++;
            while(arr[end] > pivot) end--;
            if(start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    private static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

    private static void printArray(int[] arr) {
        for(int data : arr) {
            System.out.print(data + ", ");
        }
        System.out.println();
    }

}