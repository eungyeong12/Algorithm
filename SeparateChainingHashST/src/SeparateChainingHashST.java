import java.util.ArrayList;

public class SeparateChainingHashST<K,V> {
    private int N; // 테이블에 입력된 원소의 수
    private int M; // 해시 테이블의 크기
    private SequentialSearchST<K,V>[] st; // 순차 연결 리스트의 배열로 테이블 구성

    public SeparateChainingHashST() { this(997); } // defualt: 소수
    public SeparateChainingHashST(int M) { // M을 입력받는 생성자
        this.M = M;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[M];
        for(int i=0; i<M; i++)
            st[i] = new SequentialSearchST<>();
    }

    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    // 해시 함수 : M으로 나머지 연산
    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }

    public V get(K key) { return st[hash(key)].get(key); } // 리스트를 검색

    public void put(K key, V value) { // 리스트에 추가
        if(!contains(key)) N++;
        st[hash(key)].put(key, value);
    }

    public void delete(K key) { // 리스트에서 삭제
        if(!contains(key)) return;
        st[hash(key)].delete(key); N--;
    }

    public Iterable<K> keys() { // 각 리스트의 원소들을 모두 포함
        ArrayList<K> keyList = new ArrayList<>(N);
        for(int i=0; i<M; i++)
            for(K key : st[i].keys())
                keyList.add(key);
        return keyList;
    }
}
