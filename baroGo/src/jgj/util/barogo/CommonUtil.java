package jgj.util.barogo;

import java.util.List;
import java.util.Map;

public class CommonUtil {
    public static boolean isEmpty(Object obj) {
        if (obj == null) { return true; }
        if ((obj instanceof String) && (StringUtil.isEmpty(obj))) { return true; } 
        if (obj instanceof Map) { return ((Map<?, ?>)obj).isEmpty(); }
        if (obj instanceof List) { return ((List<?>)obj).isEmpty(); }
        if (obj instanceof Object[]) { return (((Object[])obj).length == 0); } 

        return false;
    }
    
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
