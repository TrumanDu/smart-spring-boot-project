package top.trumandu.util;

import top.trumandu.common.domain.TreeDTO;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Truman.P.Du
 * @date 2022/04/14
 * @description 生成树工具类
 */
public class TreeGeneratorUtil {


    public static <T> List<TreeDTO> convertToTreeDTO(List<T> list, String labelMethodName, String valueMethodName) throws Exception {
        List<TreeDTO> roots = new ArrayList<>();
        Map<Long, List<TreeDTO>> tree = new HashMap<>(16);
        for (int i = 0; i < list.size(); i++) {
            T entity = list.get(i);
            Method labelMethod = entity.getClass().getMethod(labelMethodName);
            Method valueMethod = entity.getClass().getMethod(valueMethodName);
            Method parentIdMethod = entity.getClass().getMethod("getParentId");
            Object value = valueMethod.invoke(entity);
            Object label = labelMethod.invoke(entity);
            TreeDTO vo = new TreeDTO((Long) value, (String) label);
            Long parentId = (Long) parentIdMethod.invoke(entity);
            if (parentId == null) {
                roots.add(vo);
            } else {
                List<TreeDTO> tempList = null;
                if (tree.containsKey(parentId)) {
                    tempList = tree.get(parentId);
                } else {
                    tempList = new ArrayList<>();
                }
                tempList.add(vo);
                tree.put(parentId, tempList);
            }
        }
        //递归设置所有的孩子节点
        for (TreeDTO selectDTO : roots) {
            setChildByParentNode(tree, selectDTO);
        }
        return roots;
    }

    /**
     * 将列表转换成指定类的树结构
     *
     * @param list
     * @param target
     * @param <T>
     * @param <E>
     * @return
     * @throws Exception
     */
    public static <T, E> List<T> convertToTreeBeanDTO(List<E> list, Class<T> target) throws Exception {
        List<T> roots = new ArrayList<>();
        Map<Long, List<T>> tree = new HashMap<>(16);
        for (int i = 0; i < list.size(); i++) {
            E entity = list.get(i);
            T vo = BeanUtil.copy(entity, target);
            Method parentIdMethod = entity.getClass().getMethod("getParentId");
            Long parentId = (Long) parentIdMethod.invoke(entity);
            if (parentId == null) {
                roots.add(vo);
            } else {
                List<T> tempList = null;
                if (tree.containsKey(parentId)) {
                    tempList = tree.get(parentId);
                } else {
                    tempList = new ArrayList<>();
                }
                tempList.add(vo);
                tree.put(parentId, tempList);
            }
        }
        //递归设置所有的孩子节点
        for (T dto : roots) {
            setChildByParentNode(tree, dto);
        }
        return roots;
    }

    private static void setChildByParentNode(Map<Long, List<TreeDTO>> tree, TreeDTO vo) {
        Long id = vo.getValue();
        List<TreeDTO> child = null;
        if (tree.containsKey(id)) {
            child = tree.get(id);
        }
        if (child != null) {
            for (TreeDTO sysOrgVO : child) {
                setChildByParentNode(tree, sysOrgVO);
            }
        }
        vo.setChildren(child);
    }


    private static <T> void setChildByParentNode(Map<Long, List<T>> tree, T vo) throws Exception {
        Method idMethod = vo.getClass().getMethod("getId");
        Long id = (Long) idMethod.invoke(vo);
        List<T> child = null;
        if (tree.containsKey(id)) {
            child = tree.get(id);
        }
        if (child != null) {
            for (T sysOrgVO : child) {
                setChildByParentNode(tree, sysOrgVO);
            }
        }
        Method setChildrenMethod = vo.getClass().getMethod("setChildren", List.class);
        setChildrenMethod.invoke(vo, child);
    }
}
