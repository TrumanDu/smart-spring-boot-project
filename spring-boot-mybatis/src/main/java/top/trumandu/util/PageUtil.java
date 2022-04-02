package top.trumandu.util;

import com.github.pagehelper.Page;
import top.trumandu.common.domain.PageResultDTO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/02
 * @description
 */
public class PageUtil {

    /**
     * 转换结果为分页结果集
     * @param page
     * @param <T>
     * @return
     */
    public static <T> PageResultDTO<T> convert2PageResult(Page<T> page) {
        PageResultDTO pageResultDTO = setPage(page);
        pageResultDTO.setList(page.getResult());
        return pageResultDTO;
    }

    /**
     * 转换结果为分页结果集
     * @param page
     * @param targetClazz
     * @param <T>
     * @return
     */
    public static <T> PageResultDTO<T> convert2PageResult(Page<T> page, Class<T> targetClazz) {
        PageResultDTO pageResultDTO = setPage(page);
        List<T> records = BeanUtil.copyList(page.getResult(), targetClazz);
        pageResultDTO.setList(records);
        return pageResultDTO;
    }

    private static PageResultDTO setPage(Page page) {
        PageResultDTO pageResultDTO = new PageResultDTO();
        pageResultDTO.setPageNum((long) page.getPageNum());
        pageResultDTO.setPageSize((long) page.getPageSize());
        pageResultDTO.setTotal(page.getTotal());
        pageResultDTO.setPages((long) page.getPages());
        return pageResultDTO;
    }
}
