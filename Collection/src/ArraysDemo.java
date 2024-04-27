import java.lang.reflect.Array;
import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        int array[] = new int[8];
        for(int i=0; i<8; i++)
            array[i] = -3*i;

        System.out.print("Original contents: ");
        display(array);
        Arrays.sort(array);
        System.out.print("Sorted: ");
        display(array);

        Arrays.fill(array, 2, 6, -1);
        System.out.print("After fill(): ");
        display(array);

        Arrays.sort(array);
        System.out.print("After sorting again: ");
        display(array);

        System.out.print("-3의 위치: ");
        int index = Arrays.binarySearch(array, -3);
        System.out.println(index);
    }

    static void display(int array[]) {
        for(int i : array)
            System.out.print(i + " ");

        System.out.println();
    }
}
