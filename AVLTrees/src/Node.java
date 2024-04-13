public class Node<K,V> {
    K key;
    V value;
    Node<K,V> left, right;

    int N; // 자손 노드 + 1 (ordered 연산)
    int aux; // AVL 트리나 RB 트리에 사용
    Node<K,V> parent; // AVL or RB
    public Node(K key, V val) {
        this.key = key;
        this.value = val;
        this.N = 1;
    }
    public int getAux() { return aux; }
    public void setAux(int value) { aux = value; }
}
