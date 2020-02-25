package jgj.util.barogo;

public class StringUtil {

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }
    
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }
}
