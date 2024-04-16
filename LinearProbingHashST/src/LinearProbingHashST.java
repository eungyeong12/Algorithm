public class LinearProbingHashST<K,V> {
    private int N;
    private int M; // 항상 M>N을 만족하여야 함.
    private K[] keys; // 키를 저장하는 배열
    private V[] vals; // 값을 저장하는 배열

    public LinearProbingHashST() { this(31); }
    public LinearProbingHashST(int M) {
        keys = (K[]) new Object[M];
        vals = (V[]) new Object[M];
        this.M = M;
     }

    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }

    public V get(K key) {
        for(int i=hash(key); keys[i] != null; i = (i+1) % M) // 테이블 순회
            if(keys[i].equals(key))
                return vals[i];
        return null;
    }

    public void put(K key, V value) {
        if(N >= M/2) resize(2 * M + 1); // 테이블 재구성
        int i;
        for(i = hash(key); keys[i] != null; i = (i+1) % M)
            if(keys[i].equals(key)) { // 기존에 존재하는 키
                vals[i] = value;
                return;
            }
        keys[i] = key; // 새로 (키,값)의 쌍을 추가
        vals[i] = value;
        N++;
    }

    private void resize(int cap) {
        LinearProbingHashST<K,V> t;
        t = new LinearProbingHashST<>(cap);
        for(int i=0; i<M; i++) // 새로운 테이블에 모든 원소 추가
            if(keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void delete(K key) {
        if(!contains(key)) return;

        int i= hash(key); // 테이블에서 key가 저장된 위치를 검색
        while(!key.equals(keys[i]))
            i = (i+1) % M;

        keys[i] = null; vals[i] = null; // 키와 값을 삭제

        i = (i+1) % M;
        while(keys[i] != null) {
            K keyToRehash = keys[i];
            V valToRehash = vals[i];
            keys[i] = null; vals[i] = null; N--;
            put(keyToRehash, valToRehash);
            i = (i+1) % M;
        }
        N--;
    }
}
