package top.trumandu.module.system.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.module.system.role.domain.RoleEntity;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2021/08/14
 * @description
 */
@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public void addRole(RoleEntity role) {
        roleDao.insert(role);
    }

    public void updateRole(RoleEntity role) {
        roleDao.updateById(role);
    }

    public void deleteRole(Long id) {
        roleDao.deleteById(id);
    }

    public List<RoleEntity> listAllRoles() {
        List<RoleEntity> roles = roleDao.selectList(null);
        return roles;
    }


}
