package todorov.tp1;

public class Triplet<T,U> {
    private final T key1;
    private final T key2;
    private final U value;

    public Triplet(T _key1, T _key2 , U _value) {
        this.key1 = _key1;
        this.key2 = _key2;
        this.value = _value;
    }

    public T getKey1() {
        return this.key1;
    }
    public T getKey2() {
        return this.key2;
    }

    public U getValue() {
        return this.value;
    }
}

