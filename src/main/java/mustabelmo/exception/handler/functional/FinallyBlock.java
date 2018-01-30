package mustabelmo.exception.handler.functional;

@FunctionalInterface
public interface FinallyBlock {
    void onFinalize();
}
