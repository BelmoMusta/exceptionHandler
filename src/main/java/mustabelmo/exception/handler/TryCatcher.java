package mustabelmo.exception.handler;

import mustabelmo.exception.handler.functional.CatchBlock;
import mustabelmo.exception.handler.functional.TryBlock;

import java.util.List;

public class TryCatcher {

    public TryCatcher() {
    }

    public void throwIfnoHandler(Throwable throwable) throws Throwable {
        throw throwable;
    }

    public TryCatcher(TryBlock tryBlock, CatchBlock catchBlock) {
        try {
            tryBlock.perform();

        } catch (Throwable throwable) {
            if(throwable!=null)
            catchBlock.handle(throwable);
        }
    }

    private void catchBlock(Throwable throwable, CatchBlock catchBlock) {
        if (throwable != null)
            catchBlock.handle(throwable);
    }

    public void multipleCatchBlocks(List<Pair> handlers) {
        for (Pair pair : handlers) {
            pair.getHandler().handle(pair.getThrowable());
        }
    }

    public void tryBlock(TryBlock tryBlock, CatchBlock catchBlock) {
        try {
            tryBlock.perform();
        } catch (Throwable throwable) {
            catchBlock(throwable, catchBlock);
        }
    }

    public void tryBlockWithMultiCatches(TryBlock tryBlock, List<Pair> handlers) {
        try {
            tryBlock.perform();
        } catch (Throwable throwable) {
            multipleCatchBlocks(handlers);
        }
    }
}


