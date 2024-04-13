public class AVL<K extends Comparable<K>, V> extends BST {
    private int height(Node<K,V> x) { return (x == null) ? 0 : x.getAux(); }
    private void setHeight(Node<K,V> x, int height) { x.setAux(height);}

    private void recomputeHeight(Node<K,V> x) { // 노드의 높이 재계산
        setHeight(x, 1 + Math.max(height(x.left), height(x.right)));
    }

    private boolean isBalanced(Node<K,V> x) { // 노드의 balanced 여부 검사
        return Math.abs(height(x.left)-height(x.right)) <= 1;
    }

    private Node<K,V> tallerChild(Node<K,V> x) { // 깊이가 깊은 자식 노드 조사
        if(height(x.left) > height(x.right)) return x.left;
        if(height(x.left) < height(x.right)) return x.right;
        if(x == root) return x.left; // 같은 경우에는 왼쪽 자식
        if(x == x.parent.left) return x.left; // LL이나
        else return x.right; // RR을 선호
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

    // rebalanceInsert()와 rebalanceDelete()에서 재구성을 위하여 호출하는 함수
    private void rebalance(Node<K,V> x) {
        do {
            if(!isBalanced(x)) { // x가 balanced node가 아닐 경우, 재구성
                x = restructure(tallerChild(tallerChild(x)));
                recomputeHeight(x.left); // 재구성 후, 높이를 다시 조정
                recomputeHeight(x.right);
                for(Node<K,V> p = x; p!= null; p=p.parent)
                    recomputeHeight(p);
            }
            // put()의 경우에는 재구성 후, 부모 노드를 조사할 필요가 없으나,
            // delete의 경우에는 부모 노드들의 balanced 여부를 조사하여야 함.
            x = x.parent;
        } while (x != null);
    }
}
