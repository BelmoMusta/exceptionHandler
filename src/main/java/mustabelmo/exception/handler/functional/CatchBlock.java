package mustabelmo.exception.handler.functional;

@FunctionalInterface
public interface CatchBlock {
    void handle(Throwable throwable);
}
