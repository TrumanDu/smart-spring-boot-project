package top.trumandu.module.system.menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.trumandu.module.system.menu.domain.SysMenuEntity;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
    /**
     * 查询所有的菜单列表
     * @return
     */
    List<SysMenuEntity> selectMenuList();

    /**
     * 查询角色所拥有的所有菜单
     * @param roleId
     * @return
     */
    List<SysMenuEntity> selectMenuListByRole(@Param("roleId") Long roleId);


}
