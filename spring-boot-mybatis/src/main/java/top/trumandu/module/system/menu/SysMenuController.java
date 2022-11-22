package top.trumandu.module.system.menu;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.Response;
import top.trumandu.module.system.menu.domain.SysMenuBaseDTO;
import top.trumandu.module.system.menu.domain.SysMenuUpdateDTO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
@RestController
public class SysMenuController {
    private final SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @GetMapping("/sys_menu/list")
    public Response listAllSysOrg() {
        return Response.ok().data(sysMenuService.listAll());
    }

    @GetMapping("/sys_menu/list/tree_select")
    public Response getTreeSelectData() {
        return Response.ok().data(sysMenuService.getTreeSelectData());
    }

    @SysLog(operation = "新增菜单", params = true)
    @PostMapping("/sys_menu/add")
    public Response addSysOrg(@Valid @RequestBody SysMenuBaseDTO baseDTO) {
        return sysMenuService.add(baseDTO);
    }

    @SysLog(operation = "修改菜单", params = true)
    @PutMapping("/sys_menu/update")
    public Response update(@Valid @RequestBody SysMenuUpdateDTO updateDTO) {
        return sysMenuService.update(updateDTO);
    }

    @SysLog(operation = "删除菜单", params = true)
    @PostMapping(value = "/sys_menu/delete")
    public Response deleteMenu(@RequestBody List<Long> ids) {
        return sysMenuService.delete(ids);
    }
}
