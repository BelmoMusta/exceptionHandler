package mustabelmo.exception.handler;

public class Pair<T, H> {

    T throwable;
    H handler;

    public Pair(T throwable, H handler) {
        this.throwable = throwable;
        this.handler = handler;
    }
}
