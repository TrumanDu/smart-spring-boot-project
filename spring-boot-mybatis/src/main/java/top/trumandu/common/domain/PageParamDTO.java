package top.trumandu.common.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/01
 * @description
 */
public class PageParamDTO {
    @NotNull(message = "分页参数不能为空")
    protected Integer pageNum;

    @NotNull(message = "每页数量不能为空")
    @Max(value = 500, message = "每页最大为500")
    protected Integer pageSize;

    protected Boolean searchCount;

    protected List<OrderItemDTO> orders;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public List<OrderItemDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "PageParamDTO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", searchCount=" + searchCount +
                ", orders=" + orders +
                '}';
    }
}
