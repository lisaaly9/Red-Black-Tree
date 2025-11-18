public class RBNode {
    public int key;
    public RBNode left;
    public RBNode right;
    public RBNode parent;
    public boolean red; // true = red, false = black

    public RBNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.red = true; // node baru berwarna merah
    }

    // constructor for NIL node
    public RBNode() {
        this.key = 0;
        this.left = this.right = this.parent = this;
        this.red = false; // NIL berwarna hitam
    }

    public String colorString() {
        if (red) {
            return "\u001B[31m" + "R" + "\u001B[0m"; // merah
        } else {
            return "\u001B[30m" + "B" + "\u001B[0m"; // hitam
        }
    }

}
