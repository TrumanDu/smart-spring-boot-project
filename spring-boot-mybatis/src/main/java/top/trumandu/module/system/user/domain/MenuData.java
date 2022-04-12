package top.trumandu.module.system.user.domain;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/12
 * @description
 */
public class MenuData {
    private String path;
    private String name;
    private String icon;
    private List<MenuData> routes;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuData> getRoutes() {
        return routes;
    }

    public void setRoutes(List<MenuData> routes) {
        this.routes = routes;
    }
}
