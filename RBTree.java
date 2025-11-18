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
            if (z.parent == z.parent.parent.left) {
                RBNode y = z.parent.parent.right; // uncle
                if (y.red) {
                    // case 1 : uncle merah
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        // case 2 : uncle hitam & z adalah anak kanan / zigzag
                        z = z.parent;
                        leftRotate(z);
                    }
                    // case 3 : uncle hitam & node adalah anak kiri / lurus
                    z.parent.red = false;
                    z.parent.parent.red = true;
                    rightRotate(z.parent.parent);
                }
            } else {
                // mirror / sebaliknya
                RBNode y = z.parent.parent.left;
                if (y.red) {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.red = false;
                    z.parent.parent.red = true;
                    leftRotate(z.parent.parent);
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

    // /* ----------------- DELETE ----------------- */
    // public boolean delete(int key) {
    //     RBNode z = searchNode(root, key);
    //     if (z == NIL) return false;

    //     RBNode y = z;
    //     boolean yOriginalColor = y.red;
    //     RBNode x;

    //     if (z.left == NIL) {
    //         x = z.right;
    //         transplant(z, z.right);
    //     } else if (z.right == NIL) {
    //         x = z.left;
    //         transplant(z, z.left);
    //     } else {
    //         y = minimum(z.right);
    //         yOriginalColor = y.red;
    //         x = y.right;
    //         if (y.parent == z) {
    //             x.parent = y;
    //         } else {
    //             transplant(y, y.right);
    //             y.right = z.right;
    //             y.right.parent = y;
    //         }
    //         transplant(z, y);
    //         y.left = z.left;
    //         y.left.parent = y;
    //         y.red = z.red;
    //     }

    //     if (!yOriginalColor) {
    //         deleteFixup(x);
    //     }
    //     return true;
    // }

    // private void transplant(RBNode u, RBNode v) {
    //     if (u.parent == NIL) root = v;
    //     else if (u == u.parent.left) u.parent.left = v;
    //     else u.parent.right = v;
    //     v.parent = u.parent;
    // }

    // private RBNode minimum(RBNode node) {
    //     while (node.left != NIL) node = node.left;
    //     return node;
    // }

    // private void deleteFixup(RBNode x) {
    //     while (x != root && !x.red) {
    //         if (x == x.parent.left) {
    //             RBNode w = x.parent.right;
    //             if (w.red) {
    //                 // case 1
    //                 w.red = false;
    //                 x.parent.red = true;
    //                 leftRotate(x.parent);
    //                 w = x.parent.right;
    //             }
    //             if (!w.left.red && !w.right.red) {
    //                 // case 2
    //                 w.red = true;
    //                 x = x.parent;
    //             } else {
    //                 if (!w.right.red) {
    //                     // case 3
    //                     w.left.red = false;
    //                     w.red = true;
    //                     rightRotate(w);
    //                     w = x.parent.right;
    //                 }
    //                 // case 4
    //                 w.red = x.parent.red;
    //                 x.parent.red = false;
    //                 w.right.red = false;
    //                 leftRotate(x.parent);
    //                 x = root;
    //             }
    //         } else {
    //             // mirror
    //             RBNode w = x.parent.left;
    //             if (w.red) {
    //                 w.red = false;
    //                 x.parent.red = true;
    //                 rightRotate(x.parent);
    //                 w = x.parent.left;
    //             }
    //             if (!w.right.red && !w.left.red) {
    //                 w.red = true;
    //                 x = x.parent;
    //             } else {
    //                 if (!w.left.red) {
    //                     w.right.red = false;
    //                     w.red = true;
    //                     leftRotate(w);
    //                     w = x.parent.left;
    //                 }
    //                 w.red = x.parent.red;
    //                 x.parent.red = false;
    //                 w.left.red = false;
    //                 rightRotate(x.parent);
    //                 x = root;
    //             }
    //         }
    //     }
    //     x.red = false;
    // }

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
