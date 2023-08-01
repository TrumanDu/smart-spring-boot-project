package top.trumandu.module.system.org;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.Response;
import top.trumandu.module.system.org.domain.SysOrgBaseDTO;
import top.trumandu.module.system.org.domain.SysOrgUpdateDTO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
@RestController
public class SysOrgController {
    private final SysOrgService sysOrgService;

    public SysOrgController(SysOrgService sysOrgService) {
        this.sysOrgService = sysOrgService;
    }

    @GetMapping("/sys_org/list")
    public Response listAllSysOrg() {
        return Response.ok().data(sysOrgService.listAllSysOrg());
    }

    @GetMapping("/sys_org/list/tree_select")
    public Response getTreeSelectData() {
        return Response.ok().data(sysOrgService.getTreeSelectData());
    }

    @SysLog(operation = "新增组织", params = true)
    @PostMapping("/sys_org/add")
    public Response addSysOrg(@Valid @RequestBody SysOrgBaseDTO sysOrgBaseDTO) {
        return sysOrgService.addSysOrg(sysOrgBaseDTO);
    }

    @SysLog(operation = "修改组织", params = true)
    @PutMapping("/sys_org/update")
    public Response updateSysOrg(@Valid @RequestBody SysOrgUpdateDTO sysOrgUpdateDTO) {
        return sysOrgService.updateSysOrg(sysOrgUpdateDTO);
    }

    @SysLog(operation = "删除组织", params = true)
    @PostMapping(value = "/sys_org/delete")
    public Response deleteSysOrg(@RequestBody List<Long> ids) {
        return sysOrgService.deleteSysOrg(ids);
    }
}
