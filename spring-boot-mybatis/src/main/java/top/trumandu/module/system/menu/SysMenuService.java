package top.trumandu.module.system.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.TreeDTO;
import top.trumandu.module.system.menu.domain.SysMenuBaseDTO;
import top.trumandu.module.system.menu.domain.SysMenuEntity;
import top.trumandu.module.system.menu.domain.SysMenuUpdateDTO;
import top.trumandu.module.system.menu.domain.SysMenuVO;
import top.trumandu.util.BeanUtil;
import top.trumandu.util.SmartCurrentUserUtil;
import top.trumandu.util.TreeGeneratorUtil;

import java.util.List;

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
        List<SysMenuEntity> dbList = sysMenuDao.selectMenuList();
        return generatorSysMenuVOList(dbList);
    }

    public List<SysMenuEntity> listAllSysMenuEntity() {
        List<SysMenuEntity> dbList = sysMenuDao.selectMenuList();
        return dbList;
    }

    public List<SysMenuVO> listMenuListByRole(Long roleId) {
        List<SysMenuEntity> dbList = sysMenuDao.selectMenuListByRole(roleId);
        return generatorSysMenuVOList(dbList);
    }


    public List<TreeDTO> getTreeSelectData() {
        List<SysMenuEntity> dbList = sysMenuDao.selectMenuList();
        //获取所有的跟节点
        List<TreeDTO> roots;
        try {
            roots = TreeGeneratorUtil.convertToTreeDTO(dbList, "getMenuName", "getId");
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    private List<SysMenuVO> generatorSysMenuVOList(List<SysMenuEntity> dbList) {
        //获取所有的跟节点
        List<SysMenuVO> roots = null;
        try {
            roots = TreeGeneratorUtil.convertToTreeBeanDTO(dbList, SysMenuVO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return roots;
    }
}
