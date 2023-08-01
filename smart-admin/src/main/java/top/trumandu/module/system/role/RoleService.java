package top.trumandu.module.system.role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.trumandu.common.domain.Response;
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

    private final RoleDao roleDao;
    private final RoleMenuDao roleMenuDao;
    private final SysMenuService sysMenuService;

    public RoleService(RoleDao roleDao, RoleMenuDao roleMenuDao, SysMenuService sysMenuService) {
        this.roleDao = roleDao;
        this.roleMenuDao = roleMenuDao;
        this.sysMenuService = sysMenuService;
    }

    public List<RoleVO> listRoles() {
        List<RoleEntity> roles = roleDao.selectList(null);
        return BeanUtil.copyList(roles, RoleVO.class);
    }

    public Response addRole(RoleBaseDTO roleBaseDTO) {
        RoleEntity role = BeanUtil.copy(roleBaseDTO, RoleEntity.class);
        role.setCreateUserId(SmartCurrentUserUtil.getCurrentUserId());
        roleDao.insert(role);
        return Response.ok();
    }

    public Response updateRole(RoleUpdateDTO roleUpdateDTO) {
        RoleEntity role = BeanUtil.copy(roleUpdateDTO, RoleEntity.class);
        role.setLastEditUserId(SmartCurrentUserUtil.getCurrentUserId());
        roleDao.updateById(role);
        return Response.ok();
    }

    public Response deleteRole(Long id) {
        roleDao.deleteById(id);
        return Response.ok();
    }

    public Response getUserListByRole(Long roleId) {
        List<UserRoleVO> dbResult = roleDao.selectUserListByRole(roleId);
        return Response.ok().data(dbResult);
    }

    public List<Long> listMenuIdsByRole(Long roleId) {
        return roleMenuDao.selectMenuIdsByRole(roleId);
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
        if (!roleMenuEntities.isEmpty()) {
            roleMenuDao.batchInsert(roleMenuEntities);
        }
    }

    public void addUserRole(Long roleId, Long userId) {
        roleDao.insertUserRole(roleId, userId);
    }

    public Response deleteRoleUser(Long id) {
        roleDao.deleteRoleUser(id);
        return Response.ok();
    }
}
