public class No {
    private String caracter;
    private boolean isLeaf;
    private No left;
    private No right;
    private int bitPos;

    public No(String caracter, boolean isLeaf) {
        this.caracter = caracter;
        this.isLeaf = isLeaf;
        this.left = null;
        this.right = null;
        this.bitPos = -1;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public No getLeft() {
        return left;
    }

    public void setLeft(No left) {
        this.left = left;
    }

    public No getRight() {
        return right;
    }

    public void setRight(No right) {
        this.right = right;
    }

    public int getBitPos() {
        return bitPos;
    }

    public void setBitPos(int bitPos) {
        this.bitPos = bitPos;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}