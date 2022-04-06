package top.trumandu.module.system.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.org.domain.SysOrgBaseDTO;
import top.trumandu.module.system.org.domain.SysOrgUpdateDTO;
import top.trumandu.module.system.org.domain.SysOrgVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
@RestController
public class SysOrgController {
    @Autowired
    SysOrgService sysOrgService;

    @GetMapping("/sys_org/list")
    public ResponseDTO<List<SysOrgVO>> listAllSysOrg() {
        return sysOrgService.listAllSysOrg();
    }
    @PostMapping("/sys_org/add")
    public ResponseDTO addSysOrg(@Valid  @RequestBody SysOrgBaseDTO sysOrgBaseDTO){
        return sysOrgService.addSysOrg(sysOrgBaseDTO);
    }

    @PutMapping("/sys_org/update")
    public ResponseDTO updateSysOrg(@Valid  @RequestBody SysOrgUpdateDTO sysOrgUpdateDTO){
        return sysOrgService.updateSysOrg(sysOrgUpdateDTO);
    }

    @SysLog(operation = "删除组织")
    @DeleteMapping("/sys_org/delete/{id}")
    public ResponseDTO deleteSysOrg(@PathVariable Long id) {
        return sysOrgService.deleteSysOrg(id);
    }
}
