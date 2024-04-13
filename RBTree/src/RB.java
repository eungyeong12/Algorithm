public class RB<K extends Comparable<K>, V> extends BST {
    boolean isBlack(Node<K,V> x) { return (x == null) || (x.getAux() == 0); }
    boolean isRed(Node<K,V> x) { return (x != null) && (x.getAux() == 1); }
    void makeBlack(Node<K,V> x) { x.setAux(0);}
    void makeRed(Node<K,V> x) { x.setAux(1); }

    Node<K,V> sibling(Node<K,V> x) {
        Node<K,V> p = x.parent;
        if(p.left == x) return p.right;
        else return p.left;
    }

    protected void rotate(Node<K,V> x) { // LL과 RR을 하나의 함수로. c->x->y가 문제
        Node<K,V> y = x.parent;
        Node<K,V> z = y.parent;
        if(z == null) { root = x; x.parent = null; }
        else relink(z,x,y == z.left); // x를 z의 자식으로. 즉 c가 z와 y의 부모

        if(x == y.left) { relink(y, x.right, true); relink(x, y, false);} // LL
        else { relink(y, x.left, false); relink(x, y, true); } // RR
    }

    protected Node<K,V> restructure(Node<K,V> x) { // x->y->z가 문제
        Node<K,V> y = x.parent;
        Node<K,V> z = y.parent;
        if((x == y.left) == (y == z.left)) { rotate(y); return y; } // LL or RR : y가 중간값
        else { rotate(x); rotate(x); return x; } // 중간값이 x : LR/RL -> LL/RR로 일단 변경
    }

    private void resolveRed(Node<K,V> x) {
        Node<K,V> parent, uncle, middle, grand;
        parent = x.parent;
        if(isRed(parent)) { // x가 root가 아니므로, red -> double-red problem
            uncle = sibling(parent);
            if(uncle == null || isBlack(uncle)) { // XXb -> 4-노드를 재구성
                middle = restructure(x);
                makeBlack(middle);
                makeRed(middle.left);
                makeRed(middle.right);
            } else { // XXr -> overflow
                makeBlack(parent);
                makeBlack(uncle);
                grand = parent.parent;
                if(grand != root) { makeRed(grand); resolveRed(grand); }
            }
        }
    }
}
