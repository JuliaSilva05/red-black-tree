class RBNode<T extends Comparable<T>> {
    private RBNode<T> left;
    private T info;
    private RBNode<T> right;
    private RBNode<T> father;
    private String color;

    private boolean status;

   

    /////////////////
    RBNode(T info){
        this.info = info;
    }

    public RBNode<T> getLeft() {
        return this.left;
    }
    public void setLeft(RBNode<T> left) {
        this.left = left;
    }
    public T getInfo() {
        return this.info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public RBNode<T> getRight() {
        return this.right;
    }
    public void setRight(RBNode<T> right) {
        this.right = right;
    }

    public RBNode<T> getFather() {
        return this.father;
    }
    public void setFather(RBNode<T> father) {
        this.father = father;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }

     public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
