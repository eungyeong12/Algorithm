import java.util.LinkedList;
import java.util.Queue;

public class TrieST<Value> {
    private static int R = 256;
    private Node root;
    private int N; // number of keys in trie. size() 함수에서 사용

    private static class Node { // 노드에 문자는 저장되지 않는다.
        private Object val; // val이 null이 아니면, 그곳까지의 key가 존재
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if(x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if(x == null) return null;
        if(d == key.length()) return x;
        char c = key.charAt(d); // d-번째 문자를 이용하여 다음으로 이동
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val) {
        if(val == null) delete(key);
        else root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if(x == null) x = new Node();
        if(d == key.length()) {
            if(x.val == null) N++; // 새로운 key-value 쌍 추가
            x.val = val; // else일 경우, 단순 값 변경
            return x;
        }
        char c = key.charAt(d); // d-번째 문자를 이용하여 다음으로 이동
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix(""); // 모든 키 = 접두사가 ""
    }

    // pre를 접두사로 갖는 모든 키들을 반환
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<>();
        Node x = get(root, pre, 0); // pre까지의 노드 검색(private 함수 호출)
        collect(x, new StringBuilder(pre), q);
        return q;
    }

    private void collect(Node x, StringBuilder pre, Queue<String> q) {
        if(x == null) return;
        if(x.val != null) q.add(pre.toString());
        for(char c = 0; c < R; c++) { // pre 다음에 오는 모든 문자에 대해 검색
            if(x.next[c] == null) continue;
            pre.append(c); // pre에 c를 붙인 다음,
            collect(x.next[c], pre, q); // pre로 시작되는 모든 키 검색
            pre.deleteCharAt(pre.length()-1); // pre에서 c를 삭제
        }
    }

    // s의 접두사로 가장 긴 키를 반환
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if(x == null) return length;
        if(x.val != null) length = d; // 현재까지 가장 긴 키. 길이를 저장
        if(d == s.length()) return length; // s = longest prefix
        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if(x == null) return null;
        if(d == key.length()) { // 삭제할 노드까지 도착
            if(x.val != null) N--; // key-value 쌍의 수 감소
            x.val = null; // null value로 설정
        }
        else { // 아니면 아래로 키를 찾아 계속 내려가자.
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // 노드를 삭제할 지를 검사
        if(x.val != null) return x; // value가 null이 아니면 삭제 불가
        for(char c = 0; c < R; c++)
            if(x.next[c] != null) // null이 아닌 링크가 존재하면 삭제 불가
                return x;
        return null; // value와 링크가 모두 null이면 삭제
    }

}
