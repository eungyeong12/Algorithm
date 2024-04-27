import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(30);
        pq.add(80);
        pq.add(20);

        System.out.println(pq);

        System.out.println("원소 삭제");
        while(!pq.isEmpty())
            System.out.println(pq.remove());
    }
}
