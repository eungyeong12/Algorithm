import java.util.ArrayList;

/* 연결 리스트를 이용한 순차 검색 */
public class SequentialSearchST<K,V> {
    private Node<K,V> first; // 첫 번째 노드에 대한 참조를 유지. 초기값 = null
    int N; // 연결 리스트의 노드 수를 유지. 초기값 = 0;

    public V get(K key) {
        for(Node<K,V> x = first; x != null; x = x.next) // 연결 리스트를 스캔
            if(key.equals(x.key))
                return x.value;
        return null;
    }

    public void put(K key, V value) {
        for(Node<K,V> x = first; x != null; x = x.next)
            if(key.equals(x.key)) {
                x.value = value;
                return;
            }
        first = new Node<K,V>(key, value, first); // next가 first를 가리키도록 함
        N++;
    }

    public void delete(K key) {
        if(key.equals(first.key)) { // 첫번째 노드를 삭제하는 경우
            first = first.next;
            N--;
            return;
        }
        for(Node<K,V> x = first; x.next != null; x = x.next) { // 삭제할 노드를 검색
            if(key.equals(x.next.key)) {
                x.next = x.next.next;
                N--;
                return;
            }
        }
    }

    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<>(N); // ArrayList는 Iterable을 구현
        for(Node<K,V> x = first; x != null; x = x.next)
            keyList.add(x.key);
        return keyList;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
}
