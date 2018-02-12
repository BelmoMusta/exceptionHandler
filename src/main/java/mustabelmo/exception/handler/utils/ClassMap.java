package mustabelmo.exception.handler.utils;

import mustabelmo.exception.handler.functional.CatchBlock;

import java.util.*;

/**
 * A Map representing the Exception,
 * sorted by the independence of the java inheritance tree
 */
public class ClassMap extends TreeMap<Class<?>, CatchBlock> {
    public ClassMap() {
        super(new ClassComparator());
    }

    /**
     * gets the catchBlock assigned by the exception, if the exception
     * is not found it gets the nearest one in the inheritance tree,
     * as we go upward through the hierarchy.
     *
     * @param key the class to get the catch block of.
     * @return the catchBlock if found.
     */
    @Override
    public CatchBlock get(Object key) {
        CatchBlock catchBlock = null;

        if (!containsKey(key)) {
            Iterator<Class<?>> iterator = keySet().iterator();
            while (catchBlock == null && iterator.hasNext()) {
                Class<?> aClass = iterator.next();
                if (aClass.isAssignableFrom((Class<?>) key)) {
                    catchBlock = get(aClass);
                }
            }
        } else {
            catchBlock = super.get(key);
        }
        return catchBlock;
    }
}
