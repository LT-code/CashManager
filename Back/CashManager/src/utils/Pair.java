package utils;

public class Pair<T1,T2> {

    private T1 left;
    private T2 right;

    public Pair(T1 left, T2 right) {
        this.left = left;
        this.right = right;
    }

    public void setLeft(T1 left) {
        this.left = left;
    }

    public void setRight(T2 right) {
        this.right = right;
    }

    public T1 getLeft() {
        return left;
    }

    public T2 getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
	        @SuppressWarnings("unchecked")
			Pair<T1, T2> pairo = (Pair<T1, T2>) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }
}

