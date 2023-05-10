package top.trumandu.common.domain;

/**
 * @author Truman.P.Du
 * @date 2022/04/01
 * @description
 */
@SuppressWarnings("unused")
public class OrderItemDTO {
    private String column;
    private Boolean asc;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "column='" + column + '\'' +
                ", asc=" + asc +
                '}';
    }
}
