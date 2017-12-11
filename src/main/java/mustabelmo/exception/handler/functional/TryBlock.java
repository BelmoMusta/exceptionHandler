package mustabelmo.exception.handler.functional;

@FunctionalInterface
public interface TryBlock {
    void perform() throws Throwable;
}
