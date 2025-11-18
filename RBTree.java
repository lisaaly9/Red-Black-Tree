public class RBTree {
    private final RBNode NIL = new RBNode(); 
    private RBNode root = NIL;

    public RBTree() {
        root = NIL;
    }

    /* ----------------- INSERT ----------------- */
    public boolean insert(int key) {
        RBNode z = new RBNode(key);
        z.left = z.right = z.parent = NIL;

        RBNode y = NIL;
        RBNode x = root;
        while (x != NIL) {
            y = x;
            if (z.key == x.key) {
                return false; 
            } else if (z.key < x.key) x = x.left;
            else x = x.right;
        }
        z.parent = y;
        if (y == NIL) root = z;
        else if (z.key < y.key) y.left = z;
        else y.right = z;

        z.left = NIL;
        z.right = NIL;
        z.red = true;

        insertFixup(z);
        return true;
    }

    private void insertFixup(RBNode z) {
        while (z.parent.red) {

            RBNode parent = z.parent;
            RBNode grandparent = z.parent.parent;

            if (parent == grandparent.left) {
                RBNode uncle = grandparent.right; // uncle
                if (uncle.red) {
                    // case 1 : parent & uncle merah
                    parent.red = false;
                    uncle.red = false;
                    grandparent.red = true;
                    z = grandparent;
                } else {
                    if (z == parent.right) {
                        // case 2 : uncle hitam & z adalah anak kanan / zigzag
                        z = parent;
                        leftRotate(z);
                    }
                    // case 3 : uncle hitam & node adalah anak kiri / lurus
                    parent.red = false;
                    grandparent.red = true;
                    rightRotate(grandparent);
                }
            } else {
                // mirror atau sebaliknya
                RBNode uncle = grandparent.left;
                if (uncle.red) {
                    parent.red = false;
                    uncle.red = false;
                    grandparent.red = true;
                    z = grandparent;
                } else {
                    if (z == parent.left) {
                        z = parent;
                        rightRotate(z);
                    }
                    parent.red = false;
                    grandparent.red = true;
                    leftRotate(grandparent);
                }
            }
            if (z == root) break;
        }
        root.red = false;
    }
    /* ----------------- ROTATIONS ----------------- */
    //  Rotasi kiri
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }
    //  Rotasi kanan    
    private void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == NIL) root = x;
        else if (y == y.parent.left) y.parent.left = x;
        else y.parent.right = x;
        x.right = y;
        y.parent = x;
    }

    /* ----------------- SEARCH ----------------- */
    public boolean contains(int key) {
        return searchNode(root, key) != NIL;
    }

    private RBNode searchNode(RBNode node, int key) {
        while (node != NIL && key != node.key) {
            node = (key < node.key) ? node.left : node.right;
        }
        return node;
    }

    /* ----------------- CLEAR TREE ----------------- */
    public void clear() {
        root = NIL;
    }

    /* ----------------- TRAVERSAL ----------------- */
    // Root - Left - Right
    public void preOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(RBNode node) {
        if (node == NIL) return;
        System.out.print(node.key + "(" + node.colorString() + ") ");
        preOrderRec(node.left);
        preOrderRec(node.right);
    }

    // Left - Root - Right
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(RBNode node) {
        if (node == NIL) return;
        inOrderRec(node.left);
        System.out.print(node.key + "(" + node.colorString() + ") ");
        inOrderRec(node.right);
    }

    // Left - Right - Root
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(RBNode node) {
        if (node == NIL) return;
        postOrderRec(node.left);
        postOrderRec(node.right);
        System.out.print(node.key + "(" + node.colorString() + ") ");
    }

    /* ----------------- PRINT TREE ----------------- */
    public void printTree() {
        printRBTree(root, 0);
    }

    private void printRBTree(RBNode node, int depth) {
        if (node == NIL) return;

        printRBTree(node.right, depth + 1);

    for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println(node.key + "(" + node.colorString() + ")");

        printRBTree(node.left, depth + 1);
    }
}
