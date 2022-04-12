package top.trumandu.module.system.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.menu.SysMenuService;
import top.trumandu.module.system.menu.domain.SysMenuVO;
import top.trumandu.module.system.role.domain.*;
import top.trumandu.util.BeanUtil;
import top.trumandu.util.SmartCurrentUserUtil;

import java.util.ArrayList;
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
    @Autowired
    RoleMenuDao roleMenuDao;
    @Autowired
    SysMenuService sysMenuService;

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

    public List<Long> listMenuIdsByRole(Long roleId) {
        List<Long> dbList = roleMenuDao.selectMenuIdsByRole(roleId);
        return dbList;
    }

    public RoleMenuVO getRoleMenuVO(Long roleId) {
        List<SysMenuVO> menuList = sysMenuService.listAll();
        List<Long> selectKeys = listMenuIdsByRole(roleId);
        RoleMenuVO roleMenuVO = new RoleMenuVO();
        roleMenuVO.setMenuVOList(menuList);
        roleMenuVO.setCheckedKeys(selectKeys);
        return roleMenuVO;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveRoleMenu(RoleMenuDTO roleMenuDTO) {
        //1. 先删除role所有菜单
        roleMenuDao.deleteByRoleId(roleMenuDTO.getRoleId());
        //2. 保存最新role所有菜单
        List<RoleMenuEntity> roleMenuEntities = new ArrayList<>();
        roleMenuDTO.getMenuIds().forEach(id -> {
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setMenuId(id);
            roleMenuEntity.setRoleId(roleMenuDTO.getRoleId());
            roleMenuEntities.add(roleMenuEntity);
        });
        roleMenuDao.batchInsert(roleMenuEntities);
    }

    public void addUserRole(Long roleId, Long userId) {
        roleDao.insertUserRole(roleId, userId);
    }

    public ResponseDTO deleteRoleUser(Long id) {
        roleDao.deleteRoleUser(id);
        return ResponseDTO.success();
    }
}
