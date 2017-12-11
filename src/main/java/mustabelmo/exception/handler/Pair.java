package mustabelmo.exception.handler;

import mustabelmo.exception.handler.functional.CatchBlock;

public class Pair {

    Throwable throwable;
    CatchBlock handler;

    public Pair(Throwable throwable, CatchBlock handler) {
        this.throwable = throwable;
        this.handler = handler;
    }

    public CatchBlock getHandler() {
        return handler;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
