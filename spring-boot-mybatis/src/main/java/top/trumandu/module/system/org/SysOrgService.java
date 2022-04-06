package top.trumandu.module.system.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.org.domain.SysOrgBaseDTO;
import top.trumandu.module.system.org.domain.SysOrgUpdateDTO;
import top.trumandu.module.system.org.domain.SysOrgVO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
@Service
public class SysOrgService {
    @Autowired
    SysOrgDao sysOrgDao;

    public ResponseDTO<List<SysOrgVO>> listAllSysOrg() {
        ResponseDTO<List<SysOrgVO>> result = new ResponseDTO<>(null);
        return result;
    }

    public ResponseDTO addSysOrg(SysOrgBaseDTO sysOrgBaseDTO) {
        sysOrgDao.insert(sysOrgBaseDTO);
        return ResponseDTO.success();
    }

    public ResponseDTO updateSysOrg(SysOrgUpdateDTO sysOrgUpdateDTO) {
        sysOrgDao.updateById(sysOrgUpdateDTO);
        return ResponseDTO.success();
    }

    public ResponseDTO deleteSysOrg(Long id) {
        return ResponseDTO.success();
    }
}
