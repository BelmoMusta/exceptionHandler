package mustabelmo.exception.handler;

import mustabelmo.exception.handler.functional.CatchBlock;
import mustabelmo.exception.handler.functional.TryBlock;

import java.util.HashMap;

public class TryCatcher {

    private static final Class<?> DEFAULT = Throwable.class;

    private TryBlock tryBlock;
    private CatchBlock catchBlock;
    private HashMap<Class<?>, CatchBlock> map;

    public TryCatcher() {
        map = new HashMap<>();
        map.put(DEFAULT, throwable -> {

        });
    }

    public TryCatcher(TryBlock tryBlock) {
        this();
        this.tryBlock = tryBlock;
    }

    public TryCatcher(TryBlock tryBlock, CatchBlock catchBlock) {
        this(tryBlock);
        this.catchBlock = catchBlock;
    }


    public void execute() {
        try {
            tryBlock.perform();
        } catch (Throwable throwable) {

            if (catchBlock != null) {
                this.catchBlock.handle(throwable);
            } else {
                CatchBlock currentCatchBlock = map.get(throwable.getClass());
                if (currentCatchBlock == null) {
                    currentCatchBlock = map.get(DEFAULT);
                }
                currentCatchBlock.handle(throwable);
            }
        }
    }

    public <T> TryCatcher when(Class<T> tClass, CatchBlock catchBlockBlock) {
        map.put(tClass, catchBlockBlock);
        return this;
    }

    /**
     * in order to override the default catch block for the default exception
     * @param catchBlockBlock the catch block
     * @return the current instance of TryCatcher
     */
    public TryCatcher defaultCatch(CatchBlock catchBlockBlock) {
        map.put(DEFAULT, catchBlockBlock);
        return this;
    }
}


