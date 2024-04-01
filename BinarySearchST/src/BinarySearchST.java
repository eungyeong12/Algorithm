import java.util.ArrayList;

/* 정렬된 배열을 이용한 이진 검색 */
public class BinarySearchST<K extends Comparable<K>, V> {
    private static final int INIT_CAPACITY = 10;
    private K[] keys; // 키의 배열
    private V[] vals; // 값의 배열
    private int N;

    public BinarySearchST() {
        keys = (K[]) new Comparable[INIT_CAPACITY];
        vals = (V[]) new Object[INIT_CAPACITY];
    }

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    public boolean contains(K key) { return get(key) != null; }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    private void resize(int capacity) { // 배열 크기를 동적으로 변경
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Object[capacity];
        for(int i=0; i<N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    private int search(K key) { // 이진 검색
        int lo = 0;
        int hi = N-1;
        while(lo <= hi) {
            int mid = (hi + lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) hi = mid - 1;
            else if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo; // 키가 없을 경우, -1이 아니라 lo가 반환
    }

    public V get(K key) {
        if(isEmpty()) return null;
        int i = search(key); // 이진 검색
        if(i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null; // 키가 없으면 null을 반환
    }

    public void put(K key, V value) {
        int i = search(key); // 일단 키를 찾고
        if(i < N && keys[i].compareTo(key) == 0) { // 있으면, 값만 변경
            vals[i] = value;
            return;
        }
        if(N == keys.length) // 없으면, 추가해야 하니 배열 크기 확장
            resize(2 * keys.length);

        for(int j=N; j>i; j++) { // 추가될 곳의 공간 확보
            keys[j] = keys[j-1]; // 뒤에서부터 당기기
            vals[j] = vals[j-1];
        }

        keys[i] = key;
        vals[i] = value;
        N++;
    }

    public void delete(K key) {
        if(isEmpty()) return;
        int i = search(key);
        if(i == N && keys[i].compareTo(key) != 0) return; // 키가 없는 경우, 그냥 반환

        for(int j=i; j<N-1; j++) { // 뒤에 있는 키들을 한칸 앞으로.
            keys[j] = keys[j+1]; // 앞에서부터 당기기
            vals[j] = vals[j+1];
        }
        N--;
        keys[N] = null; vals[N] = null;
        if(N > INIT_CAPACITY && N == keys.length/4) // 배열이 너무 크면, 다시 줄이자.
            resize(keys.length/2);
    }

    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<>(N);
        for(int i=0; i<N; i++)
            keyList.add(keys[i]);
        return keyList;
    }
}
