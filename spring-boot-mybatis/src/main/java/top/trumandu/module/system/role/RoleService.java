package top.trumandu.module.system.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.role.domain.*;
import top.trumandu.util.BeanUtil;
import top.trumandu.util.SmartCurrentUserUtil;

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

    public List<RoleVO> listRoles() {
        List<RoleEntity> roles = roleDao.selectList(null);
        List<RoleVO> result = BeanUtil.copyList(roles, RoleVO.class);
        return result;
    }

    public ResponseDTO addRole(RoleBaseDTO roleBaseDTO) {
        RoleEntity role = BeanUtil.copy(roleBaseDTO, RoleEntity.class);
        role.setCreateUserId(SmartCurrentUserUtil.getCurrentUserId());
        roleDao.insert(role);
        return ResponseDTO.success();
    }

    public ResponseDTO updateRole(RoleUpdateDTO roleUpdateDTO) {
        RoleEntity role = BeanUtil.copy(roleUpdateDTO, RoleEntity.class);
        role.setLastEditUserId(SmartCurrentUserUtil.getCurrentUserId());
        roleDao.updateById(role);
        return ResponseDTO.success();
    }

    public ResponseDTO deleteRole(Long id) {
        roleDao.deleteById(id);
        return ResponseDTO.success();
    }

    public ResponseDTO<List<UserRoleVO>> getUserListByRole(Long roleId) {
        List<UserRoleVO> dbResult = roleDao.selectUserListByRole(roleId);
        return ResponseDTO.success(dbResult);
    }

    public void addUserRole(Long roleId,Long userId){
        roleDao.insertUserRole(roleId,userId);
    }

    public ResponseDTO deleteRoleUser(Long id) {
        roleDao.deleteRoleUser(id);
        return ResponseDTO.success();
    }
}
