package top.trumandu.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Truman.P.Du
 * @date 2023/07/30
 * @description 替代hutool的工具类
 */
public class SmartUtil {

    /**
     * 格式化字符串，使用{}作为占位符
     *
     * @param template 模板字符串
     * @param params   参数数组
     * @return 格式化后的字符串
     */
    public static String format(String template, Object... params) {
        if (template == null) {
            return null;
        }
        if (params == null || params.length == 0) {
            return template;
        }

        StringBuilder result = new StringBuilder(template.length() + params.length * 10);
        int paramIndex = 0;
        int templateLength = template.length();

        for (int i = 0; i < templateLength; i++) {
            char c = template.charAt(i);
            if (c == '{' && i + 1 < templateLength && template.charAt(i + 1) == '}') {
                if (paramIndex < params.length) {
                    Object param = params[paramIndex];
                    result.append(param != null ? param.toString() : "null");
                    paramIndex++;
                }
                i++; // 跳过下一个字符 '}'
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * 判断字符串是否为空
     *
     * @param text 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }

    /**
     * 判断字符串是否为空白（null、空串或只包含空白符）
     *
     * @param text 字符串
     * @return 是否为空白
     */
    public static boolean isBlank(CharSequence text) {
        if (isEmpty(text)) {
            return true;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isWhitespace(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否包含子串
     *
     * @param textToSearch 被搜索的字符串
     * @param substring    子串
     * @return 是否包含
     */
    public static boolean contains(CharSequence textToSearch, CharSequence substring) {
        if (textToSearch == null || substring == null) {
            return false;
        }
        // 如果都是String类型，直接使用contains方法
        if (textToSearch instanceof String && substring instanceof String) {
            return ((String) textToSearch).contains((String) substring);
        }
        return textToSearch.toString().contains(substring.toString());
    }

    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否包含null元素
     *
     * @param array 数组
     * @return 是否包含null元素
     */
    public static boolean hasNull(Object[] array) {
        if (array == null) {
            return false;
        }
        for (Object obj : array) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断Iterable是否为空
     * 优化：避免重复的null检查
     *
     * @param iterable Iterable对象
     * @return 是否为空
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        return iterable == null || isEmpty(iterable.iterator());
    }

    /**
     * 判断Iterator是否为空
     *
     * @param iterator Iterator对象
     * @return 是否为空
     */
    public static boolean isEmpty(Iterator<?> iterator) {
        return iterator == null || !iterator.hasNext();
    }

    /**
     * 判断Map是否为空
     *
     * @param map Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断两个对象是否相等
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 是否相等
     */
    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    /**
     * 判断两个对象是否不相等
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 是否不相等
     */
    public static boolean notEqual(Object obj1, Object obj2) {
        return !equals(obj1, obj2);
    }
}