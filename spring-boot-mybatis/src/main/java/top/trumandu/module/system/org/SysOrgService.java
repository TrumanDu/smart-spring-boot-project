package top.trumandu.module.system.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.TreeSelectDTO;
import top.trumandu.module.system.org.domain.SysOrgBaseDTO;
import top.trumandu.module.system.org.domain.SysOrgEntity;
import top.trumandu.module.system.org.domain.SysOrgUpdateDTO;
import top.trumandu.module.system.org.domain.SysOrgVO;
import top.trumandu.util.BeanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<SysOrgVO> roots = new ArrayList<>();
        Map<Long, List<SysOrgVO>> tree = new HashMap<>();
        for (int i = 0; i < dbList.size(); i++) {
            SysOrgEntity entity = dbList.get(i);
            SysOrgVO vo = BeanUtil.copy(entity, SysOrgVO.class);
            if (entity.getParentId() == null) {
                roots.add(vo);
            } else {
                List<SysOrgVO> list = null;
                if (tree.containsKey(entity.getParentId())) {
                    list = tree.get(entity.getParentId());
                } else {
                    list = new ArrayList<>();
                }
                list.add(vo);
                tree.put(entity.getParentId(), list);
            }
        }
        //递归设置所有的孩子节点
        for (SysOrgVO sysOrgVO : roots) {
            setChildByParentNode(tree, sysOrgVO);
        }
        return roots;
    }

    public List<TreeSelectDTO> getTreeSelectData() {
        List<SysOrgEntity> dbList = sysOrgDao.selectSysOrgList();
        //获取所有的跟节点
        List<TreeSelectDTO> roots = new ArrayList<>();
        Map<Long, List<TreeSelectDTO>> tree = new HashMap<>();
        for (int i = 0; i < dbList.size(); i++) {
            SysOrgEntity entity = dbList.get(i);
            TreeSelectDTO vo = new TreeSelectDTO(entity.getId(), entity.getOrgName());
            if (entity.getParentId() == null) {
                roots.add(vo);
            } else {
                List<TreeSelectDTO> list = null;
                if (tree.containsKey(entity.getParentId())) {
                    list = tree.get(entity.getParentId());
                } else {
                    list = new ArrayList<>();
                }
                list.add(vo);
                tree.put(entity.getParentId(), list);
            }
        }
        //递归设置所有的孩子节点
        for (TreeSelectDTO sysOrgSelectDTO : roots) {
            setChildByParentNode(tree, sysOrgSelectDTO);
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

    private void setChildByParentNode(Map<Long, List<SysOrgVO>> tree, SysOrgVO vo) {
        Long id = vo.getId();
        List<SysOrgVO> child = null;
        if (tree.containsKey(id)) {
            child = tree.get(id);
        }
        if (child != null) {
            for (SysOrgVO sysOrgVO : child) {
                setChildByParentNode(tree, sysOrgVO);
            }
        }
        vo.setChildren(child);
    }

    private void setChildByParentNode(Map<Long, List<TreeSelectDTO>> tree, TreeSelectDTO vo) {
        Long id = vo.getValue();
        List<TreeSelectDTO> child = null;
        if (tree.containsKey(id)) {
            child = tree.get(id);
        }
        if (child != null) {
            for (TreeSelectDTO sysOrgVO : child) {
                setChildByParentNode(tree, sysOrgVO);
            }
        }

        vo.setChildren(child);
    }
}
