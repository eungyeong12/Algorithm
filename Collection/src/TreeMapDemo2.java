import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class TComp implements Comparator<String> {
    @Override
    public int compare(String aStr, String bStr) {
        int i,j,k;

        i = aStr.lastIndexOf(" ");
        j = bStr.lastIndexOf(" ");

        k = aStr.substring(i).compareToIgnoreCase(bStr.substring(j));
        return (k == 0) ? aStr.compareToIgnoreCase(bStr) : k;
    }
}
public class TreeMapDemo2 {
    public static void main(String[] args) {
        TreeMap<String, Double> tm = new TreeMap<>(new TComp());
        tm.put("John Doe", 3434.34);
        tm.put("Tom Smith", 123.22);
        tm.put("Hane Baker", 1378.00);
        tm.put("Tod Hall", 99.22);
        tm.put("Ralph Smith", -19.08);

        Set<Map.Entry<String, Double>> set = tm.entrySet();
        for(Map.Entry<String, Double> me : set)
            System.out.println(me.getKey() + ": " + me.getValue());

    }
}
