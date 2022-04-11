package top.trumandu.module.system.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.role.domain.RoleBaseDTO;
import top.trumandu.module.system.role.domain.RoleMenuDTO;
import top.trumandu.module.system.role.domain.RoleUpdateDTO;
import top.trumandu.module.system.role.domain.UserRoleDTO;

import javax.validation.Valid;

/**
 * @author Truman.P.Du
 * @date 2021/08/14
 * @description
 */
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;


    @GetMapping("/sys_role/list")
    public ResponseDTO list() {
        return ResponseDTO.success(roleService.listRoles());
    }

    @SysLog(operation = "新增角色", params = true)
    @PostMapping("/sys_role/add")
    public ResponseDTO addRole(@Valid @RequestBody RoleBaseDTO role) {
        return roleService.addRole(role);
    }

    @SysLog(operation = "编辑角色", params = true)
    @PutMapping("/sys_role/update")
    public ResponseDTO updateRole(@Valid @RequestBody RoleUpdateDTO roleUpdateDTO) {
        return roleService.updateRole(roleUpdateDTO);
    }

    @SysLog(operation = "删除角色")
    @DeleteMapping("/sys_role/delete/{id}")
    public ResponseDTO deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("/sys_role_user/list/{roleId}")
    public ResponseDTO listRoleUser(@PathVariable Long roleId) {
        return roleService.getUserListByRole(roleId);
    }

    @SysLog(operation = "删除角色用户")
    @DeleteMapping("/sys_role_user/delete/{id}")
    public ResponseDTO deleteRoleUser(@PathVariable Long id) {
        return roleService.deleteRoleUser(id);
    }

    @SysLog(operation = "用户赋予角色", params = true)
    @PostMapping("/sys_role_user/add")
    public ResponseDTO addUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        roleService.addUserRole(userRoleDTO.getRoleId(), userRoleDTO.getUserId());
        return ResponseDTO.success();
    }

    @GetMapping("/sys_role_menu/list/{roleId}")
    public ResponseDTO listRoleMenu(@PathVariable Long roleId) {
        return roleService.getRoleMenuVO(roleId);
    }

    @PostMapping("/sys_role_menu/add")
    public ResponseDTO listRoleMenu(@RequestBody RoleMenuDTO roleMenuDTO) {
        roleService.saveRoleMenu(roleMenuDTO);
        return ResponseDTO.success();
    }

}
