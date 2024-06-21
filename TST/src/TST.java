public class TST<Value> {
    private Node<Value> root;
    private int N; // number of keys in TST

    private class Node<Value> {
        char c; // 문자
        Value val; // 값
        Node<Value> left, mid, right; // 링크
    }

    public int size() {
        return N;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        Node<Value> x = get(root, key, 0); // d = 0 -> 첫번째 문자부터 시작
        if(x == null) return null;
        return x.val;
    }

    private Node<Value> get(Node<Value> x, String key, int d) {
        if(x == null) return null;
        char c = key.charAt(d);
        if(c < x.c) return get(x.left, key, d);
        else if(c > x.c) return get(x.right, key, d);
        else if(d < key.length()-1) return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val) {
        if(!contains(key)) N++;
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if(x == null) { // 노드 생성
            x = new Node<>();
            x.c = c;
        }
        if(c < x.c) x.left = put(x.left, key, val, d);
        else if(c > x.c) x.right = put(x.right, key, val, d);
        else if(d < key.length()-1)
            x.mid = put(x.mid, key, val, d+1);
        else x.val = val;
        return x;
    }
}
