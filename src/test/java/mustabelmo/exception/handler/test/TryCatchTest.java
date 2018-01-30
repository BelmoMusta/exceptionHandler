package mustabelmo.exception.handler.test;

import junit.framework.TestCase;
import mustabelmo.exception.handler.TryCatcher;
import mustabelmo.exception.handler.functional.CatchBlock;
import mustabelmo.exception.handler.functional.TryBlock;

public class TryCatchTest extends TestCase {


     private static final String MESSAGE = "here is your exception";

    public void testUniqueTryCatchBlockWithConstructor() {
        final boolean[] check = {false};
        TryBlock tryBlock = () -> {
            throw new Exception(MESSAGE);
        };
        CatchBlock catchBlockBlock = throwable -> {
            throwable.printStackTrace();
            check[0] = true;
        };

        TryCatcher tryCatcher = new TryCatcher(tryBlock, catchBlockBlock);
        tryCatcher.execute();
        assertTrue(check[0]);
    }


    public void testWithoutCatchBlock() {
        final boolean[] check = {true};

        TryBlock tryBlock = () -> {
            //  don't throw any exception from here
        };
        CatchBlock catchBlockBlock = throwable -> {
            check[0] = false;
            throwable.printStackTrace();
        };
        TryCatcher tryCatcher = new TryCatcher(tryBlock, catchBlockBlock);
        tryCatcher.execute();
        assertTrue(check[0]);
    }

    public void testMultipleExceptions() {
        final boolean[] check = {true};

        TryBlock tryBlock = () -> {
            if (check[0]) throw new IllegalArgumentException("ILLEGAL ARGUMENT");
            else throw new NullPointerException("A DUMMY NULL POINTER EXCEPTION ");
        };

        CatchBlock illegalArgBlock = throwable -> {
            check[0] = false;
            throwable.printStackTrace();
        };

        CatchBlock nullPointerBlock = throwable -> {
            check[0] = false;
            throwable.printStackTrace();
        };
        TryCatcher tryCatcher = new TryCatcher(tryBlock);

        tryCatcher
                .when(NullPointerException.class, nullPointerBlock)
                .when(IllegalArgumentException.class, illegalArgBlock)
                .execute();
        assertFalse(check[0]);
    }
}
