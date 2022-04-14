package top.trumandu.module.system.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.TreeDTO;
import top.trumandu.module.system.org.domain.SysOrgBaseDTO;
import top.trumandu.module.system.org.domain.SysOrgEntity;
import top.trumandu.module.system.org.domain.SysOrgUpdateDTO;
import top.trumandu.module.system.org.domain.SysOrgVO;
import top.trumandu.util.BeanUtil;
import top.trumandu.util.TreeGeneratorUtil;

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

    public List<SysOrgVO> listAllSysOrg() {
        List<SysOrgEntity> dbList = sysOrgDao.selectSysOrgList();
        //获取所有的跟节点
        List<SysOrgVO> roots = null;
        try {
            roots = TreeGeneratorUtil.convertToTreeBeanDTO(dbList, SysOrgVO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return roots;
    }

    public List<TreeDTO> getTreeSelectData() {
        List<SysOrgEntity> dbList = sysOrgDao.selectSysOrgList();
        //获取所有的跟节点
        List<TreeDTO> roots = null;
        try {
            roots = TreeGeneratorUtil.convertToTreeDTO(dbList,"getOrgName","getId");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return roots;
    }


    public ResponseDTO addSysOrg(SysOrgBaseDTO sysOrgBaseDTO) {
        SysOrgEntity entity = BeanUtil.copy(sysOrgBaseDTO, SysOrgEntity.class);
        sysOrgDao.insert(entity);
        return ResponseDTO.success();
    }

    public ResponseDTO updateSysOrg(SysOrgUpdateDTO sysOrgUpdateDTO) {
        SysOrgEntity entity = BeanUtil.copy(sysOrgUpdateDTO, SysOrgEntity.class);
        sysOrgDao.updateById(entity);
        return ResponseDTO.success();
    }

    public ResponseDTO deleteSysOrg(List<Long> ids) {
        sysOrgDao.deleteBatchIds(ids);
        return ResponseDTO.success();
    }
}
