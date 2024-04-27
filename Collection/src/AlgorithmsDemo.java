import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class AlgorithmsDemo {
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("C"); ll.add("A"); ll.add("E"); ll.add("D"); ll.add("A");

        Comparator<String> r = Collections.reverseOrder();
        Collections.sort(ll, r);
        System.out.println("List sorted in reverse: " + ll);

        Collections.shuffle(ll);
        System.out.println("List shuffled: " + ll);

        System.out.println("Minimum: " + Collections.min(ll));
        System.out.println("Maximum: " + Collections.max(ll));
        System.out.println("A의 빈도수: " + Collections.frequency(ll, "A"));
    }
}
