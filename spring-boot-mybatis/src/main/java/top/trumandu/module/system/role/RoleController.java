package top.trumandu.module.system.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.trumandu.module.system.role.domain.RoleEntity;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2021/08/14
 * @description
 */
@RestController
public class RoleController  {

    @Autowired
    RoleService roleService;

    @GetMapping("/role/list")
    public List<RoleEntity> list() {
        return null;
    }

    @PostMapping("/role/add")
    public String add(RoleEntity role) {
        return null;
    }

    @PostMapping("/role/update")
    public String update(RoleEntity role) {
        return null;
    }

    @GetMapping("/role/delete/{id}")
    public String delete(@PathVariable Long id) {
        return null;
    }

}
