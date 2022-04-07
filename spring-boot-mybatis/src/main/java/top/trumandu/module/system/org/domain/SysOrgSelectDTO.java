package top.trumandu.module.system.org.domain;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/07
 * @description
 */
public class SysOrgSelectDTO {
    private Long value;
    private String title;
    private List<SysOrgSelectDTO>children;

    public SysOrgSelectDTO(Long value,String title){
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

    public List<SysOrgSelectDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SysOrgSelectDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysOrgSelectDTO{" +
                "value=" + value +
                ", title='" + title + '\'' +
                ", children=" + children +
                '}';
    }
}
