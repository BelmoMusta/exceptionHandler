package mustabelmo.exception.handler.test;

import junit.framework.TestCase;

import mustabelmo.exception.handler.TryCatcher;
import mustabelmo.exception.handler.functional.CatchBlock;
import mustabelmo.exception.handler.functional.TryBlock;
import org.junit.Test;

public class TryCatchTest extends TestCase {


    public static final String MESSAGE = "here is your exception";

    @Test
    public void testUniqueTryCatchBlock() {
        final boolean[] y = {false};
        TryCatcher tryCatcher = new TryCatcher();
        tryCatcher.tryBlock(() -> {
            throw new Exception(MESSAGE);
        },
            throwable -> {
            throwable.printStackTrace();
            y[0] = throwable.getMessage().equals(MESSAGE) ;
        });
        assertEquals(y[0], true);
    }


    @Test
    public void testUniqueTryCatchBlockWithConstructor() {
        final boolean[] check = {false};
        TryBlock tryBlock = () -> {
            throw new Exception(MESSAGE);
        };
        CatchBlock catchBlockBlock =  throwable -> {
            throwable.printStackTrace();
            check[0] = throwable.getMessage().equals(MESSAGE) ;
        };

        TryCatcher tryCatcher = new TryCatcher(tryBlock,catchBlockBlock);
        assertEquals(check[0], true);
    }
}
