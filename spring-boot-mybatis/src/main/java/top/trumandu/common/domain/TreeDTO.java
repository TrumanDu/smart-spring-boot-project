package top.trumandu.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeDTO {
    private Long value;
    private String title;
    private List<TreeDTO> children;

    public TreeDTO(Long value, String title) {
        this.value = value;
        this.title = title;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeSelectDTO{" +
                "value=" + value +
                ", title='" + title + '\'' +
                ", children=" + children +
                '}';
    }
}
