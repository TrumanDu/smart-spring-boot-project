package top.trumandu.common.domain;

/**
 * @author Truman.P.Du
 * @date 2022/04/10
 * @description
 */
@SuppressWarnings("unused")
public class SelectDTO {
    private String label;
    private String value;

    public SelectDTO() {
    }

    public SelectDTO(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SelectDTO{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
