package mustabelmo.exception.handler;

import mustabelmo.exception.handler.functional.CatchBlock;
import mustabelmo.exception.handler.functional.TryBlock;

import java.util.HashMap;

public class TryCatcher {
    /**
     * the default exception
     */
    private static final Class<?> DEFAULT = Throwable.class;
    /**
     * the try block
     */
    private TryBlock tryBlock;

    /**
     * the catchBlockMap of the exceptions and their correspondent catch blocks
     */
    private HashMap<Class<?>, CatchBlock> catchBlockMap;

    /**
     * Constructor of the TryCatcher class
     * init the catchBlockMap holding the exceptions types and their correspondent catch blocks
     */
    public TryCatcher() {
        catchBlockMap = new HashMap<>();
        catchBlockMap.put(DEFAULT, throwable -> {
        });
    }

    /**
     * Constructor of the TryCatcher class
     *
     * @param tryBlock the try block
     */
    public TryCatcher(TryBlock tryBlock) {
        this();
        this.tryBlock = tryBlock;
    }

    /**
     * Constructor of the TryCatcher class
     *
     * @param tryBlock   the try block
     * @param catchBlock the catch block
     */
    public TryCatcher(TryBlock tryBlock, CatchBlock catchBlock) {
        this(tryBlock);
        defaultCatch(catchBlock);
    }

    /**
     * Execute the try block and the associated catch blocks
     */
    public void execute() {
        try {
            tryBlock.perform();

        } catch (Throwable throwable) {
            CatchBlock currentCatchBlock = catchBlockMap.get(throwable.getClass());
            if (currentCatchBlock == null) {
                currentCatchBlock = catchBlockMap.get(DEFAULT);
            }
            currentCatchBlock.handle(throwable);
        }
    }

    /**
     * Assign the specific catch block whenever this kind of exception is caught
     *
     * @param exceptionClass  the exception class
     * @param catchBlockBlock the correspondant catch block
     * @param <T>             type of the exception
     * @return the current instance
     */
    public <T> TryCatcher when(Class<T> exceptionClass, CatchBlock catchBlockBlock) {
        catchBlockMap.put(exceptionClass, catchBlockBlock);
        return this;
    }

    /**
     * in order to override the default catch block for the default exception
     *
     * @param catchBlockBlock the catch block
     * @return the current instance of TryCatcher
     */
    public TryCatcher defaultCatch(CatchBlock catchBlockBlock) {
        catchBlockMap.put(DEFAULT, catchBlockBlock);
        return this;
    }
}


