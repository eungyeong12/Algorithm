import java.util.Comparator;
import java.util.TreeSet;

class MyComp2 implements Comparator<String> {
    @Override
    public int compare(String aStr, String bStr) {
        return aStr.compareTo(bStr); // 오름차순
    }
}
public class ComparatorDemo2 {
    public static void main(String[] args) {
        MyComp2 mc = new MyComp2();
        TreeSet<String> ts = new TreeSet<>(mc.reversed());

        ts.add("C"); ts.add("A"); ts.add("B"); ts.add("E"); ts.add("F"); ts.add("D");

        System.out.println(ts);
    }
}
