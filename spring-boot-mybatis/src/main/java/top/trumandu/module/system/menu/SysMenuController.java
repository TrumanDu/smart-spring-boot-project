package top.trumandu.module.system.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.TreeSelectDTO;
import top.trumandu.module.system.menu.domain.SysMenuBaseDTO;
import top.trumandu.module.system.menu.domain.SysMenuUpdateDTO;
import top.trumandu.module.system.org.domain.SysOrgVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
@RestController
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/sys_menu/list")
    public ResponseDTO<List<SysOrgVO>> listAllSysOrg() {
        return ResponseDTO.success(sysMenuService.listAll());
    }

    @GetMapping("/sys_menu/list/tree_select")
    public ResponseDTO<List<TreeSelectDTO>> getTreeSelectData() {
        return ResponseDTO.success(sysMenuService.getTreeSelectData());
    }

    @SysLog(operation = "新增菜单", params = true)
    @PostMapping("/sys_menu/add")
    public ResponseDTO addSysOrg(@Valid @RequestBody SysMenuBaseDTO baseDTO) {
        return sysMenuService.add(baseDTO);
    }

    @SysLog(operation = "修改菜单", params = true)
    @PutMapping("/sys_menu/update")
    public ResponseDTO update(@Valid @RequestBody SysMenuUpdateDTO updateDTO) {
        return sysMenuService.update(updateDTO);
    }

    @SysLog(operation = "删除菜单", params = true)
    @PostMapping(value = "/sys_menu/delete")
    public ResponseDTO deleteMenu(@RequestBody List<Long> ids) {
        return sysMenuService.delete(ids);
    }
}
