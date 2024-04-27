import java.util.ArrayList;

public class ArrayListToArray {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();

        al.add(1); al.add(2); al.add(3); al.add(4);

        System.out.println("Contents of al: " + al);

        Integer ia[] = new Integer[al.size()];
        ia = al.toArray(ia); // toArray() 함수를 이용하여 array list의 내용을 배열에 저장
        // al.size()가 3 -> 무시하고 크기가 4인 배열을 만듦

        int sum = 0;
        for(int i : ia)
            sum += i;

        System.out.println("Sum is: " + sum);
    }
}
