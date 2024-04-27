import java.util.Comparator;
import java.util.TreeSet;

class MyComp implements Comparator<String> {
    @Override
    public int compare(String aStr, String bStr) {
        return bStr.compareTo(aStr); // 내림차순
    }
}
public class ComparatorDemo1 {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>(new MyComp());

        ts.add("C"); ts.add("A"); ts.add("B"); ts.add("E"); ts.add("F"); ts.add("D");

        System.out.println(ts);
    }
}
