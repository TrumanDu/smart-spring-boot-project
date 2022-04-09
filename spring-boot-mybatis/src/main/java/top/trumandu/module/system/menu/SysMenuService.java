package top.trumandu.module.system.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.TreeSelectDTO;
import top.trumandu.module.system.menu.domain.SysMenuBaseDTO;
import top.trumandu.module.system.menu.domain.SysMenuEntity;
import top.trumandu.module.system.menu.domain.SysMenuUpdateDTO;
import top.trumandu.module.system.menu.domain.SysMenuVO;
import top.trumandu.util.BeanUtil;
import top.trumandu.util.SmartCurrentUserUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
@Service
public class SysMenuService {
    @Autowired
    SysMenuDao sysMenuDao;

    public List<SysMenuVO> listAll() {
        List<SysMenuEntity> dbList = sysMenuDao.selectList();
        //获取所有的跟节点
        List<SysMenuVO> roots = new ArrayList<>();
        Map<Long, List<SysMenuVO>> tree = new HashMap<>();
        for (int i = 0; i < dbList.size(); i++) {
            SysMenuEntity entity = dbList.get(i);
            SysMenuVO vo = BeanUtil.copy(entity, SysMenuVO.class);
            if (entity.getParentId() == null) {
                roots.add(vo);
            } else {
                List<SysMenuVO> list = null;
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
        for (SysMenuVO sysMenuVO : roots) {
            setChildByParentNode(tree, sysMenuVO);
        }
        return roots;
    }

    public List<TreeSelectDTO> getTreeSelectData() {
        List<SysMenuEntity> dbList = sysMenuDao.selectList();
        //获取所有的跟节点
        List<TreeSelectDTO> roots = new ArrayList<>();
        Map<Long, List<TreeSelectDTO>> tree = new HashMap<>();
        for (int i = 0; i < dbList.size(); i++) {
            SysMenuEntity entity = dbList.get(i);
            TreeSelectDTO vo = new TreeSelectDTO(entity.getId(), entity.getMenuName());
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


    public ResponseDTO add(SysMenuBaseDTO baseDTO) {
        SysMenuEntity entity = BeanUtil.copy(baseDTO, SysMenuEntity.class);
        entity.setCreateUserId(SmartCurrentUserUtil.getCurrentUserId());
        sysMenuDao.insert(entity);
        return ResponseDTO.success();
    }

    public ResponseDTO update(SysMenuUpdateDTO updateDTO) {
        SysMenuEntity entity = BeanUtil.copy(updateDTO, SysMenuEntity.class);
        entity.setLastEditUserId(SmartCurrentUserUtil.getCurrentUserId());
        sysMenuDao.updateById(entity);
        return ResponseDTO.success();
    }

    public ResponseDTO delete(List<Long> ids) {
        sysMenuDao.deleteBatchIds(ids);
        return ResponseDTO.success();
    }

    private void setChildByParentNode(Map<Long, List<SysMenuVO>> tree, SysMenuVO vo) {
        Long id = vo.getId();
        List<SysMenuVO> child = new ArrayList<>();
        if (tree.containsKey(id)) {
            child = tree.get(id);
        }
        for (SysMenuVO sysMenuVO : child) {
            setChildByParentNode(tree, sysMenuVO);
        }
        vo.setChildren(child);
    }

    private void setChildByParentNode(Map<Long, List<TreeSelectDTO>> tree, TreeSelectDTO treeSelectDTO) {
        Long id = treeSelectDTO.getValue();
        List<TreeSelectDTO> child = new ArrayList<>();
        if (tree.containsKey(id)) {
            child = tree.get(id);
        }
        for (TreeSelectDTO sysOrgVO : child) {
            setChildByParentNode(tree, sysOrgVO);
        }
        treeSelectDTO.setChildren(child);
    }
}
