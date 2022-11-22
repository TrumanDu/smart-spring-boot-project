package top.trumandu.module.system.menu;

import org.springframework.stereotype.Service;
import top.trumandu.common.domain.Response;
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
    private final SysMenuDao sysMenuDao;

    public SysMenuService(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
    }

    public List<SysMenuVO> listAll() {
        List<SysMenuEntity> dbList = sysMenuDao.selectMenuList();
        return generatorSysMenuVOList(dbList);
    }

    @SuppressWarnings("unused")
    public List<SysMenuEntity> listAllSysMenuEntity() {
        return sysMenuDao.selectMenuList();
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


    public Response add(SysMenuBaseDTO baseDTO) {
        SysMenuEntity entity = BeanUtil.copy(baseDTO, SysMenuEntity.class);
        entity.setCreateUserId(SmartCurrentUserUtil.getCurrentUserId());
        sysMenuDao.insert(entity);
        return Response.ok();
    }

    public Response update(SysMenuUpdateDTO updateDTO) {
        SysMenuEntity entity = BeanUtil.copy(updateDTO, SysMenuEntity.class);
        entity.setLastEditUserId(SmartCurrentUserUtil.getCurrentUserId());
        sysMenuDao.updateById(entity);
        return Response.ok();
    }

    public Response delete(List<Long> ids) {
        sysMenuDao.deleteBatchIds(ids);
        return Response.ok();
    }

    private List<SysMenuVO> generatorSysMenuVOList(List<SysMenuEntity> dbList) {
        //获取所有的跟节点
        List<SysMenuVO> roots;
        try {
            roots = TreeGeneratorUtil.convertToTreeBeanDTO(dbList, SysMenuVO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return roots;
    }
}
