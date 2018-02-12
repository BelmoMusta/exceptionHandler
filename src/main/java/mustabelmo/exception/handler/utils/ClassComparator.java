package mustabelmo.exception.handler.utils;

import java.util.Comparator;

public class ClassComparator implements Comparator<Class<?>> {
    @Override
    public int compare(Class<?> a, Class<?> b) {
        int retValue;
        if (a == b) {
            retValue = 0;
        } else if (a.isAssignableFrom(b)) {
            retValue = 1;
        } else {
            retValue = -1;
        }
        return retValue;
    }
}
